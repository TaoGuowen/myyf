package com.btyc.modules.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.modules.payment.entity.PayEntity;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.payment.entity.PayEntity;

import java.util.Map;

/**
 * 支付
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:58
 */
public interface PayService extends IService<PayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

