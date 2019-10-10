package com.btyc.modules.pt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.pt.dao.FeedbackDao;
import com.btyc.modules.pt.entity.FeedbackEntity;
import com.btyc.modules.pt.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("feedbackService")
public class FeedbackServiceImpl extends ServiceImpl<FeedbackDao, FeedbackEntity> implements FeedbackService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FeedbackEntity> page = this.page(
                new Query<FeedbackEntity>().getPage(params),
                new QueryWrapper<FeedbackEntity>()
        );

        return new PageUtils(page);
    }

}
