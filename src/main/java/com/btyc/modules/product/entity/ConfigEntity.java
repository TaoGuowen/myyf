package com.btyc.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 身高尺码配置表
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:59
 */
@Data
@TableName("PRODUCT_SIZE_CONFIG")
public class ConfigEntity implements Serializable {
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
	 * 身高下限
	 */
	private BigDecimal low;
	/**
	 * 身高上限
	 */
	private BigDecimal up;
	/**
	 * 排序号
	 */
	private Integer sortNo;

}
