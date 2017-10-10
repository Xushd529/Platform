package com.maizhong.platform.manage.controller;

import com.maizhong.common.dto.PageSearchParam;
import com.maizhong.common.result.JsonResult;
import com.maizhong.platform.pojo.MUser;
import com.maizhong.platform.service.MUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Xushd on 2017/10/10.
 */
@Controller
public class SystemController extends BaseController {

    @Autowired
    private MUserService mUserService;

    @RequestMapping(value = "/admin/user")
    public String manageUserIndex(){

        return "system/muser";
    }

    /**
     * Manage 用户数据列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/admin/user/list")
    @ResponseBody
    public JsonResult manageUserList(PageSearchParam param){
        return mUserService.getManageUserList(param);
    }

    /**
     * Manage 用户新增/修改
     * @param mUser
     * @param result
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/user/handle")
    @ResponseBody
    public JsonResult manageUserHandle(@Valid MUser mUser, BindingResult result, HttpServletRequest request){

        String token = super.getToken(request);

        String errors = super.getErrors(result);
        if(StringUtils.isNotBlank(errors)){
            return JsonResult.Error(errors);
        }
        return mUserService.handleManageUser(mUser,token);
    }

    /**
     * Manage 用户状态修改
     * @param id
     * @param status
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/user/status/{id}/{status}")
    @ResponseBody
    public JsonResult manageUserStatus(@PathVariable long id, @PathVariable int status, HttpServletRequest request){
        String token = super.getToken(request);
        return mUserService.statusManageUser(id,status,token);
    }

    /**
     * Manage 用户删除
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/user/del/{id}")
    @ResponseBody
    public JsonResult manageUserDel(@PathVariable long id,HttpServletRequest request){
        String token = super.getToken(request);
        return mUserService.delManageUser(id,token);
    }
}
