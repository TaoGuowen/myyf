package com.btyc.modules.app.controller;


import com.btyc.common.utils.R;
import com.btyc.common.validator.ValidatorUtils;
import com.btyc.modules.app.entity.UserEntity;
import com.btyc.modules.app.form.RegisterForm;
import com.btyc.modules.app.service.UserService;
import com.btyc.modules.app.utils.WxUtils;
import com.btyc.modules.user.entity.MemberEntity;
import com.btyc.modules.user.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 注册
 *
 * @author ams
 */
@RestController
@RequestMapping("/app")
@Api("APP注册接口")
public class AppRegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private WxUtils wxUtils;

    @PostMapping("register")
    @ApiOperation("注册")
    public R register(@RequestBody RegisterForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);
        String code=form.getCode();
        String openId = wxUtils.getOpenId(code);
        UserEntity user = new UserEntity();
        user.setMobile(form.getMobile().toString());
        user.setOpenid(openId);
        user.setCreateTime(new Date());
        Long userId = userService.register(user);
        MemberEntity doc = new MemberEntity();
        doc.setUserId(userId.toString());
        doc.setName(form.getName());
        doc.setBirthDay(form.getBirthDay());
        doc.setMobile(form.getMobile());
        doc.setHeight(form.getHeight());
        doc.setIsSelf("1");
        memberService.save(doc);
        return R.ok();
    }
}
