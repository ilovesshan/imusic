package com.ilovesshan.imusic.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private static final String TOKEN = "weixinToken";

    @RequestMapping("/receive")
    public void receiveWxToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println("signature: " + signature);
        System.out.println("timestamp: " + timestamp);
        System.out.println("nonce: " + nonce);
        System.out.println("echostr: " + echostr);
        String[] params = new String[]{nonce, timestamp, TOKEN};
        Arrays.sort(params);
        String signatureResult = DigestUtils.sha1Hex(params[0] + params[1] + params[2]);
        //校验签名
        if (!signatureResult.equals(signature)) {
            throw new RuntimeException("signature is not the same wechat signature is " + signature + " signatureResult is " + signatureResult);
        }
        response.getWriter().write(echostr);
    }
}
