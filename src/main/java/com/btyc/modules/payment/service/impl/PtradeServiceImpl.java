package com.btyc.modules.payment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.payment.dao.PtradeDao;
import com.btyc.modules.payment.entity.PtradeEntity;
import com.btyc.modules.payment.service.PtradeService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("ptradeService")
public class PtradeServiceImpl extends ServiceImpl<PtradeDao, PtradeEntity> implements PtradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PtradeEntity> page = this.page(
                new Query<PtradeEntity>().getPage(params),
                new QueryWrapper<PtradeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public String createPtrade(PtradeEntity ptradeEntity) {
        return baseMapper.createPtrade(ptradeEntity);
    }

}
