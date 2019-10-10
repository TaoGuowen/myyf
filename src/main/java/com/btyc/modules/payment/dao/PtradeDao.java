package com.btyc.modules.payment.dao;

import com.btyc.modules.payment.entity.PtradeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付流水
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:57
 */
@Mapper
public interface PtradeDao extends BaseMapper<PtradeEntity> {

    String createPtrade(PtradeEntity ptradeEntity);
}
