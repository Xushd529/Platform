package com.maizhong.platform.mapper;

import com.maizhong.platform.pojo.MNavs;
import com.maizhong.platform.pojo.MNavsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MNavsMapper {
    long countByExample(MNavsExample example);

    int deleteByExample(MNavsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MNavs record);

    int insertSelective(MNavs record);

    List<MNavs> selectByExample(MNavsExample example);

    MNavs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MNavs record, @Param("example") MNavsExample example);

    int updateByExample(@Param("record") MNavs record, @Param("example") MNavsExample example);

    int updateByPrimaryKeySelective(MNavs record);

    int updateByPrimaryKey(MNavs record);
}