package com.btyc.modules.product.controller;

import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;
import com.btyc.modules.product.entity.CardEntity;
import com.btyc.modules.product.service.CardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 卡
 *
 * @author tsy
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@RestController
@RequestMapping("product/card")
public class CardController {
    @Autowired
    private CardService cardService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:card:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cardService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/list1")
    @RequiresPermissions("product:card:list")
    public R selectList(@RequestParam Map<String, Object> params){
        int page = Integer.valueOf((String) params.get("page"));
        int limit = Integer.valueOf((String) params.get("limit"));
        String name = (String) params.get("key");
        PageHelper.startPage(page,limit);
        List<HashMap> list = cardService.selectCa(name);
        PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(list);
        return R.ok().put("page",pageInfo);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:card:info")
    public R info(@PathVariable("id") String id){
		CardEntity card = cardService.getById(id);
        return R.ok().put("card", card);
    }

    /**
     * 信息
     */
    @RequestMapping("/info1/{id}")
    @RequiresPermissions("product:card:info")
    public R info1(@PathVariable("id") String id){
        return R.ok().put("card",  cardService.selectCaById(id));
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:card:save")
    public R save(@RequestBody CardEntity card){
		cardService.save(card);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:card:update")
    public R update(@RequestBody CardEntity card){
        cardService.updateById(card);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:card:delete")
    public R delete(@RequestBody String[] ids){
		cardService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
