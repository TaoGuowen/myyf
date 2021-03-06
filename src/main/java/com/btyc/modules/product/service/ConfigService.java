package com.btyc.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.modules.product.entity.ConfigEntity;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.product.entity.ConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * 身高尺码配置表
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:59
 */
public interface ConfigService extends IService<ConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> configAll();

}

