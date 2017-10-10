package com.maizhong.platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maizhong.common.dto.PageSearchParam;
import com.maizhong.common.enums.AuthEnum;
import com.maizhong.common.enums.OperateEnum;
import com.maizhong.common.result.JsonResult;
import com.maizhong.common.utils.IDUtils;
import com.maizhong.common.utils.SqlUtils;
import com.maizhong.platform.mapper.MUserMapper;
import com.maizhong.platform.pojo.MUser;
import com.maizhong.platform.pojo.MUserExample;
import com.maizhong.platform.service.MUserService;
import com.maizhong.platform.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Xushd on 2017/10/10.
 */
@Service
public class MUserServiceImpl implements MUserService {

    @Autowired
    private MUserMapper mUserMapper;
    @Autowired
    private RedisService redisService;

    /**
     * Manage 获取用户列表
     * @param param
     * @return
     */
    @Override
    public JsonResult getManageUserList(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());
        MUserExample muEx = new MUserExample();
        MUserExample.Criteria criteria = muEx.createCriteria().andDelflagEqualTo(0);

        if(param.getFiled("phone")!=null){
            criteria.andPhoneEqualTo(Long.valueOf(param.getFiled("phone")));
        }
        if(param.getFiled("name")!=null){
            criteria.andNameLike(SqlUtils.getLikeSql(param.getFiled("name")));
        }
        List<MUser> mUsers = mUserMapper.selectByExample(muEx);
        PageInfo<MUser> pageInfo = new PageInfo<>(mUsers);


        return JsonResult.OK(pageInfo);
    }

    /**
     * Manage 用户更新/新增
     * @param mUser
     * @param token
     * @return
     */
    @Override
    public JsonResult handleManageUser(MUser mUser, String token) {

        MUser user = redisService.getManageUserByToken(token);
        if(user==null)return JsonResult.Error(AuthEnum.TIME_OUT);
        int i;
        if(user.getId()==-1){
            //新增
            mUser.setId(null);
            mUser.setStatus(1);
            mUser.setDelflag(0);
            mUser.setPassword(IDUtils.sha256("123456"));
            mUser.setUpdateUser(user.getName());
            mUser.setUpdateTime(new Date());
            i = mUserMapper.insertSelective(mUser);
        }else{
            //更新
            mUser.setUpdateUser(user.getName());
            mUser.setUpdateTime(new Date());
            i = mUserMapper.updateByPrimaryKeySelective(mUser);
        }
        if(i>0)return JsonResult.OK(OperateEnum.SUCCESS);

        return JsonResult.Error(OperateEnum.SERVER_ERROR);
    }

    /**
     * Manage 更新状态
     * @param id
     * @param status
     * @param token
     * @return
     */
    @Override
    public JsonResult statusManageUser(long id, int status, String token) {
        MUser user = redisService.getManageUserByToken(token);
        if(user==null)return JsonResult.Error(AuthEnum.TIME_OUT);
        MUser mUser = new MUser();
        mUser.setId(id);
        mUser.setStatus(status);
        int i = mUserMapper.updateByPrimaryKeySelective(mUser);
        if(i>0)return JsonResult.OK(OperateEnum.SUCCESS);

        return JsonResult.Error(OperateEnum.SERVER_ERROR);
    }

    /**
     * Manage 用户删除
     * @param id
     * @param token
     * @return
     */
    @Override
    public JsonResult delManageUser(long id, String token) {
        MUser user = redisService.getManageUserByToken(token);
        if(user==null)return JsonResult.Error(AuthEnum.TIME_OUT);
        MUser mUser = new MUser();
        mUser.setId(id);
        mUser.setDelflag(1);
        int i = mUserMapper.updateByPrimaryKeySelective(mUser);
        if(i>0)return JsonResult.OK(OperateEnum.SUCCESS);

        return JsonResult.Error(OperateEnum.SERVER_ERROR);
    }


}
