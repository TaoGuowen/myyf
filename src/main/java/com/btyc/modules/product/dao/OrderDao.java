package com.btyc.modules.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.btyc.modules.product.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 订单
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

    String createOrder(OrderEntity order);

    /**
     * 查询订单信息
     * @param userId
     * @return
     */
    List<Map> queryOrders(Integer userId);

}
