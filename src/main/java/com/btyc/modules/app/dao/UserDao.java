package com.btyc.modules.app.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.btyc.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户
 *
 * @author ams
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
    Long register(UserEntity user);

    IPage<UserEntity> selectUserList(IPage<UserEntity> page, @Param("ew") Wrapper<UserEntity> var2);
}
