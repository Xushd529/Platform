package com.maizhong.platform.manage.interceptor;

import com.maizhong.common.result.JsonResult;
import com.maizhong.common.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Xushd on 2017/9/28.
 */
public class WebInterceptor extends HandlerInterceptorAdapter {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        StringBuffer requestURL = request.getRequestURL();
        if(StringUtils.contains(String.valueOf(requestURL),"/login")
                || StringUtils.contains(String.valueOf(requestURL),"/admin/login/check")
                || StringUtils.contains(String.valueOf(requestURL),"/verifyCode")
                || StringUtils.contains(String.valueOf(requestURL),"/resources")
                || StringUtils.contains(String.valueOf(requestURL),"/favicon.ico")){
            return true;
        }
        String token = "nologin";
        String requestType = request.getHeader("X-Requested-With");
        if(StringUtils.isBlank(requestType)){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("manage_token")){
                    token = cookie.getValue();
                    break;
                }
            }
            if (!StringUtils.equals("nologin",token)) {
                request.setAttribute("token",token);
                return true;
            }
            request.getRequestDispatcher("/login").forward(request, response);
        }else{
            //ajax
            token = request.getHeader("platform-manage-token");
            if (!StringUtils.equals("nologin",token)) {
                request.setAttribute("token",token);
                return true;
            }
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtils.objectToJson(JsonResult.build(403,"LOGIN TIME OUT","")));
            writer.flush();
        }
        return false;
    }
}