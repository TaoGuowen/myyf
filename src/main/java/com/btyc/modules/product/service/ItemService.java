package com.btyc.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.modules.product.entity.ItemEntity;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.product.entity.ItemEntity;

import java.util.List;
import java.util.Map;

/**
 * 检测项目
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
public interface ItemService extends IService<ItemEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> itemAll();
}

