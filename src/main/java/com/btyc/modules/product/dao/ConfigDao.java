package com.btyc.modules.product.dao;

import com.btyc.modules.product.entity.ConfigEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 身高尺码配置表
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:59
 */
@Mapper
public interface ConfigDao extends BaseMapper<ConfigEntity> {
    List<Map<String,Object>> configAll();


}
