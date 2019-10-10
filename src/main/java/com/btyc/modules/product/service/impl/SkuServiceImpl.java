package com.btyc.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.product.dao.SkuDao;
import com.btyc.modules.product.entity.SkuEntity;
import com.btyc.modules.product.service.SkuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("skuService")
public class SkuServiceImpl extends ServiceImpl<SkuDao, SkuEntity> implements SkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuEntity> page = this.page(
                new Query<SkuEntity>().getPage(params),
                new QueryWrapper<SkuEntity>().like("name",params.get("key")).orderByAsc("CHECK_CODE").orderByAsc("SORT_NO")
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuEntity> selectListByCheckItem(String checkCode) {
        return baseMapper.selectList(new QueryWrapper<SkuEntity>().eq("CHECK_CODE", checkCode));
    }

}
