package com.btyc.wxpay;

import java.io.*;

public class MyConfig extends WXPayConfig {

    /**
     * 小程序id
     */
    private String appId;
    /**
     * 商户号
     */
    private String mchId;
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

    private byte[] certData;

    /**
     *
     * @param appId
     * @param mchId
     * @param key
     * @param httpConnectTimeoutMs
     * @param httpReadTimeoutMs
     * @param certPath
     * @throws Exception
     */
    public MyConfig(String appId, String mchId, String key, int httpConnectTimeoutMs, int httpReadTimeoutMs, String certPath) throws IOException {
        this.appId = appId;
        this.mchId = mchId;
        this.key = key;
        this.httpConnectTimeoutMs = httpConnectTimeoutMs;
        this.httpReadTimeoutMs = httpReadTimeoutMs;
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public String getAppID() {
        return appId;
    }

    public String getMchID() {
        return mchId;
    }

    public String getKey() {
        return key;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return httpConnectTimeoutMs;
    }

    public int getHttpReadTimeoutMs() {
        return httpReadTimeoutMs;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return null;
    }

}
