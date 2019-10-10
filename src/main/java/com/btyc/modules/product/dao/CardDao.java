package com.btyc.modules.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.btyc.modules.product.entity.CardEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 卡
 *
 * @author tsy
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Mapper
public interface CardDao extends BaseMapper<CardEntity> {

     List<HashMap> selectCa(@Param("name") String name);

     List<HashMap> selectCaById(@Param("id") String id);

}
