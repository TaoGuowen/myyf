package com.btyc.modules.user.dao;

import com.btyc.modules.user.entity.InfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.Map;

/**
 * 用户卡信息
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:56
 */
@Mapper
public interface InfoDao extends BaseMapper<InfoEntity> {

    Date queryMaxDay(Map map);
}
