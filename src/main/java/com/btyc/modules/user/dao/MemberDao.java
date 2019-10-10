package com.btyc.modules.user.dao;

import com.btyc.modules.user.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 家庭成员
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {

    Integer addMember(MemberEntity memberEntity);

}
