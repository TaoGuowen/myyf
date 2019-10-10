package com.btyc.modules.app.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.btyc.common.exception.RRException;
import com.btyc.common.utils.R;
import com.btyc.constant.*;
import com.btyc.modules.app.entity.UserEntity;
import com.btyc.modules.app.service.AppOrderService;
import com.btyc.modules.app.utils.WxUtils;
import com.btyc.modules.payment.dao.PayDao;
import com.btyc.modules.payment.dao.PtradeDao;
import com.btyc.modules.payment.entity.PayEntity;
import com.btyc.modules.payment.entity.PtradeEntity;
import com.btyc.modules.product.dao.CardDao;
import com.btyc.modules.product.dao.OrderDao;
import com.btyc.modules.product.dao.SkuDao;
import com.btyc.modules.product.dao.SpuDao;
import com.btyc.modules.product.entity.CardEntity;
import com.btyc.modules.product.entity.OrderEntity;
import com.btyc.modules.product.entity.SkuEntity;
import com.btyc.modules.product.entity.SpuEntity;
import com.btyc.modules.user.dao.InfoDao;
import com.btyc.modules.user.dao.MemberDao;
import com.btyc.modules.user.entity.InfoEntity;
import com.btyc.modules.user.entity.MemberEntity;
import com.btyc.wxpay.MyConfig;
import com.btyc.wxpay.WXPay;
import com.btyc.wxpay.WXPayConstants;
import com.btyc.wxpay.WXPayUtil;
import com.btyc.wxpay.enums.TradeType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单service
 */
