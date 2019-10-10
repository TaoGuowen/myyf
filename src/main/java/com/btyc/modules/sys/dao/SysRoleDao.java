package com.btyc.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.btyc.modules.sys.entity.SysRoleEntity;
import com.btyc.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 *
 * @author ams
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
