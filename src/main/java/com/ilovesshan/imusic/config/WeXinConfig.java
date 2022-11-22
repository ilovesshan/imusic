package com.ilovesshan.imusic.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/22
 * @description:
 */

@Configuration
public class WeXinConfig {


    @Value("${WX_APP_ID}")
    private String appId;

    @Value("${WX_APP_SECRET}")
    private String appSecret;

    @Bean
    WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(appId);
        config.setSecret(appSecret);
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }

}
