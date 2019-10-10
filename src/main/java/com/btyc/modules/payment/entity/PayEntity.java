package com.btyc.modules.payment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:58
 */
@Data
@TableName("PAYMENT_PAY")
public class PayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 支付号
	 */
	private String no;
	/**
	 * 支付交易id
	 */
	private String ptradeId;
	/**
	 * 支付方式（01：微信， 02:支付宝，...）
	 */
	private String payMethod;
	/**
	 * 支付请求时间
	 */
	private Date reqTime;
	/**
	 * 支付响应时间
	 */
	private Date resTime;
	/**
	 * 支付响应信息
	 */
	private String resMsg;
	/**
	 * 第三方交易号，部分第三方退款时需要
	 */
	private String resno;
	/**
	 * 状态：01：init,  02:成功, 09:失败,
	 */
	private String statusCode;

}
