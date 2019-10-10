package com.btyc.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.product.dao.OrderDao;
import com.btyc.modules.product.entity.OrderEntity;
import com.btyc.modules.product.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<OrderEntity> selectListByUserId(Integer userId) {
        return baseMapper.selectList(new QueryWrapper<OrderEntity>().eq("USER_ID", userId));
    }

    @Override
    public String createOrder(OrderEntity order) {
        return baseMapper.createOrder(order);
    }

}
