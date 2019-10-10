package com.btyc.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 产品属性
本表记录产品属性：
1、color、颜色、varchar(16)
2、si
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:59
 */
@Data
@TableName("PRODUCT_PROPERTY")
public class PropertyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 码值
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 属性类型
	 */
	private String dataType;
	/**
	 * 排序号
	 */
	private Integer sortNo;

}
