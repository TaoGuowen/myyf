package com.btyc.modules.product.dao;

import com.btyc.modules.product.entity.SpuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 产品 本表记录产品信息：如防护服1
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Mapper
public interface SpuDao extends BaseMapper<SpuEntity> {

    List<Map<String,Object>> spuAll();

}
