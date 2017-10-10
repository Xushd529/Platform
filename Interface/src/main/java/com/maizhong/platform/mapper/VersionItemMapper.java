package com.maizhong.platform.mapper;

import com.maizhong.platform.pojo.VersionItem;
import com.maizhong.platform.pojo.VersionItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VersionItemMapper {
    long countByExample(VersionItemExample example);

    int deleteByExample(VersionItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VersionItem record);

    int insertSelective(VersionItem record);

    List<VersionItem> selectByExample(VersionItemExample example);

    VersionItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VersionItem record, @Param("example") VersionItemExample example);

    int updateByExample(@Param("record") VersionItem record, @Param("example") VersionItemExample example);

    int updateByPrimaryKeySelective(VersionItem record);

    int updateByPrimaryKey(VersionItem record);
}