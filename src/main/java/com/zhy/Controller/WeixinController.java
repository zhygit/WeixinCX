package com.zhy.Controller;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by zhy on 2017/4/16.
 */

@Controller
@RequestMapping(value = "/api")
public class WeixinController {
    private String token = "cxwork";
    private String encodingAESKey = "AqHCZ38M5LV8FUIoekdHSKfNRlQ1ew41LcB2Cd1vza5";
    private String appId = "wxb81c4d22981b40cb";


    public static final Logger logger = LoggerFactory.getLogger(WeixinController.class);

    @RequestMapping(value = "cxwork", method = RequestMethod.GET)
    public String verifyURLGet(@RequestParam(required = false) String signature, @RequestParam(required = false) String timestamp,
                            @RequestParam(required = false) String nonce, @RequestParam(required = false) String echostr) {

        logger.info("WxSignature: "+signature+ " | Wxtimestamp: "+timestamp+" | Wxnonce: "+nonce+ " | Wxechostr: "+echostr);
        if (doValid(signature, token, timestamp, nonce)) {
            logger.info("Valid WxURL Success!");
            logger.info("Reture echostr: "+echostr);
            return echostr;
        } else
            return "Sorry! Please tell me who you are!";

    }

//    @RequestMapping(value = "cxwork", method = RequestMethod.POST)
//    public String mainController(@RequestParam(required = false) String signature){
//        return null;
//    }

    @RequestMapping(value = "getAllParam")
    public void getAllParams(HttpServletRequest request, HttpServletResponse response){
        logger.info(request.toString());
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));
        }
        logger.info("Print all params");

    }


    private String MyCalculateSignaure(String token, String timestamp, String nonce) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(token);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        String sig = DigestUtils.sha1Hex(list.get(0) + list.get(1) + list.get(2));
        logger.info("sig: " + sig);
        return sig;
    }

    public boolean doValid(String WxSignature, String token, String timestamp, String nonce) {
        String sig = MyCalculateSignaure(token, timestamp, nonce);
        if (sig.equals(WxSignature)) {
            logger.info("Calcutaled Sig : "+sig);
            logger.info("WxSignature    : "+WxSignature);
            return true;
        } else {
            return false;
        }

    }

    public static  void  main(String args[]){
        String signature = "90e223174681fb8df67d534a611b2461288c7d11";
        String echostr = "5303853878407905278";
        String timestamp="1493479902";
        String nonce="200001779";
        String token = "cxwork";
        String encodingAESKey = "AqHCZ38M5LV8FUIoekdHSKfNRlQ1ew41LcB2Cd1vza5";
        String appId = "wxb81c4d22981b40cb";

        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey, appId);
//            SHA1 SHA1= new SHA1();
            String signature2 = SHA1.getSHA1(token, timestamp, nonce, echostr);
            String result = wxcpt.verifyUrl(signature, timestamp, nonce, echostr);

            logger.info("result :"+signature2);
            logger.info("signat :"+signature);
        } catch (AesException e) {
            e.printStackTrace();
        }

    }


}
