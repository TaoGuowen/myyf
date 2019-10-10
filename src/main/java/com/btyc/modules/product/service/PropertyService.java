package com.btyc.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.modules.product.entity.PropertyEntity;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.product.entity.PropertyEntity;

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
public interface PropertyService extends IService<PropertyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> propertyAll();
}

