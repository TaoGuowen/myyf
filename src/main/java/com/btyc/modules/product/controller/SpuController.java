package com.btyc.modules.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.btyc.modules.product.entity.SpuEntity;
import com.btyc.modules.product.service.CategoryService;
import com.btyc.modules.product.service.SpuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.btyc.modules.product.entity.SpuEntity;
import com.btyc.modules.product.service.SpuService;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;



/**
 * 产品 本表记录产品信息：如防护服1
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@RestController
@RequestMapping("product/spu")
public class SpuController {
    @Autowired
    private SpuService spuService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:spu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:spu:info")
    public R info(@PathVariable("id") String id){
		SpuEntity spu = spuService.getById(id);

        return R.ok().put("spu", spu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:spu:save")
    public R save(@RequestBody SpuEntity spu){
		spuService.save(spu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:spu:update")
    public R update(@RequestBody SpuEntity spu){
		spuService.updateById(spu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:spu:delete")
    public R delete(@RequestBody String[] ids){
		spuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @GetMapping("/selectPruductType")
    @RequiresPermissions("product:spu:selectPruductType")
    public Map selectAll(){
        Map<String,List<Map<String,Object >>> dicmaps = new HashMap<>();
        List<Map<String, Object>> productType =categoryService.selectAll();
        dicmaps.put("productType",productType);
        return dicmaps;
    }

}
