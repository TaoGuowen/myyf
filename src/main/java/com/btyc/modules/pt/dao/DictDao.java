package com.btyc.modules.pt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.btyc.modules.pt.entity.DictEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 字典
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Mapper
public interface DictDao extends BaseMapper<DictEntity> {
    /**查询卡类型
     * @return
     */
    List<HashMap> selectPt();
}
