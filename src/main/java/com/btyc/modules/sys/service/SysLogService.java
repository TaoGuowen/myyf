package com.btyc.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.sys.entity.SysLogEntity;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author ams
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
