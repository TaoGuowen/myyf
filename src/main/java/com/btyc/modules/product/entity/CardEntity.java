package com.btyc.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 卡
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Data
@TableName("PRODUCT_CARD")
public class CardEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 卡类型 参见码表（01:次卡；02：月卡；03：活动卡）
	 */
	private String typeCode;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 次卡使用次数
            月卡使用次数为-1

	 */
	private Integer times;
	/**
	 * 次卡 截至日期
            月卡截至日期： 1979/1/1
	 */
	private Date endDate;
	/**
	 * 次卡有效期 为 -1
            月卡有效期 为真实有效期如30天
	 */
	private Integer ttl;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 原始价格
	 */
	private BigDecimal oprice;
	/**
	 * 最大购买次数 默认：-1（无限量）
	 */
	private Integer maxBuy;
	/**
	 * 库存 默认：-1（无限量）
	 */
	private Integer amount;
	/**
	 * 排序号
	 */
	private Integer sortNo;
	/**
	 * 描述
	 */
	private String memo;

}
