package com.btyc.modules.pt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.modules.pt.entity.KnowledgeEntity;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.pt.entity.KnowledgeEntity;

import java.util.Map;

/**
 * 知识
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:58
 */
public interface KnowledgeService extends IService<KnowledgeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

