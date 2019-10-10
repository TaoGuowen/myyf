package com.btyc.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.user.dao.InfoDao;
import com.btyc.modules.user.entity.InfoEntity;
import com.btyc.modules.user.service.InfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("infoService")
public class InfoServiceImpl extends ServiceImpl<InfoDao, InfoEntity> implements InfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InfoEntity> page = this.page(
                new Query<InfoEntity>().getPage(params),
                new QueryWrapper<InfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<InfoEntity> selectListByUserId(Integer userId) {
        return baseMapper.selectList(new QueryWrapper<InfoEntity>().eq("USER_ID", userId));
    }

    @Override
    public Date queryMaxDay(Map map) {
        return baseMapper.queryMaxDay(map);
    }

}
