package com.btyc.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户卡信息
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:56
 */
@Data
@TableName("USER_CARD_INFO")
public class InfoEntity implements Serializable {
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
	 * 订单ID
	 */
	private String orderNo;
	/**
	 * 卡类型
	 */
	private String cardType;
	/**
	 * 卡名称
	 */
	private String cardName;
	/**
	 * 总次数
	 */
	private Integer totalTimes;
	/**
	 * 剩余次数
	 */
	private Integer remaindTimes;
	/**
	 * 开始日期
	 */
	private String beginDay;
	/**
	 * 截至日期
	 */
	private String endDay;
	/**
	 * 购买日期
	 */
	private String buyDay;
	/**
	 * 购买价格
	 */
	private BigDecimal buyPrice;
	/**
	 * 是否有效
	 */
	private String statusCode;

}
