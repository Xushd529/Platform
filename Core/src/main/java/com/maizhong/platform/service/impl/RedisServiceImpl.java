package com.maizhong.platform.service.impl;

import com.maizhong.common.utils.JsonUtils;
import com.maizhong.platform.dao.JedisClient;
import com.maizhong.platform.dto.NavsDto;
import com.maizhong.platform.pojo.MUser;
import com.maizhong.platform.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 缓存接口实现
 * Created by Xushd on 2017/10/9.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisClient jedisClient;


    /**
     * Manage 通过手机号查询token
     *
     * @param phone
     * @return
     */
    @Override
    public String getManageTokenByPhone(String phone) {
        String json = jedisClient.get("MANAGE_PHONE_TOKEN:" + phone);
        if (StringUtils.isBlank(json)) return null;
        return json;
    }

    /**
     * Manage 创建登录用户缓存
     *
     * @param token
     * @param mUser
     */
    @Override
    public void createManageLoginUser(String token, MUser mUser) {
        jedisClient.set("MANAGE_TOKEN:" + token, JsonUtils.objectToJson(mUser));
        jedisClient.expire("MANAGE_TOKEN:" + token, 60 * 30);
        jedisClient.set("MANAGE_PHONE_TOKEN:" + mUser.getPhone(), token);
        jedisClient.expire("MANAGE_PHONE_TOKEN:" + mUser.getPhone(), 60 * 30);

    }

    /**
     * Manage 创建用户菜单
     *
     * @param manageMenu
     */
    @Override
    public void createManageMenu(String token, List<NavsDto> manageMenu) {
        jedisClient.set("MANAGE_MENU_TOKEN:" + token, JsonUtils.objectToJson(manageMenu));
        jedisClient.expire("MANAGE_MENU_TOKEN:" + token, 60 * 30);
    }

    /**
     * Manage 获取用户菜单
     *
     * @param token
     * @return
     */
    @Override
    public List<NavsDto> getManageMenu(String token) {
        String json = jedisClient.get("MANAGE_MENU_TOKEN:" + token);
        if (StringUtils.isBlank(json)) return null;
        return JsonUtils.jsonToList(json, NavsDto.class);
    }

    /**
     * Manage 获取登录用户信息
     *
     * @param token
     * @return
     */
    @Override
    public MUser getManageUserByToken(String token) {
        String json = jedisClient.get("MANAGE_TOKEN:" + token);
        if (StringUtils.isBlank(json)) return null;
        return JsonUtils.jsonToPojo(json, MUser.class);
    }

    /**
     * Manage 用户退出
     *
     * @param token
     */
    @Override
    public void ManageUserLogout(String token) {
        MUser mUser = this.getManageUserByToken(token);
        jedisClient.del("MANAGE_TOKEN:" + token);
        jedisClient.del("MANAGE_MENU_TOKEN:" + token);
        jedisClient.del("MANAGE_PHONE_TOKEN:" + mUser.getPhone());
    }
}
