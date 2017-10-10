package com.maizhong.platform.service;

import com.maizhong.common.dto.PageSearchParam;
import com.maizhong.common.result.JsonResult;
import com.maizhong.platform.pojo.MUser;

/**
 * Created by Xushd on 2017/10/10.
 */
public interface MUserService {

    JsonResult getManageUserList(PageSearchParam param);

    JsonResult handleManageUser(MUser mUser, String token);

    JsonResult statusManageUser(long id, int status, String token);

    JsonResult delManageUser(long id, String token);
}
