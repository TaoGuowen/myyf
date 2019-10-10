package com.btyc.modules.pt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@Data
@TableName("PT_DICT")
public class DictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 类型
	 */
	private String busiType;
	/**
	 * 码值
	 */
	private String codeValue;
	/**
	 * 码值名称
	 */
	private String codeName;
	/**
	 * 排序号
	 */
	private String sortNo;
	/**
	 * 备注
	 */
	private String remark;

}
