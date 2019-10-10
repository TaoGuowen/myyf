package com.btyc.modules.pt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.pt.dao.KnowledgeDao;
import com.btyc.modules.pt.entity.KnowledgeEntity;
import com.btyc.modules.pt.service.KnowledgeService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("knowledgeService")
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeDao, KnowledgeEntity> implements KnowledgeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<KnowledgeEntity> page = this.page(
                new Query<KnowledgeEntity>().getPage(params),
                new QueryWrapper<KnowledgeEntity>()
        );

        return new PageUtils(page);
    }

}
