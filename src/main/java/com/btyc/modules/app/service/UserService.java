package com.btyc.modules.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.app.entity.UserEntity;
import com.btyc.modules.app.form.LoginForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author ams
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回用户ID
	 */
	Long login(LoginForm form);

	PageUtils queryPage(Map<String, Object> params);

	Long register(UserEntity user);

	public PageUtils selectUserList(Map<String, Object> params);

}
