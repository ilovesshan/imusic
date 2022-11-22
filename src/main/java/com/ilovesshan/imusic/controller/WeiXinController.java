package com.ilovesshan.imusic.controller;

import com.ilovesshan.imusic.common.R;
import com.ilovesshan.imusic.exception.CustomException;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/22
 * @description:
 */

@RestController
@RequestMapping("/weixin")
public class WeiXinController {

    @Value("${WX_TOKEN_KEY}")
    private String token;

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/receive")
    public String receiveWxToken(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println("signature: " + signature);
        System.out.println("timestamp: " + timestamp);
        System.out.println("nonce: " + nonce);
        System.out.println("echostr: " + echostr);
        String[] params = new String[]{nonce, timestamp, token};
        Arrays.sort(params);
        String signatureResult = DigestUtils.sha1Hex(params[0] + params[1] + params[2]);
        //校验签名
        if (!signatureResult.equals(signature)) {
            throw new CustomException("signature is not the same wechat signature is " + signature + " signatureResult is " + signatureResult);
        }
        return echostr;
    }

    @GetMapping("/accessUrl")
    public String accessUrl(@RequestParam String redirectUrl) {
        String authorizationUrl = wxMpService.getOAuth2Service().buildAuthorizationUrl(wxMpService.getOAuth2Service().buildAuthorizationUrl(redirectUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null), WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
        return authorizationUrl;
    }

    @GetMapping("/authorization/{authorizationCode}")
    public R authorization(@PathVariable String authorizationCode) throws WxErrorException {
        WxOAuth2AccessToken wxOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(authorizationCode);
        WxOAuth2UserInfo userInfo = wxMpService.getOAuth2Service().getUserInfo(wxOAuth2AccessToken, null);
        return R.success(R.SUCCESS_MESSAGE_LOGIN, userInfo);
    }
}
