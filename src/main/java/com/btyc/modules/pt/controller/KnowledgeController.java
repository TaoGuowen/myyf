package com.btyc.modules.pt.controller;

import java.util.Arrays;
import java.util.Map;

import com.btyc.modules.pt.entity.KnowledgeEntity;
import com.btyc.modules.pt.service.KnowledgeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btyc.modules.pt.entity.KnowledgeEntity;
import com.btyc.modules.pt.service.KnowledgeService;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;



/**
 * 知识
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:25:58
 */
@RestController
@RequestMapping("pt/knowledge")
public class KnowledgeController {
    @Autowired
    private KnowledgeService knowledgeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("pt:knowledge:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = knowledgeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("pt:knowledge:info")
    public R info(@PathVariable("id") String id){
		KnowledgeEntity knowledge = knowledgeService.getById(id);

        return R.ok().put("knowledge", knowledge);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("pt:knowledge:save")
    public R save(@RequestBody KnowledgeEntity knowledge){
		knowledgeService.save(knowledge);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("pt:knowledge:update")
    public R update(@RequestBody KnowledgeEntity knowledge){
		knowledgeService.updateById(knowledge);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("pt:knowledge:delete")
    public R delete(@RequestBody String[] ids){
		knowledgeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
