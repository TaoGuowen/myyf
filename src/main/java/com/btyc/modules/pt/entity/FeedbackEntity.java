package com.btyc.modules.pt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author ysq
 * @date 2019-08-22 19:15:53
 */
@Data
@TableName("PT_FEEDBACK")
public class FeedbackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 反馈内容
	 */
	private String content;
	/**
	 * 回复内容
	 */
	private String reply;
	/**
	 * 回复时间
	 */
	private String replyTime;

}
