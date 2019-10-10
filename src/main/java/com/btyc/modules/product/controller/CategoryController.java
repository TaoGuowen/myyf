package com.btyc.modules.product.controller;

import com.btyc.common.utils.PageUtils;
import com.btyc.common.utils.R;
import com.btyc.modules.product.entity.CategoryEntity;
import com.btyc.modules.product.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



/**
 * 本表记录产品分类信息
  如：
    一级：女装/内衣

 *
 * @author ysq
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 19:26:00
 */
@RestController
@RequestMapping("/product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:category:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:category:info")
    public R info(@PathVariable("id") String id){

        Map map =  categoryService.selectInfoById(Integer.valueOf(id));
        if (map.get("pname")==null){
            map.put("pname","一级菜单");
        }
        return R.ok().put("category",map);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }




    /**
     * 所有菜单列表
     */
    @GetMapping("/cardlist")
   // @RequiresPermissions("sys:menu:list")
    public List<CategoryEntity> list(){
        List<CategoryEntity> menuList = categoryService.list();
        for(CategoryEntity categoryEntity : menuList){
            CategoryEntity parentMenuEntity = categoryService.getById(categoryEntity.getPid());
            if(parentMenuEntity != null){
                categoryEntity.setParentName(parentMenuEntity.getName());
            }
        }

        return menuList;
    }


    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    //@RequiresPermissions("sys:menu:select")
    public R select(){

        //查询列表数据
        List<CategoryEntity> menuList = categoryService.selectBy();

        //添加顶级菜单
        CategoryEntity root = new CategoryEntity();
        root.setId(0L);
        root.setName("一级菜单");
        root.setPid(-1L);
        root.setOpen(true);
        menuList.add(root);

        return R.ok().put("menuList", menuList);
    }


    /**
     * 删除
     */
    @PostMapping("/delete/{menuId}")
    @RequiresPermissions("product:category:delete")
    public R delete(@PathVariable("menuId") long menuId){
        List<CategoryEntity> menuList = categoryService.queryListPId(menuId);
        //判断是否有子菜单或按钮
        if(menuList.size() > 0){
            return R.error("请先删除子产品");
        }
        categoryService.delete(menuId);
        return R.ok();
    }

}
