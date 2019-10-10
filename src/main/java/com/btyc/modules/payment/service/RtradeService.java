package com.btyc.modules.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.modules.payment.entity.RtradeEntity;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.payment.entity.RtradeEntity;

import java.util.Map;

/**
 * 退款流水
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:57
 */
public interface RtradeService extends IService<RtradeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

