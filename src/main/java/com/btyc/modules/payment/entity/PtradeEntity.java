package com.btyc.modules.payment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付流水
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:57
 */
@Data
@TableName("PAYMENT_PTRADE")
public class PtradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 金额
	 */
	private BigDecimal cash;
	/**
	 * 交易创建时间
	 */
	private Date cjtime;
	/**
	 * 状态：01：init,  02:成功, 09:失败,
	 */
	private String statusCode;
	/**
	 * 支付成功记录id
	 */
	private String payid;

}
