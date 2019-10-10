package com.btyc.modules.pt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btyc.common.utils.PageUtils;
import com.btyc.modules.pt.entity.DictEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
public interface DictService extends IService<DictEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils select(Map<String, Object> params);

    /**查询卡类型
     * @return
     */
    List<HashMap> selectPt();

    List<DictEntity> selectListByBusiType(String busiType);
}

