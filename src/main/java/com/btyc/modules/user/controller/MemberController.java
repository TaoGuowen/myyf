package com.btyc.modules.user.controller;

import java.util.Arrays;
import java.util.Map;

import com.btyc.modules.user.entity.MemberEntity;
import com.btyc.modules.user.service.MemberService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btyc.modules.user.entity.MemberEntity;
import com.btyc.modules.user.service.MemberService;
import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;



/**
 * 家庭成员
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@RestController
@RequestMapping("user/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:member:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("user:member:info")
    public R info(@PathVariable("id") String id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:member:delete")
    public R delete(@RequestBody String[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
