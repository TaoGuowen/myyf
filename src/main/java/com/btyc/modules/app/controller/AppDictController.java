package com.btyc.modules.app.controller;

import com.btyc.common.utils.R;
import com.btyc.modules.product.entity.ConfigEntity;
import com.btyc.modules.product.entity.ItemEntity;
import com.btyc.modules.product.service.ConfigService;
import com.btyc.modules.product.service.ItemService;
import com.btyc.modules.pt.entity.DictEntity;
import com.btyc.modules.pt.service.DictService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * APP知识库接口
 */
@RestController
@RequestMapping("/app/dict")
@Api("APP字典接口")
public class AppDictController {

    @Autowired
    private DictService dictService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private ItemService itemService;

    /**
     * 列表
     */
    @GetMapping("list/{types}")
    public R list(@PathVariable("types") String[] types) {
        Map map = new HashMap();
        for (String type : types) {
            List<DictEntity> list = dictService.selectListByBusiType(type);
            map.put(type, list);
        }
        return R.ok().put("data", map);
    }


    /**
     * 尺寸配置列表
     */
    @GetMapping("sizeConfig")
    public R sizeConfig() {
        List<ConfigEntity> list = configService.list();
        return R.ok().put("sizeConfig", list);
    }

    /**
     * 检查项目配置列表
     */
    @GetMapping("checkItem")
    public R checkItem() {
        List<ItemEntity> list = itemService.list();
        return R.ok().put("checkItem", list);
    }

}
