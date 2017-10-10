package com.maizhong.platform.manage.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Xushd on 2017/10/9.
 */
public class BaseController {

    /**
     * 获取请求的
     * @param request
     * @return
     */
    protected String getToken(HttpServletRequest request){
        return (String) request.getAttribute("token");
    }

    /**
     * 获取错误信息
     * @param result
     * @return
     */
    protected String getErrors(BindingResult result){
        String error = "";
        List<ObjectError> allErrors = result.getAllErrors();
        for (ObjectError allError : allErrors) {
            error+= allError.getDefaultMessage();
        }
        return error;
    }
}
