package com.hummingbird.demo.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.hummingbird.demo.entity.Weather;

public interface WeatherMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Weather record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Weather record);

    /**
     * 根据主键查询记录
     */
    Weather selectByPrimaryKey(Integer id);

    /**
     * 根据城市和日期查询记录
     */
    Weather selectByCityAndDate(Weather weather);
    
    
    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Weather record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Weather record);
}