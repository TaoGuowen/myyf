package com.btyc.modules.payment.controller;

import java.util.Arrays;
import java.util.Map;

import com.btyc.modules.payment.entity.RtradeEntity;
import com.btyc.modules.payment.service.RtradeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btyc.modules.payment.entity.RtradeEntity;
import com.btyc.modules.payment.service.RtradeService;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;



/**
 * 退款流水
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:57
 */
@RestController
@RequestMapping("payment/rtrade")
public class RtradeController {
    @Autowired
    private RtradeService rtradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("payment:rtrade:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = rtradeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("payment:rtrade:info")
    public R info(@PathVariable("id") String id){
		RtradeEntity rtrade = rtradeService.getById(id);

        return R.ok().put("rtrade", rtrade);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("payment:rtrade:save")
    public R save(@RequestBody RtradeEntity rtrade){
		rtradeService.save(rtrade);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("payment:rtrade:update")
    public R update(@RequestBody RtradeEntity rtrade){
		rtradeService.updateById(rtrade);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("payment:rtrade:delete")
    public R delete(@RequestBody String[] ids){
		rtradeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
