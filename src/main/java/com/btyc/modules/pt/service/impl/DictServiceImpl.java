package com.btyc.modules.pt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.pt.dao.DictDao;
import com.btyc.modules.pt.entity.DictEntity;
import com.btyc.modules.pt.service.DictService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictDao, DictEntity> implements DictService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictEntity> page = this.page(
                new Query<DictEntity>().getPage(params),
                new QueryWrapper<DictEntity>().like("CODE_NAME",params.get("key"))
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils select(Map<String, Object> params) {
        IPage<DictEntity> page = this.page(
                new Query<DictEntity>().getPage(params),
                new QueryWrapper<DictEntity>().like("CODE_VALUE",params.get("key"))
        );
        return new PageUtils(page);
    }

    @Override
    public List<HashMap> selectPt() {
        return baseMapper.selectPt();
    }

    @Override
    public List<DictEntity> selectListByBusiType(String busiType) {
        return baseMapper.selectList(new QueryWrapper<DictEntity>().eq("BUSI_TYPE", busiType));
    }

}
