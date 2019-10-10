package com.btyc.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.product.entity.CategoryEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本表记录产品分类信息
  如：
    一级：女装/内衣

 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> selectAll();

    List<CategoryEntity> selectBy();

    /** 根据Id查询信息
     * @return
     */
    HashMap selectInfoById(int id);

    /**
     * 根据父菜单，查询子菜单
     * @param pid 父菜单ID
     */
    List<CategoryEntity> queryListPId(Long pid);

    /**
     * 删除
     */
    void delete(Long menuId);
}

