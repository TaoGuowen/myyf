package com.btyc.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.product.entity.SkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:59
 */
public interface SkuService extends IService<SkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuEntity> selectListByCheckItem(String checkCode);
}

