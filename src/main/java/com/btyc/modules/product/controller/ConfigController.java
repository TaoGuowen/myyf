package com.btyc.modules.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.btyc.modules.product.entity.ConfigEntity;
import com.btyc.modules.product.service.ConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btyc.modules.product.entity.ConfigEntity;
import com.btyc.modules.product.service.ConfigService;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;



/**
 * 身高尺码配置表
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:59
 */
@RestController
@RequestMapping("product/config")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:config:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = configService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:config:info")
    public R info(@PathVariable("id") String id){
		ConfigEntity config = configService.getById(id);

        return R.ok().put("config", config);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:config:save")
    public R save(@RequestBody ConfigEntity config){
		configService.save(config);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:config:update")
    public R update(@RequestBody ConfigEntity config){
		configService.updateById(config);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:config:delete")
    public R delete(@RequestBody String[] ids){
		configService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
