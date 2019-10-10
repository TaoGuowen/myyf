package com.btyc.modules.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.btyc.modules.product.entity.OrderEntity;
import com.btyc.modules.product.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btyc.modules.product.entity.OrderEntity;
import com.btyc.modules.product.service.OrderService;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;



/**
 * 订单
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@RestController
@RequestMapping("product/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:order:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{no}")
    @RequiresPermissions("product:order:info")
    public R info(@PathVariable("no") String no){
		OrderEntity order = orderService.getById(no);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:order:save")
    public R save(@RequestBody OrderEntity order){
		orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:order:update")
    public R update(@RequestBody OrderEntity order){
		orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:order:delete")
    public R delete(@RequestBody String[] nos){
		orderService.removeByIds(Arrays.asList(nos));

        return R.ok();
    }

}
