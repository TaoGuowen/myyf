package com.btyc.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Data
@TableName("PRODUCT_ORDER")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String no;
	/**
	 * 卡号
	 */
	private String number;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 产品ID
	 */
	private String spuId;
	/**
	 * 商品ID
	 */
	private String skuId;
	/**
	 * 产品名称
	 */
	private String spuName;
	/**
	 * 商品名称
	 */
	private String skuName;
	/**
	 * 订单类型： 01：产品类；02：卡片类
     注：因卡片类订单在处理逻辑上比产品类订单多1个环节：支付成功后需要生成用户卡片信息，故此处加以区分
	 */
	private String typeCode;
	/**
	 * 码 用于生成二维码或条码等，
	 */
	private String code;
	/**
	 * 金额
	 */
	private BigDecimal money;
	/**
	 * 支付类型： 01：卡支付，02：直接支付
	 */
	private String payType;
	/**
	 * 卡片ID
	 */
	private String cardInfoId;
	/**
	 * 联系人
	 */
	private String name;
	/**
	 * 联系方式
	 */
	private String mobile;
	/**
	 * 创建日期
	 */
	private String createTime;
	/**
	 * 状态 01:待支付
            02:已支付
            06:已退款
            09:已删除
	 */
	private String statusCode;
	/**
	 * 支付流水ID 交易流水完成后 回填
	 */
	private String tradeId;
	/**
	 * 支付方式 交易流水完成后 回填
            支付方式，参见T_PAYMENT_METHOD
	 */
	private String payMethod;

}
