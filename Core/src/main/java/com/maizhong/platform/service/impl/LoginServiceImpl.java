package com.maizhong.platform.service.impl;

import com.maizhong.common.enums.AuthEnum;
import com.maizhong.common.enums.OperateEnum;
import com.maizhong.common.result.JsonResult;
import com.maizhong.common.utils.IDUtils;
import com.maizhong.platform.dto.NavsDto;
import com.maizhong.platform.mapper.MUserMapper;
import com.maizhong.platform.pojo.MUser;
import com.maizhong.platform.pojo.MUserExample;
import com.maizhong.platform.service.LoginService;
import com.maizhong.platform.service.MenuService;
import com.maizhong.platform.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录接口实现
 * Created by Xushd on 2017/9/30.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private MUserMapper mUserMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RedisService redisService;

    /**
     * Manage 登录
     * @param phone
     * @param pass
     * @return
     */
    @Override
    public JsonResult manageLogin(String phone, String pass) {

        MUserExample muEx = new MUserExample();
        muEx.createCriteria().andDelflagEqualTo(0).andPhoneEqualTo(Long.valueOf(phone));
        List<MUser> mUsers = mUserMapper.selectByExample(muEx);
        if(mUsers.size()==0)return JsonResult.Error(AuthEnum.USER_NO_EXIT);
        MUser mUser = mUsers.get(0);
        if(mUser.getStatus()==0)return JsonResult.Error(AuthEnum.USER_STOP);
        if(!StringUtils.equals(pass,mUser.getPassword()))return JsonResult.Error(AuthEnum.USER_ERROR_PASSWORD);

        String manageTokenByPhone = redisService.getManageTokenByPhone(phone);
        if(manageTokenByPhone==null){
            manageTokenByPhone = IDUtils.getUUID();
        }
        redisService.createManageLoginUser(manageTokenByPhone,mUser);

        List<NavsDto> manageMenu = menuService.getManageMenu();

        redisService.createManageMenu(manageTokenByPhone,manageMenu);


        return JsonResult.build(200,manageTokenByPhone,mUser);

    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @Override
    public JsonResult manageLogout(String token) {


        redisService.ManageUserLogout(token);

        return JsonResult.OK();
    }
}
