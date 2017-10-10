package com.maizhong.platform.service.impl;

import com.maizhong.common.utils.TimeUtils;
import com.maizhong.platform.dto.VersionDto;
import com.maizhong.platform.mapper.VersionItemMapper;
import com.maizhong.platform.mapper.VersionMapper;
import com.maizhong.platform.pojo.Version;
import com.maizhong.platform.pojo.VersionExample;
import com.maizhong.platform.pojo.VersionItem;
import com.maizhong.platform.pojo.VersionItemExample;
import com.maizhong.platform.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xushd on 2017/10/9.
 */
public class VersionServiceImpl implements VersionService {

    @Autowired
    private VersionMapper versionMapper;
    @Autowired
    private VersionItemMapper versionItemMapper;

    /**
     * 获取版本日志
     * @return
     */
    @Override
    public List<VersionDto> getVersionLog() {

        List<VersionDto> list = new ArrayList<>();
        List<Version> versions = versionMapper.selectByExample(new VersionExample());
        VersionItemExample viEx = new VersionItemExample();
        for (Version version : versions) {
            VersionDto pDto = new VersionDto();
            pDto.setId(version.getId());
            pDto.setTitle(version.getTitle());
            pDto.setTime(TimeUtils.getFormatDateTime3(version.getUpdateTime()));
            pDto.setAuthor(version.getUpdateUser());
            pDto.setVersion(version.getVersion());
            viEx.clear();
            viEx.createCriteria().andVersionIdEqualTo(version.getId());
            List<VersionItem> versionItems = versionItemMapper.selectByExample(viEx);
            List<VersionDto> children = new ArrayList<>();
            for (VersionItem versionItem : versionItems) {
                VersionDto cDto = new VersionDto();
                cDto.setTitle(versionItem.getItem());
                cDto.setId(versionItem.getId());
                children.add(cDto);
            }
            pDto.setItems(children);
            list.add(pDto);
        }
        return list;
    }
}
