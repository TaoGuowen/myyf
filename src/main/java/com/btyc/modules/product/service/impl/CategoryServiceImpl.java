package com.btyc.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.product.dao.CategoryDao;
import com.btyc.modules.product.entity.CategoryEntity;
import com.btyc.modules.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Autowired
    CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>().like("name",params.get("key"))
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectAll() {

        return categoryDao.selectAll();
    }

    @Override
    public List<CategoryEntity> selectBy() {
        return baseMapper.selectBy();
    }

    @Override
    public HashMap selectInfoById(int id) {
        return baseMapper.selectInfoById(id);
    }

    @Override
    public List<CategoryEntity> queryListPId(Long pid) {
        return baseMapper.queryListPId(pid);
    }

    @Override
    public void delete(Long menuId) {
        //删除产品
        this.removeById(menuId);
    }

}
