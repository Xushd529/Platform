package com.maizhong.platform.mapper;

import com.maizhong.platform.pojo.MUser;
import com.maizhong.platform.pojo.MUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MUserMapper {
    long countByExample(MUserExample example);

    int deleteByExample(MUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MUser record);

    int insertSelective(MUser record);

    List<MUser> selectByExample(MUserExample example);

    MUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MUser record, @Param("example") MUserExample example);

    int updateByExample(@Param("record") MUser record, @Param("example") MUserExample example);

    int updateByPrimaryKeySelective(MUser record);

    int updateByPrimaryKey(MUser record);
}