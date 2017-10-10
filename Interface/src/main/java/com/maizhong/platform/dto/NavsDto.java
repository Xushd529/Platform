package com.maizhong.platform.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Navs
 * Created by Xushd on 2017/10/9.
 */
public class NavsDto implements Serializable{

    private String title;
    private String icon;
    private String href;
    private boolean spread;
    private List<NavsDto> children;

    public NavsDto() {
    }


    public List<NavsDto> getChildren() {
        return children;
    }

    public void setChildren(List<NavsDto> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }
}
