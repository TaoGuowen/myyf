package com.btyc.modules.pt.controller;

import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;
import com.btyc.modules.pt.entity.FeedbackEntity;
import com.btyc.modules.pt.service.FeedbackService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 *
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-08-22 19:15:53
 */
@RestController
@RequestMapping("pt/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("pt:feedback:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = feedbackService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("pt:feedback:info")
    public R info(@PathVariable("id") Long id){
		FeedbackEntity feedback = feedbackService.getById(id);

        return R.ok().put("feedback", feedback);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("pt:feedback:save")
    public R save(@RequestBody FeedbackEntity feedback){
		feedbackService.save(feedback);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("pt:feedback:update")
    public R update(@RequestBody FeedbackEntity feedback){
		feedbackService.updateById(feedback);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("pt:feedback:delete")
    public R delete(@RequestBody Long[] ids){
		feedbackService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
