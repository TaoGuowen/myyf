package com.btyc.modules.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.btyc.modules.product.entity.ItemEntity;
import com.btyc.modules.product.service.ItemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btyc.modules.product.entity.ItemEntity;
import com.btyc.modules.product.service.ItemService;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;



/**
 * 检测项目
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@RestController
@RequestMapping("product/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:item:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = itemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:item:info")
    public R info(@PathVariable("id") String id){
		ItemEntity item = itemService.getById(id);

        return R.ok().put("item", item);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:item:save")
    public R save(@RequestBody ItemEntity item){
		itemService.save(item);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:item:update")
    public R update(@RequestBody ItemEntity item){
		itemService.updateById(item);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:item:delete")
    public R delete(@RequestBody String[] ids){
		itemService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
