package com.btyc.modules.payment.controller;

import java.util.Arrays;
import java.util.Map;

import com.btyc.modules.payment.entity.PayEntity;
import com.btyc.modules.payment.service.PayService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btyc.modules.payment.entity.PayEntity;
import com.btyc.modules.payment.service.PayService;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;



/**
 * 支付
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:58
 */
@RestController
@RequestMapping("payment/pay")
public class PayController {
    @Autowired
    private PayService payService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("payment:pay:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = payService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("payment:pay:info")
    public R info(@PathVariable("id") String id){
		PayEntity pay = payService.getById(id);

        return R.ok().put("pay", pay);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("payment:pay:save")
    public R save(@RequestBody PayEntity pay){
		payService.save(pay);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("payment:pay:update")
    public R update(@RequestBody PayEntity pay){
		payService.updateById(pay);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("payment:pay:delete")
    public R delete(@RequestBody String[] ids){
		payService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
