package com.btyc.modules.app.service;

import com.btyc.common.utils.R;
import com.btyc.modules.app.entity.UserEntity;
import com.btyc.wxpay.WXPay;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AppOrderService {
    /**
     * 生成订单
     * @param user
     * @param params
     * @param req
     * @return
     */
    R createOrder(UserEntity user, Map<String, String> params, HttpServletRequest req) throws Exception;


    /**
     * 支付结果处理
     *
     * @param xml
     * @param wxpay
     * @return
     * @throws Exception
     */
    Map<String, String> notiy(String xml, WXPay wxpay) throws Exception;


    /**
     * 查询用户创建的角色ID列表
     */
    List<Map> queryOrders(Integer userId);

}
