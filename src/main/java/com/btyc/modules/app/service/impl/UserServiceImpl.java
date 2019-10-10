package com.btyc.modules.app.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btyc.common.exception.RRException;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.Query;
import com.btyc.modules.app.dao.UserDao;
import com.btyc.modules.app.entity.UserEntity;
import com.btyc.modules.app.form.LoginForm;
import com.btyc.modules.app.service.UserService;
import com.btyc.modules.app.utils.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
	@Autowired
	private WxUtils wxUtils;

	@Override
	public UserEntity queryByMobile(String mobile) {
		return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
	}

	public UserEntity queryByOpenId(String openid) {
		return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("openid", openid));
	}

	@Override
	public Long login(LoginForm form) {
		String openId = wxUtils.getOpenId(form.getCode());
		if(openId==null){
			throw new RRException("登录失败，非法的code值");
		}
		UserEntity user = queryByOpenId(openId);
		//如果未登录，先注册
		if(user==null){
			UserEntity newuser = new UserEntity();
			//user.setMobile(form.getMobile().toString());
			newuser.setOpenid(openId);
			newuser.setCreateTime(new Date());
			return baseMapper.register(newuser);
		}else {
			return user.getUserId();
		}
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params) {

		QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();

		if(params.containsKey("key") && !params.get("key").toString().equals("")){
				queryWrapper.like("username",params.get("key"));
		}

		IPage<UserEntity> page = this.page(
				new Query<UserEntity>().getPage(params),queryWrapper
		);

		return new PageUtils(page);
	}

	@Override
	public Long register(UserEntity user) {
		return baseMapper.register(user);
	}

	@Override
	public PageUtils selectUserList(Map<String, Object> params) {
		QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();

		if(params.containsKey("key") && !params.get("key").toString().equals("")){
			queryWrapper.like("username",params.get("key"));
		}
		IPage<UserEntity> page = this.page(
				new Query<UserEntity>().getPage(params),queryWrapper
		);

		return new PageUtils(page);
	}

	public IPage<UserEntity> page(IPage<UserEntity> page, Wrapper<UserEntity> queryWrapper) {
		return this.baseMapper.selectUserList(page, queryWrapper);
	}

}
