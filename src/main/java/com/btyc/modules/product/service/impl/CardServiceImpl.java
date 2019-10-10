package com.btyc.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.product.dao.CardDao;
import com.btyc.modules.product.entity.CardEntity;
import com.btyc.modules.product.service.CardService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("cardService")
public class CardServiceImpl extends ServiceImpl<CardDao, CardEntity> implements CardService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CardEntity> page = this.page(
                new Query<CardEntity>().getPage(params),
                new QueryWrapper<CardEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<HashMap> selectCa(String name) {
        return baseMapper.selectCa(name);
    }

    @Override
    public List<HashMap> selectCaById(String id) {
        return baseMapper.selectCaById(id);
    }


}
