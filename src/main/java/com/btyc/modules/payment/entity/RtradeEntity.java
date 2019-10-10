package com.btyc.modules.payment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退款流水
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:57
 */
@Data
@TableName("PAYMENT_RTRADE")
public class RtradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 支付交易id
	 */
	private String ptradeid;
	/**
	 * 退款金额
	 */
	private BigDecimal cash;
	/**
	 * 退款交易创建时间
	 */
	private Date cjtime;
	/**
	 * 支付请求时间
	 */
	private Date reqtime;
	/**
	 * 支付响应时间
	 */
	private Date restime;
	/**
	 * 支付响应信息
	 */
	private String resmsg;
	/**
	 * 状态：01：init,  02:成功, 09:失败,
	 */
	private String statusCode;

}
