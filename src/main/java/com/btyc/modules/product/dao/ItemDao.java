package com.btyc.modules.product.dao;

import com.btyc.modules.product.entity.ItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 检测项目
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Mapper
public interface ItemDao extends BaseMapper<ItemEntity> {

    List<Map<String,Object>> itemAll();

}
