package com.btyc.modules.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.btyc.modules.product.entity.SkuEntity;
import com.btyc.modules.product.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.btyc.modules.product.entity.SkuEntity;
import com.btyc.modules.product.service.SkuService;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;



/**
 * 商品
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:59
 */
@RestController
@RequestMapping("product/sku")
public class SkuController {
    @Autowired
    private SkuService skuService;

    @Autowired
    private SpuService spuService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private PropertyService propertyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:sku:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:sku:info")
    public R info(@PathVariable("id") String id){
		SkuEntity sku = skuService.getById(id);

        return R.ok().put("sku", sku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:sku:save")
    public R save(@RequestBody SkuEntity sku){
		skuService.save(sku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:sku:update")
    public R update(@RequestBody SkuEntity sku){
		skuService.updateById(sku);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:sku:delete")
    public R delete(@RequestBody String[] ids){
		skuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @GetMapping("/selectAll")
    @RequiresPermissions("product:sku:selectAll")
    public Map selectAll(){
        Map<String,List<Map<String,Object >>> dicmaps = new HashMap<>();
        List<Map<String, Object>> spuAll =spuService.spuAll();
        List<Map<String, Object>> itemAll =itemService.itemAll();
        List<Map<String, Object>> configAll =configService.configAll();
        List<Map<String, Object>> propertyAll =propertyService.propertyAll();
        dicmaps.put("spuAll",spuAll);
        dicmaps.put("itemAll",itemAll);
        dicmaps.put("configAll",configAll);
        dicmaps.put("propertyAll",propertyAll);
        return dicmaps;
    }

}
