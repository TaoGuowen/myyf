package com.btyc.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.product.dao.SpuDao;
import com.btyc.modules.product.entity.SpuEntity;
import com.btyc.modules.product.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("spuService")
public class SpuServiceImpl extends ServiceImpl<SpuDao, SpuEntity> implements SpuService {

    @Autowired
    SpuDao spuDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuEntity> page = this.page(
                new Query<SpuEntity>().getPage(params),
                new QueryWrapper<SpuEntity>().like("name",params.get("key"))
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> spuAll() {
        return spuDao.spuAll();
    }

}
