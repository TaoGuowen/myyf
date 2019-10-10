package com.btyc.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:59
 */
@Data
@TableName("PRODUCT_SKU")
public class SkuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 产品ID
	 */
	private String spuId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 规格 该字段用于记录该商品的所有属性名+属性值，如“颜色：red，尺码：xxl”，等，
            重要字段或共有字段单独成为字段（如尺码） 用于快速解析
	 */
	private String specs;
	/**
	 * 检查项目
	 */
	private String checkCode;
	/**
	 * 颜色
	 */
	private String colorCode;
	/**
	 * 尺码
	 */
	private String sizeCode;
	/**
	 * 尺码名称
	 */
	@TableField(exist = false)
	private String sizeName;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 原始价
	 */
	private BigDecimal oprice;
	/**
	 * 库存
	 */
	private Long amount;
	/**
	 * 排序号
	 */
	private Integer sortNo;
	/**
	 * 描述
	 */
	private String memo;

}
