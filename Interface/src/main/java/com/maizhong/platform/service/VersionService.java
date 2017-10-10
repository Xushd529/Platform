package com.maizhong.platform.service;

import com.maizhong.platform.dto.VersionDto;

import java.util.List;

/**
 * Created by Xushd on 2017/10/9.
 */
public interface VersionService {

    List<VersionDto> getVersionLog();
}
