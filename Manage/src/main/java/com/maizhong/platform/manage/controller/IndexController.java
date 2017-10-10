package com.maizhong.platform.manage.controller;

import com.maizhong.common.result.JsonResult;
import com.maizhong.platform.dto.NavsDto;
import com.maizhong.platform.dto.SystemDto;
import com.maizhong.platform.dto.VersionDto;
import com.maizhong.platform.pojo.MUser;
import com.maizhong.platform.service.MenuService;
import com.maizhong.platform.service.RedisService;
import com.maizhong.platform.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Properties;

/**
 * Created by Xushd on 2017/9/28.
 */
@Controller
public class IndexController extends BaseController{


    @Autowired
    private RedisService redisService;
    @Autowired
    private VersionService versionService;

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, Model model){
        String token = super.getToken(request);
        MUser mUser = redisService.getManageUserByToken(token);
        model.addAttribute("userInfo",mUser);
        return "index";
    }

    /**
     * 欢迎页
     * @param model
     * @return
     */
    @RequestMapping(value = "/welcome")
    public String welcome(Model model){
        Properties properties = System.getProperties();

        SystemDto dto = new SystemDto();
        dto.setJavaVersion(properties.getProperty("java.version"));
        dto.setJavaVendor(properties.getProperty("java.vendor"));
        dto.setOsName(properties.getProperty("os.name"));
        dto.setOsArch(properties.getProperty("os.arch"));
        dto.setOsVersion(properties.getProperty("os.version"));
        dto.setCurVersion("v0.0.1");
        dto.setMysqlVersion("v5.6.21");
        model.addAttribute("system",dto);

        List<VersionDto> versionLog = versionService.getVersionLog();
        model.addAttribute("versionLog",versionLog);

        return "welcome";
    }

    @RequestMapping(value = "/400")
    public String index400(){

        return "400";
    }

    @RequestMapping(value = "/404")
    public String index404(){

        return "404";
    }
    @RequestMapping(value = "/500")
    public String index500(){

        return "500";
    }

    /**
     * 获取登录用户 菜单
     * @return
     */
    @RequestMapping(value = "/admin/user/navs",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getUserMenuJson(HttpServletRequest request){
        String token = super.getToken(request);
        List<NavsDto> manageMenu = redisService.getManageMenu(token);
        return JsonResult.OK(manageMenu);
    }
}
