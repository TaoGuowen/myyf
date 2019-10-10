package com.btyc.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.modules.job.entity.ScheduleJobLogEntity;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.job.entity.ScheduleJobLogEntity;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author ams
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(Map<String, Object> params);

}
