package com.btyc.modules.app.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.btyc.wxpay.MyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@ConfigurationProperties(prefix = "myyf.wxconfig")
@Component
public class WxUtils {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 小程序id
     */
    private String appId;

    /**
     * 小程序密钥
     */
    private String secret;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 证书路径
     */
    private String certPath;

    /**
     * key为商户平台设置的密钥key
     */
    private String key;
    /**
     * 连接超时时间设置
     */
    private int httpConnectTimeoutMs;
    /**
     * 读取超时时间设置
     */
    private int httpReadTimeoutMs;
    /**
     * 回调地址
     */
    private String notifyUrl;
    /**
     * 是否上报
     */
    private boolean autoReport;
    /**
     * 是否是沙盒环境
     */
    private boolean useSandbox;


    private String wxurl = "https://api.weixin.qq.com/sns/jscode2session";


    /**
     * 获取openId
     *
     * @param code
     * @return
     */
    public String getOpenId(String code) {
        String url = wxurl + "?appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String data = responseEntity.getBody();
            JSONObject jsonObj = JSON.parseObject(data);
            String openId = jsonObj.getString("openid");
            return openId;
        }
        return null;
    }

    public MyConfig getMyConfig() throws Exception {
        MyConfig config = new MyConfig(appId, mchId, key, httpConnectTimeoutMs, httpReadTimeoutMs, certPath);
        return config;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getHttpConnectTimeoutMs() {
        return httpConnectTimeoutMs;
    }

    public void setHttpConnectTimeoutMs(int httpConnectTimeoutMs) {
        this.httpConnectTimeoutMs = httpConnectTimeoutMs;
    }

    public int getHttpReadTimeoutMs() {
        return httpReadTimeoutMs;
    }

    public void setHttpReadTimeoutMs(int httpReadTimeoutMs) {
        this.httpReadTimeoutMs = httpReadTimeoutMs;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public boolean isAutoReport() {
        return autoReport;
    }

    public void setAutoReport(boolean autoReport) {
        this.autoReport = autoReport;
    }

    public boolean isUseSandbox() {
        return useSandbox;
    }

    public void setUseSandbox(boolean useSandbox) {
        this.useSandbox = useSandbox;
    }
}
