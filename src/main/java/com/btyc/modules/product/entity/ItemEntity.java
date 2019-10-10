package com.btyc.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 检测项目
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Data
@TableName("PRODUCT_CHECK_ITEM")
public class ItemEntity implements Serializable {
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
	 * 遮挡编码
	 */
	private String coverCodes;
	/**
	 * 排序号
	 */
	private Integer sortNo;

}
