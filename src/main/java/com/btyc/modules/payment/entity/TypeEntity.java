package com.btyc.modules.payment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付方式
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:58
 */
@Data
@TableName("PAYMENT_TYPE")
public class TypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 *
	 */
	private String code;
	/**
	 *
	 */
	private String name;
	/**
	 *
	 */
	private String styleClass;
	/**
	 *
	 */
	private String payUri;
	/**
	 *
	 */
	private Integer sortNo;

}
