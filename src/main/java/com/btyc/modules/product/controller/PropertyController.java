package com.btyc.modules.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.btyc.modules.product.entity.PropertyEntity;
import com.btyc.modules.product.service.PropertyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btyc.modules.product.entity.PropertyEntity;
import com.btyc.modules.product.service.PropertyService;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;



/**
 * 产品属性
本表记录产品属性：
1、color、颜色、varchar(16)
2、si
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:59
 */
@RestController
@RequestMapping("product/property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:property:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = propertyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:property:info")
    public R info(@PathVariable("id") String id){
		PropertyEntity property = propertyService.getById(id);

        return R.ok().put("property", property);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:property:save")
    public R save(@RequestBody PropertyEntity property){
		propertyService.save(property);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:property:update")
    public R update(@RequestBody PropertyEntity property){
		propertyService.updateById(property);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:property:delete")
    public R delete(@RequestBody String[] ids){
		propertyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
