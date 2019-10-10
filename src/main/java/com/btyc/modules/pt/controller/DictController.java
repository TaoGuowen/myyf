package com.btyc.modules.pt.controller;

import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;
import com.btyc.modules.pt.entity.DictEntity;
import com.btyc.modules.pt.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 字典
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@RestController
@RequestMapping("pt/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("pt:dict:list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = dictService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("pt:dict:info")
    public R info(@PathVariable("id") String id){
		DictEntity dict = dictService.getById(id);

        return R.ok().put("dict", dict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("pt:dict:save")
    public R save(@RequestBody DictEntity dict){
		dictService.save(dict);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("pt:dict:update")
    public R update(@RequestBody DictEntity dict){
		dictService.updateById(dict);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("pt:dict:delete")
    public R delete(@RequestBody String[] ids){
		dictService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
