package com.maizhong.platform.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xushd on 2017/10/9.
 */
public class VersionDto implements Serializable {

    private Long id;
    private String title;
    private String time;
    private String author;
    private String version;
    private List<VersionDto> items;

    public VersionDto() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<VersionDto> getItems() {
        return items;
    }

    public void setItems(List<VersionDto> items) {
        this.items = items;
    }
}
