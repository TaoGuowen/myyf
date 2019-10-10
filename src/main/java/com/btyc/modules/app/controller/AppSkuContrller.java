package com.btyc.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.btyc.common.utils.R;
import com.btyc.modules.product.entity.CardEntity;
import com.btyc.modules.product.entity.ConfigEntity;
import com.btyc.modules.product.entity.SkuEntity;
import com.btyc.modules.product.service.CardService;
import com.btyc.modules.product.service.ConfigService;
import com.btyc.modules.product.service.SkuService;
import com.btyc.modules.product.service.SpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/app/sku")
@Api("APP商品信息接口")
public class AppSkuContrller {

    @Autowired
    private SpuService spuService;

    @Autowired
    private SkuService skuService;

    @Autowired
    private CardService cardService;

    @Autowired
    private ConfigService configService;

    /**
     * 查询防辐射服信息
     *
     * @param checkItem
     * @return
     */
    @RequestMapping("/getSkuInfo")
    @ApiOperation("查询防辐射服信息")
    public R getSkuInfo(@RequestParam String checkItem) {
        try {
            List<String> strings = Arrays.asList(new String[]{"CHECK_CODE"});
            List<SkuEntity> skuEntities = skuService.list(new QueryWrapper<SkuEntity>().eq("CHECK_CODE",checkItem).orderByAsc("SIZE_CODE"));
            List<ConfigEntity> list = configService.list();
            for (SkuEntity skuEntity : skuEntities) {
                for (ConfigEntity cofig : list) {
                    if(skuEntity.getSizeCode().equals(cofig.getCode())){
                        skuEntity.setSizeName(cofig.getName());
                        continue;
                    }
                }
            }
            return R.ok().put("data", skuEntities);
        } catch (Exception ex) {
            return R.error();
        }
    }

    /**
     * 查询月卡次卡信息
     *
     * @return
     */
    @GetMapping("/getCardInfo")
    @ApiOperation("查询月卡次卡信息")
    public R getCardInfo() {
        try {
            List<CardEntity> list = cardService.list();
            return R.ok().put("card", list);
        } catch (Exception ex) {
            return R.error();
        }
    }

}
