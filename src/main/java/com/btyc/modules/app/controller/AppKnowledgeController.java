package com.btyc.modules.app.controller;

import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;
import com.btyc.modules.pt.entity.KnowledgeEntity;
import com.btyc.modules.pt.service.KnowledgeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *APP知识库接口
 */
@RestController
@RequestMapping("/app/knowledge")
@Api("APP知识库接口")
public class AppKnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    /**
     * 列表
     */
    @GetMapping("list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = knowledgeService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 单条知识库信息查询
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        KnowledgeEntity knowledge = knowledgeService.getById(id);
        return R.ok().put("data", knowledge);
    }

}
