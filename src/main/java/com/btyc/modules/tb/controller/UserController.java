package com.btyc.modules.tb.controller;

import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;
import com.btyc.modules.app.entity.UserEntity;
import com.btyc.modules.app.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 用户
 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 20:37:57
 */
@RestController
@RequestMapping("tb/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tb:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.selectUserList(params);

        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("tb:user:info")
    public R info(@PathVariable("userId") Long userId){
		UserEntity user = userService.getById(userId);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tb:user:save")
    public R save(@RequestBody UserEntity user){
        userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tb:user:update")
    public R update(@RequestBody UserEntity user){
        userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tb:user:delete")
    public R delete(@RequestBody Long[] userIds){
        userService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
