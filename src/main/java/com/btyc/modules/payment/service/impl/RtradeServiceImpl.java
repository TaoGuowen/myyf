package com.btyc.modules.payment.service.impl;

import com.btyc.modules.payment.dao.RtradeDao;
import com.btyc.modules.payment.entity.RtradeEntity;
import com.btyc.modules.payment.service.RtradeService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;

import com.btyc.modules.payment.dao.RtradeDao;
import com.btyc.modules.payment.entity.RtradeEntity;
import com.btyc.modules.payment.service.RtradeService;


@Service("rtradeService")
public class RtradeServiceImpl extends ServiceImpl<RtradeDao, RtradeEntity> implements RtradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RtradeEntity> page = this.page(
                new Query<RtradeEntity>().getPage(params),
                new QueryWrapper<RtradeEntity>()
        );

        return new PageUtils(page);
    }

}
