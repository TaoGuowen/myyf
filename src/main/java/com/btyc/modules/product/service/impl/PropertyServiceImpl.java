package com.btyc.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.product.dao.PropertyDao;
import com.btyc.modules.product.entity.PropertyEntity;
import com.btyc.modules.product.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("propertyService")
public class PropertyServiceImpl extends ServiceImpl<PropertyDao, PropertyEntity> implements PropertyService {

    @Autowired
    PropertyDao propertyDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PropertyEntity> page = this.page(
                new Query<PropertyEntity>().getPage(params),
                new QueryWrapper<PropertyEntity>().like("name",params.get("key"))
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> propertyAll() {
        return propertyDao.propertyAll();
    }

}
