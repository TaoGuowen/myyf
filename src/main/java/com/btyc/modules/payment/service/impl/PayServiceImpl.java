package com.btyc.modules.payment.service.impl;

import com.btyc.modules.payment.dao.PayDao;
import com.btyc.modules.payment.entity.PayEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;

import com.btyc.modules.payment.dao.PayDao;
import com.btyc.modules.payment.entity.PayEntity;
import com.btyc.modules.payment.service.PayService;


@Service("payService")
public class PayServiceImpl extends ServiceImpl<PayDao, PayEntity> implements PayService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PayEntity> page = this.page(
                new Query<PayEntity>().getPage(params),
                new QueryWrapper<PayEntity>()
        );

        return new PageUtils(page);
    }

}
