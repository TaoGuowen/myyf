package com.btyc.modules.product.dao;

import com.btyc.modules.product.entity.PropertyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 产品属性
本表记录产品属性：
1、color、颜色、varchar(16)
2、si
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:59
 */
@Mapper
public interface PropertyDao extends BaseMapper<PropertyEntity> {

    List<Map<String,Object>> propertyAll();

}
