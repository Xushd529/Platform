package com.maizhong.platform.service;

import com.maizhong.common.result.JsonResult;

/**
 * 登录控制接口
 * Created by Xushd on 2017/10/9.
 */
public interface LoginService {

    JsonResult manageLogin(String phone,String pass);

    JsonResult manageLogout(String token);
}
