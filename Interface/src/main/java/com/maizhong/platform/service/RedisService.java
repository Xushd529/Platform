package com.maizhong.platform.service;

import com.maizhong.platform.dto.NavsDto;
import com.maizhong.platform.pojo.MUser;

import java.util.List;

/**
 * Created by Xushd on 2017/10/9.
 */
public interface RedisService {
    String getManageTokenByPhone(String phone);

    void createManageLoginUser(String token, MUser mUser);

    void createManageMenu(String token,List<NavsDto> manageMenu);

    List<NavsDto> getManageMenu(String token);

    MUser getManageUserByToken(String token);

    void ManageUserLogout(String token);
}
