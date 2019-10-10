package com.btyc.modules.payment.controller;

import java.util.Arrays;
import java.util.Map;

import com.btyc.modules.payment.entity.PtradeEntity;
import com.btyc.modules.payment.service.PtradeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btyc.modules.payment.entity.PtradeEntity;
import com.btyc.modules.payment.service.PtradeService;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;



/**
 * 支付流水
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:57
 */
@RestController
@RequestMapping("payment/ptrade")
public class PtradeController {
    @Autowired
    private PtradeService ptradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("payment:ptrade:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ptradeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("payment:ptrade:info")
    public R info(@PathVariable("id") String id){
		PtradeEntity ptrade = ptradeService.getById(id);

        return R.ok().put("ptrade", ptrade);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("payment:ptrade:save")
    public R save(@RequestBody PtradeEntity ptrade){
		ptradeService.save(ptrade);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("payment:ptrade:update")
    public R update(@RequestBody PtradeEntity ptrade){
		ptradeService.updateById(ptrade);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("payment:ptrade:delete")
    public R delete(@RequestBody String[] ids){
		ptradeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
