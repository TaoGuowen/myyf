package com.btyc.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 家庭成员
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Data
@TableName("USER_MEMBER")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 出生年月
	 */
	private String birthDay;
	/**
	 * 体重
	 */
	private Integer weight;
	/**
	 * 身高
	 */
	private Integer height;
	/**
	 * 关系
	 */
	private String relation;

	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 是否自己
	 */
	private String isSelf;

}