@Service("appOrderService")
public class AppOrderImpl implements AppOrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SkuDao skuDao;

    @Autowired
    private SpuDao spuDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private CardDao cardDao;

    @Autowired
    private PtradeDao ptradeDao;

    @Autowired
    private PayDao payDao;

    @Autowired
    private InfoDao infoDao;

    @Autowired
    private WxUtils wxUtils;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final AtomicInteger atomicInteger = new AtomicInteger(1000000);


    /**
     * 生成订单
     *
     * @param user
     * @param params
     * @param req
     * @return
     */
    @Override
    @Transactional
    public R createOrder(UserEntity user, Map<String, String> params, HttpServletRequest req) throws Exception {
        String skuId = params.get("skuId");
        String typeCode = params.get("typeCode");
        String memberId = params.get("memberId");
        String payMethod = params.get("payMethod");
        if (StringUtils.isEmpty(skuId) || StringUtils.isEmpty(typeCode) || StringUtils.isEmpty(memberId) || StringUtils.isEmpty(payMethod)) {
            throw new RRException("参数不完整！");
        }
        OrderEntity order = new OrderEntity();
        order.setUserId(user.getUserId().toString());
        order.setSkuId(skuId);
        order.setTypeCode(typeCode);
        //生成订单编号
        String orderNumber = getOrderNoByAtomic();
        order.setNumber(orderNumber);
        //产品类
        if ("01".equals(typeCode)) {
            skuDao.selectById(skuId);
            SkuEntity sku = skuDao.selectById(skuId);
            SpuEntity spu = spuDao.selectById(sku.getSpuId());
            order.setSpuId(spu.getId());
            order.setSpuName(spu.getName());
            order.setSkuName(sku.getName());
            order.setMoney(sku.getPrice());
            //卡片类
        } else {
            CardEntity card = cardDao.selectById(skuId);
            order.setSkuName(card.getName());
            order.setMoney(card.getPrice());
        }
        MemberEntity member = memberDao.selectById(memberId);
        order.setName(member.getName());
        order.setMobile(member.getMobile());
        order.setCreateTime(DateUtil.today());
        order.setStatusCode(OrderStatus.UNPAY.getValue());

        //会员卡支付
        if (PayMethod.CARD.getValue().equals(payMethod)) {
            String cardId = params.get("cardId");
            if (StringUtils.isEmpty(cardId)) {
                throw new RRException("该支付方式cardId为空");
            }
            InfoEntity infoEntity = infoDao.selectById(cardId);
            //卡片有效
            if (YesNo.YES.getValue().equals(infoEntity.getStatusCode())) {
                Integer remaindTimes = infoEntity.getRemaindTimes();
                //检查卡片有效期
                String beginDay = infoEntity.getBeginDay().replace("-", "").replace("/", "");
                String endDay = infoEntity.getEndDay().replace("-", "").replace("/", "");
                String today = DateUtil.format(new Date(), "yyyyMMdd");
                int begin = Integer.parseInt(beginDay);
                int end = Integer.parseInt(endDay);
                int now = Integer.parseInt(today);
                //在有效期内
                if (now >= begin && end <= now) {
                    //卡片是否还有剩余次数
                    if (remaindTimes > 0 || remaindTimes == -1) {
                        //如果为次卡，需要剩余次数减1，如果为月卡，则无需更新卡状态
                        if (CardType.TIME_CARD.getValue().equals(infoEntity.getCardType())) {
                            infoEntity.setRemaindTimes(remaindTimes - 1);
                            infoDao.updateById(infoEntity);
                        }
                        order.setCardInfoId(cardId);
                        order.setPayMethod(PayMethod.CARD.getValue());
                        //order.setPayType();
                        orderDao.createOrder(order);
                    } else {
                        throw new RRException("所选卡片剩余次数不足！");
                    }
                } else {
                    throw new RRException("卡片不在有效期内！");
                }
                //卡片无效
            } else {
                throw new RRException("所选卡片已失效！");
            }
            //微信支付
        } else if (PayMethod.WEIXIN.getValue().equals(payMethod)) {
            String remoteAddr = req.getRemoteAddr();
            //支付流水
            PtradeEntity ptradeEntity = new PtradeEntity();
            ptradeEntity.setCash(order.getMoney());
            //产品类
            if (OrderType.PRODUCT.getValue().equals(order.getTypeCode())) {
                ptradeEntity.setTitle("无线医服-防辐射服租赁");
                //卡片类
            } else if (OrderType.CARD.getValue().equals(order.getTypeCode())) {
                ptradeEntity.setTitle("无线医服-会员卡购买");
            }
            ptradeEntity.setCjtime(new Date());
            ptradeEntity.setStatusCode(PayStatus.INIT.getValue());
            String ptradeNo = ptradeDao.createPtrade(ptradeEntity);
            if (StringUtils.isEmpty(ptradeNo)) {
                throw new RRException("支付流水生成失败！");
            }
            order.setTradeId(ptradeNo);
            int insertnum = orderDao.insert(order);
            //String no = orderDao.createOrder(order);
            if (insertnum==0) {
                throw new RRException("订单生成失败！");
            }
            //order.setNo(no);
            //支付明细信息
            PayEntity payEntity = new PayEntity();
            payEntity.setNo(order.getNumber());
            payEntity.setPtradeId(ptradeNo);
            //支付方式
            payEntity.setPayMethod(PayMethod.WEIXIN.getValue());
            //支付状态
            payEntity.setStatusCode(PayStatus.INIT.getValue());
            int size = payDao.insert(payEntity);
            if (size != 1) {
                throw new RRException("支付明细生成失败！");
            }
            MyConfig config = wxUtils.getMyConfig();
            WXPay wxpay = new WXPay(config, wxUtils.getNotifyUrl(), wxUtils.isAutoReport(), wxUtils.isUseSandbox());
            String body = ptradeEntity.getTitle();
            Map<String, String> data = new HashMap<String, String>();
            data = wxpay.fillRequestData(data);
            data.put("body", body);
            //商户订单号
            data.put("out_trade_no", order.getNumber());
            //设备号
            data.put("device_info", params.get("device_info"));
            //标价币种
            data.put("fee_type", "CNY");
            //标价金额
            data.put("total_fee", order.getMoney().toString());
            //终端IP
            data.put("spbill_create_ip", remoteAddr);
            //通知地址 异步接收微信支付结果通知的回调地址
            data.put("notify_url", wxUtils.getNotifyUrl());
            //交易类型
            data.put("trade_type", TradeType.JSAPI.getTradeType());
            //商品ID
            data.put("product_id", skuId);
            //调用微信统一下单接口
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
            if ("SUCCESS".equals(resp.get("return_code"))) {
                //再次签名
                Map<String, String> reData = new HashMap<>();
                reData.put("appId", wxUtils.getAppId());
                reData.put("nonceStr", resp.get("nonce_str"));
                String newPackage = "prepay_id=" + resp.get("prepay_id");
                reData.put("package", newPackage);
                reData.put("signType", WXPayConstants.SignType.MD5.name());
                reData.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
                String newSign = WXPayUtil.generateSignature(reData, wxUtils.getKey());
                resp.put("paySign", newSign);
                resp.put("timeStamp", reData.get("timeStamp"));
                return R.ok().put("data", resp);
            } else {
                return R.error(resp.get("return_msg"));
            }
        } else if (PayMethod.ZHIFUBAO.getValue().equals(payMethod)) {
            return R.error("暂不支持该支付方式！");
        }
        return R.ok().put("order", order);
    }


    @Override
    public List<Map> queryOrders(Integer userId) {
        return orderDao.queryOrders(userId);
    }

    /**
     * 支付结果回调
     *
     * @param xml
     * @param wxpay
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Map<String, String> notiy(String xml, WXPay wxpay) throws Exception {
        Map<String, String> retMsg = new HashMap<String, String>();
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(xml.toString());
        // 签名正确
        if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
            //此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
            String return_code = notifyMap.get("return_code");
            //交易成功标识
            String result_code = notifyMap.get("result_code");
            logger.info("微信支付:" + notifyMap.get("result_code"));
            //通讯成功
            if ("SUCCESS".equals(return_code)) {
                //商户订单号
                String out_trade_no = notifyMap.get("out_trade_no");
                //查询订单信息
                OrderEntity orderEntity = orderDao.selectOne(new QueryWrapper<OrderEntity>().eq("NUMBER",out_trade_no));
                //如果该订单为未付款状态，执行更新订单状态，更新流水信息，如果为卡购买还需生成卡片信息
                if (OrderStatus.UNPAY.getValue().equals(orderEntity.getStatusCode())) {
                    orderEntity.setStatusCode(OrderStatus.PAY.getValue());
                    //更新订单状态
                    orderDao.updateById(orderEntity);
                    String trideId = orderEntity.getTradeId();
                    //查询支付流水信息
                    PtradeEntity ptradeEntity = ptradeDao.selectById(trideId);
                    PayEntity payEntity = payDao.selectOne(new QueryWrapper<PayEntity>().eq("ptrade_id", trideId).eq("payment_pay", out_trade_no));
                    payEntity.setResMsg(xml);
                    payEntity.setResTime(new Date());
                    if ("SUCCESS".equals(result_code)) {//支付成功
                        ptradeEntity.setPayid(payEntity.getId());
                        ptradeEntity.setStatusCode(PayStatus.SUCCESS.getValue());
                        ptradeDao.updateById(ptradeEntity);
                        //更新支付明细信息
                        payEntity.setStatusCode(PayStatus.SUCCESS.getValue());
                        // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                    } else {//支付失败
                        ptradeEntity.setStatusCode(PayStatus.FAIL.getValue());
                        payEntity.setStatusCode(PayStatus.FAIL.getValue());
                    }
                    ptradeDao.updateById(ptradeEntity);
                    payDao.updateById(payEntity);
                    //开始生成用户卡信息
                    String typeCode = orderEntity.getTypeCode();
                    //如果购买的是卡片，需要生成用户卡信息
                    if (OrderType.CARD.getValue().equals(typeCode)) {
                        CardEntity cardEntity = cardDao.selectById(orderEntity.getSkuId());
                        //卡片类型
                        InfoEntity usercard = new InfoEntity();
                        usercard.setUserId(orderEntity.getUserId());
                        usercard.setOrderNo(orderEntity.getNumber());
                        usercard.setCardType(typeCode);
                        usercard.setCardName(cardEntity.getName());
                        //实际购买价格
                        String cash_fee = notifyMap.get("cash_fee");
                        usercard.setBuyPrice(new BigDecimal(cash_fee));
                        //支付完成时间
                        String time_end = notifyMap.get("time_end");
                        usercard.setBuyDay(time_end);
                        usercard.setStatusCode(YesNo.YES.getValue());
                        usercard.setTotalTimes(cardEntity.getTimes());
                        usercard.setRemaindTimes(cardEntity.getTimes());
                        //有效期
                        Integer ttl = cardEntity.getTtl();
                        if (CardType.TIME_CARD.getValue().equals(typeCode)) {
                            DateTime dateTime = DateUtil.offsetDay(new Date(), ttl - 1);
                            String endDay = DateUtil.format(dateTime, "yyyy-MM-dd");
                            usercard.setEndDay(endDay);
                        } else if (CardType.MONTH_CARD.getValue().equals(typeCode)) {
                            //查询最大月卡有效期
                            //infoDao.selectOne("");
                            Map map = new HashMap();
                            map.put("userId", orderEntity.getUserId());
                            map.put("cardType", typeCode);
                            Date date = infoDao.queryMaxDay(map);
                            DateTime beginTime = null;
                            DateTime endTime = null;
                            if (date != null) {
                                beginTime = DateUtil.offsetDay(date, 1);
                                endTime = DateUtil.offsetDay(date, ttl);
                            } else {
                                beginTime = DateTime.now();
                                endTime = DateUtil.offsetDay(new Date(), ttl - 1);
                            }
                            String beginDay = DateUtil.format(beginTime, "yyyy-MM-dd");
                            String endDay = DateUtil.format(endTime, "yyyy-MM-dd");
                            usercard.setBeginDay(beginDay);
                            usercard.setEndDay(endDay);
                        } else {
                            //TODO 其它卡，暂时无
                        }
                        infoDao.insert(usercard);
                    }
                }
                retMsg.put("return_code", "SUCCESS");
                retMsg.put("return_msg", "");
            } else {
                retMsg.put("return_code", "FAIL");
                retMsg.put("return_msg", "通讯失败");
            }
        }
        return retMsg;
    }

    /**
     * 获取同一秒钟 生成的订单号连续
     * @return
     */
    public static synchronized String getOrderNoByAtomic() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndIncrement();
        int i = atomicInteger.get();
        String date = simpleDateFormat.format(new Date());
        return date + i;
    }

}
