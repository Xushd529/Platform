package com.maizhong.platform.manage.controller;

import com.maizhong.common.enums.OperateEnum;
import com.maizhong.common.result.JsonResult;
import com.maizhong.common.utils.VerifyCodeUtils;
import com.maizhong.platform.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录控制器
 * Created by Xushd on 2017/9/30.
 */
@Controller
public class LoginController extends BaseController{

    @Autowired
    private LoginService loginService;

    /**
     * 登录页
     * @return
     */
    @RequestMapping(value = "/login")
    public String loginIndex(){

        return "login";
    }

    /**
     * Manage 登录验证
     * @param account
     * @param pass
     * @param code
     * @param vercode
     * @return
     */
    @RequestMapping(value = "/admin/login/check")
    @ResponseBody
    public JsonResult loginCheck(String account,String pass,String code,
                                 @CookieValue(value = "vercode",required = false) String vercode){
        if(StringUtils.isBlank(vercode)){
            return JsonResult.Error(OperateEnum.VERCODE_OUT);
        }
        if(!StringUtils.equals(code.toLowerCase(),vercode)){
            return JsonResult.Error(OperateEnum.VERCODE_ERROR);
        }

        return loginService.manageLogin(account,pass);
    }

    /**
     * Manage 退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/logout")
    @ResponseBody
    public JsonResult logout(HttpServletRequest request){
        String token = super.getToken(request);
        return loginService.manageLogout(token);
    }
    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/verifyCode",method = RequestMethod.GET)
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session

        Cookie cookie = new Cookie("vercode", verifyCode.toLowerCase());
        cookie.setMaxAge(60);// 设置为60min*24
        cookie.setPath("/");
        response.addCookie(cookie);
        //生成图片
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
}
