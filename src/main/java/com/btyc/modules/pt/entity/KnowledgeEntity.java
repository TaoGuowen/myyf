package com.btyc.modules.pt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 知识
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:58
 */
@Data
@TableName("PT_KNOWLEDGE")
public class KnowledgeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 类型
	 */
	private String typeCode;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 专家
	 */
	private String expert;
	/**
	 * 状态
	 */
	private String statusCode;

}
