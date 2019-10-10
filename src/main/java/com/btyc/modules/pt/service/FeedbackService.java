package com.btyc.modules.pt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.pt.entity.FeedbackEntity;

import java.util.Map;

/**
 *
 *
 * @author ysq
 * @date 2019-08-22 19:15:53
 */
public interface FeedbackService extends IService<FeedbackEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

