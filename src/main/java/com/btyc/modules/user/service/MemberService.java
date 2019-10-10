package com.btyc.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.user.entity.MemberEntity;

import java.util.List;
import java.util.Map;

/**
 * 家庭成员
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MemberEntity> selectListByUserId(Integer userId);

    Integer addMember(MemberEntity memberEntity);
}

