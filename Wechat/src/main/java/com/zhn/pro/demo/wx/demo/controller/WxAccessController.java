package com.zhn.pro.demo.wx.demo.controller;

import com.zhn.pro.demo.wx.common.utils.XmlUtil;
import com.zhn.pro.demo.wx.demo.config.WxApp;
import com.zhn.pro.demo.wx.session.DataVerification;
import com.zhn.pro.demo.wx.session.entity.InputMessage;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author ZhouHN
 * @ClassName WxAccessController
 * @Description
 * @date 13:50 2019/10/21 0021
 */
@Controller
@RequestMapping("/messageAccess")
public class WxAccessController {

    private final Logger logger = LogManager.getLogger(WxAccessController.class);
    @Autowired
    private WxApp wxApp;

    /**
     * @param signature 微信签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     * @MethodName urlToken
     * @Description 此接口用于接收验证消息是否来自微信服务器
     * @date 14:06 2019/10/21 0021
     */
    @GetMapping
    @ResponseBody
    private String urlToken(String signature, String timestamp, String nonce, String echostr) {
        logger.debug("signature : " + signature + " timestamp : " + timestamp + " nonce : " + nonce);
        if (DataVerification.verifyMsg(signature, timestamp, nonce, wxApp.getConfig().getToken()))
            return echostr;
        return "failure";
    }

    /**
     * @MethodName messageReceiveAndHandle
     * @Description 此接口接收微信会话界面的所有消息
     * 安全性：有明文，兼容，加密三种方式，本接口兼容三种模式，所以在接收时进行判断消息安全模式，进而分派处理。
     * 相关参数：signature，timestamp，nonce，msg_signature，encrypt_type 这些参数来自URL中，用于验证消息，
     * 详细内容在请求体中
     * 1. 非加密时有：signature，timestamp，nonce 和 请求体消息（包含具体的xml消息）
     * 2. 加密时有：msg_signature，timestamp，nonce，encrypt_type（值为aes） 和 请求体密文消息
     * @date 18:47 2019/10/21 0021
     */
    @PostMapping
    public void messageReceiveAndHandle(String timestamp, String nonce,
                                        @RequestParam(required = false) String encrypt_type,
                                        @RequestParam(required = false) String msg_signature,
                                        @RequestParam(required = false) String signature,
                                        // @RequestBody String postData,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws JAXBException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 1. 在获取的参数来判断消息所处的安全模式。
        // 2. 下列方式为加密模式判断。
        if (encrypt_type != null && encrypt_type.equals("aes")) {
            // 3. 在解密微信的详细消息时，先进行消息校验，错误则返回失败。
            if (!DataVerification.verifyMsg(msg_signature, timestamp, nonce, wxApp.getConfig().getToken()))
                response.getWriter().write("failure");
            // 4. 将消息（微信对话消息格式xml字符串）格式化为消息对象
            // 5. 将消息对象交由分发处理器处理得到结果并返回
        } else {
            // 未加密
            if (!DataVerification.verifyMsg(signature, timestamp, nonce, wxApp.getConfig().getToken()))
                response.getWriter().write("failure");
            // String string = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
            // response.getWriter().write("success");

            InputMessage in = XmlUtil.toJavaObj(request.getInputStream(), InputMessage.class);
            String result = wxApp.getDispatchHandler().doDispatch(in);
            response.getWriter().write(result);
        }
    }
}
