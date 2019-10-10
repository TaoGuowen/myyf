package com.btyc.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.user.dao.MemberDao;
import com.btyc.modules.user.entity.MemberEntity;
import com.btyc.modules.user.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<MemberEntity> selectListByUserId(Integer userId) {
        return baseMapper.selectList(new QueryWrapper<MemberEntity>().eq("USER_ID", userId));
    }

    @Override
    public Integer addMember(MemberEntity memberEntity) {
        return baseMapper.addMember(memberEntity);
    }

}
