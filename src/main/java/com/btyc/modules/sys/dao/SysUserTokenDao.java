package com.btyc.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.btyc.modules.sys.entity.SysUserTokenEntity;
import com.btyc.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author ams
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);

}
