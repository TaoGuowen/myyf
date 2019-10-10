package com.btyc.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.user.entity.InfoEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户卡信息
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:56
 */
public interface InfoService extends IService<InfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<InfoEntity> selectListByUserId(Integer userId);

    Date queryMaxDay(Map map);

}

