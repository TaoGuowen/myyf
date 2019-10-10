package com.btyc.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.product.entity.CardEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卡
 *
 * @author tsy
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
public interface CardService extends IService<CardEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<HashMap> selectCa(String name);

    List<HashMap> selectCaById(String id);
}

