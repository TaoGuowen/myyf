package com.btyc.modules.oss.cloud;


import com.btyc.common.utils.ConfigConstant;
import com.btyc.common.utils.Constant;
import com.btyc.common.utils.SpringContextUtils;
import com.btyc.modules.sys.service.SysConfigService;
import com.btyc.common.utils.ConfigConstant;
import com.btyc.common.utils.Constant;
import com.btyc.common.utils.SpringContextUtils;
import com.btyc.modules.sys.service.SysConfigService;

/**
 * 文件上传Factory
 *
 * @author ams
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
