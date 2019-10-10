package com.btyc.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.modules.product.entity.SpuEntity;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.product.entity.SpuEntity;

import java.util.List;
import java.util.Map;

/**
 * 产品 本表记录产品信息：如防护服1
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
public interface SpuService extends IService<SpuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> spuAll();
}

