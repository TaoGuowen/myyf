package com.btyc.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 产品 本表记录产品信息：如防护服1
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Data
@TableName("PRODUCT_SPU")
public class SpuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 产品分类ID
	 */
	private String categoryId;
	/**
	 * 产品名称
	 */
	private String name;
	/**
	 * 产品规格 该字段用于记录该商品的所有属性名 如“颜色，尺码，等，
            注：不记录属性值，属性值 商品表记录
            主要用于录入商品时 快速定位属性值

	 */
	private String specs;
	/**
	 * 排序号
	 */
	private Integer sortNo;
	/**
	 * 描述
	 */
	private String memo;

}
