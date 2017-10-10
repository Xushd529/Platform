package com.maizhong.platform.dto;

import java.io.Serializable;

/**
 * Created by Xushd on 2017/10/9.
 */
public class SystemDto implements Serializable{

    //Java的运行环境版本
    private String javaVersion;
    //Java的运行环境供应商
    private String javaVendor;
    //操作系统名称
    private String osName;
    //操作系统架构
    private String osArch;
    //操作系统版本
    private String osVersion;
    //当前版本
    private String curVersion;
    //数据库版本
    private String mysqlVersion;

    public SystemDto() {
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getJavaVendor() {
        return javaVendor;
    }

    public void setJavaVendor(String javaVendor) {
        this.javaVendor = javaVendor;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getCurVersion() {
        return curVersion;
    }

    public void setCurVersion(String curVersion) {
        this.curVersion = curVersion;
    }

    public String getMysqlVersion() {
        return mysqlVersion;
    }

    public void setMysqlVersion(String mysqlVersion) {
        this.mysqlVersion = mysqlVersion;
    }
}
