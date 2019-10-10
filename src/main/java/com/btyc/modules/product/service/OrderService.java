package com.btyc.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.product.entity.OrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<OrderEntity> selectListByUserId(Integer userId);

    String createOrder(OrderEntity order);

}

