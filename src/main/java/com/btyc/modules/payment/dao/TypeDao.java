package com.btyc.modules.payment.dao;

import com.btyc.modules.payment.entity.TypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付方式
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:58
 */
@Mapper
public interface TypeDao extends BaseMapper<TypeEntity> {

}
