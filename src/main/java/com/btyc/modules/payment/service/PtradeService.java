package com.btyc.modules.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.payment.entity.PtradeEntity;

import java.util.Map;

/**
 * 支付流水
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:57
 */
public interface PtradeService extends IService<PtradeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    String createPtrade(PtradeEntity ptradeEntity);
}

