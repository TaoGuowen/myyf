package com.btyc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.modules.sys.entity.SysUserTokenEntity;
import com.btyc.common.utils.R;
import com.btyc.common.utils.R;
import com.btyc.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 *
 * @author ams
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
