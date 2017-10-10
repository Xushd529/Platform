package com.maizhong.platform.mapper;

import com.maizhong.platform.pojo.CarModel;
import com.maizhong.platform.pojo.CarModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarModelMapper {
    long countByExample(CarModelExample example);

    int deleteByExample(CarModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CarModel record);

    int insertSelective(CarModel record);

    List<CarModel> selectByExample(CarModelExample example);

    CarModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CarModel record, @Param("example") CarModelExample example);

    int updateByExample(@Param("record") CarModel record, @Param("example") CarModelExample example);

    int updateByPrimaryKeySelective(CarModel record);

    int updateByPrimaryKey(CarModel record);
}