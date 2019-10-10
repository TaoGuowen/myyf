package com.btyc.modules.app.controller;

import com.btyc.common.utils.R;
import com.btyc.modules.app.annotation.Login;
import com.btyc.modules.app.annotation.LoginUser;
import com.btyc.modules.app.entity.UserEntity;
import com.btyc.modules.app.service.AppOrderService;
import com.btyc.modules.app.utils.WxUtils;
import com.btyc.wxpay.MyConfig;
import com.btyc.wxpay.WXPay;
import com.btyc.wxpay.WXPayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * APP知识库接口
 */
@RestController
@RequestMapping("/app/order")
@Api("APP订单接口")
public class AppOrderController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AppOrderService appOrderService;

    @Autowired
    private WxUtils wxUtils;


    /**
     * 创建订单
     * @param user
     * @param params
     * @param req
     * @return
     */
    @Login
    @RequestMapping("/create")
    @ApiOperation("创建订单")
    public R create(@LoginUser UserEntity user, @RequestBody Map<String,String> params,HttpServletRequest req) throws Exception {
        return appOrderService.createOrder(user, params, req);
    }

    /**
     * 支付结果通知接口
     *
     * @param request
     * @param response
     */
    @RequestMapping("/notiy")
    @ApiOperation("支付结果通知接口")
    public void notiy(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        MyConfig config = null;
        String returnXml = "";
        StringBuffer xmlStr = new StringBuffer();
        Map retMsg = new HashMap<String, String>();
        try {
            out = response.getWriter();
            config = wxUtils.getMyConfig();
            WXPay wxpay = new WXPay(config);
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                xmlStr.append(line);
            }
            logger.debug("支付回调通知：" + xmlStr.toString());
            retMsg = appOrderService.notiy(xmlStr.toString(), wxpay);
            returnXml = WXPayUtil.mapToXml(retMsg);
        } catch (Exception ex) {
            ex.printStackTrace();
            retMsg.put("return_code", "FAIL");
            retMsg.put("return_msg", "处理过程中发生了异常");
            try {
                returnXml = WXPayUtil.mapToXml(retMsg);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        } finally {
            out.write(returnXml);
            out.close();
        }

    }

}
