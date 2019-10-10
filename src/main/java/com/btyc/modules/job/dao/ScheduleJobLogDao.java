package com.btyc.modules.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.btyc.modules.job.entity.ScheduleJobLogEntity;
import com.btyc.modules.job.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 * @author ams
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {

}
