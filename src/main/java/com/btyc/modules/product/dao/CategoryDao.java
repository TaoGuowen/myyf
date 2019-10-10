package com.btyc.modules.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.btyc.modules.product.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

    List<Map<String,Object>> selectAll();

    List<CategoryEntity> selectBy();

    /** 根据Id查询信息
     * @return
     */
    HashMap selectInfoById(@Param("id") int id);

    /**
     * 根据父产品，查询子产品
     * @param
     */
    List<CategoryEntity> queryListPId(@Param("pid") Long pid);

}
