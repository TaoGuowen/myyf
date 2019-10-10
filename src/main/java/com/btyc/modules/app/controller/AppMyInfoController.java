package com.btyc.modules.app.controller;

import cn.hutool.core.date.DateUtil;
import com.btyc.common.utils.R;
import com.btyc.modules.app.annotation.Login;
import com.btyc.modules.app.service.AppOrderService;
import com.btyc.modules.product.service.OrderService;
import com.btyc.modules.pt.entity.FeedbackEntity;
import com.btyc.modules.pt.service.FeedbackService;
import com.btyc.modules.user.entity.InfoEntity;
import com.btyc.modules.user.entity.MemberEntity;
import com.btyc.modules.user.service.InfoService;
import com.btyc.modules.user.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 小程序我的接口
 */
@RestController
@RequestMapping("/app/my")
@Api("APP我的接口")
public class AppMyInfoController {

    @Autowired
    private InfoService cardInfoService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private AppOrderService appOrderService;

    /**
     * 获取用户卡信息
     */
    @Login
    @GetMapping("cards")
    public R login(@RequestAttribute("userId") Integer userId) {
        List<InfoEntity> cardinfoList = cardInfoService.selectListByUserId(userId);
        return R.ok().put("data", cardinfoList);
    }

    /**
     * 根据卡片id获取卡片信息
     */
    @Login
    @RequestMapping("/card/{id}")
    public R card(@PathVariable("id") String id) {
        InfoEntity info = cardInfoService.getById(id);
        return R.ok().put("info", info);
    }

    /**
     * 获取用户订单列表
     */
    @Login
    @RequestMapping("orders")
    public R orders(@RequestAttribute("userId") Integer userId) {
        //List<OrderEntity> orderList = orderService.selectListByUserId(userId);
        List<Map> maps = appOrderService.queryOrders(userId);
        return R.ok().put("data", maps);
    }


    /**
     * 获取家庭成员列表
     */
    @Login
    @RequestMapping("members")
    public R members(@RequestAttribute("userId") Integer userId) {
        List<MemberEntity> memberList = memberService.selectListByUserId(userId);
        return R.ok().put("data", memberList);
    }

    /**
     * 保存家庭成员
     */
    @Login
    @RequestMapping("/getMember")
    public R addMember(@RequestParam String id) {
        MemberEntity memberEntity = memberService.getById(id);
        return R.ok().put("data",memberEntity);
    }

    /**
     * 保存家庭成员
     */
    @Login
    @RequestMapping("/addMember")
    public R addMember(@RequestAttribute("userId") Integer userId,@RequestBody MemberEntity member) {
        member.setUserId(userId.toString());
        Integer s = memberService.addMember(member);
        member.setId(s.toString());
        return R.ok().put("data",member);
    }

    /**
     * 成员修改
     */
    @Login
    @RequestMapping("/updateMember")
    public R updateMember(@RequestAttribute("userId") Integer userId,@RequestBody MemberEntity member) {
        member.setUserId(userId.toString());
        memberService.updateById(member);
        return R.ok();
    }

    /**
     * 成员删除
     */
    @Login
    @RequestMapping("/deleteMember")
    public R delete(@RequestBody String[] ids) {
        memberService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 成员删除
     */
    @Login
    @RequestMapping("/deleteMember/{id}")
    public R delete(@PathVariable String id) {
        memberService.removeById(id);
        return R.ok();
    }


    /**
     * 保存
     */
    @Login
    @RequestMapping("/addFeedback")
    public R save(@RequestAttribute("userId") Integer userId,@RequestBody FeedbackEntity feedback){
        feedback.setUserId(userId.toString());
        feedback.setCreateTime(DateUtil.now());
        feedbackService.save(feedback);
        return R.ok();
    }

}
