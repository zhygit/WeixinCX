package com.zhy.Service.impl;

import com.zhy.Service.CoreService;
import com.zhy.Utils.MessageUtils;
import com.zhy.Utils.XmlUtils;
import com.zhy.beans.message.req.TextMessage;
import com.zhy.beans.message.resp.TextMessageResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by zhy on 2017/5/1.
 */

@Service
public class CoreServiceImpl implements CoreService{
    public static Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);


    public String processTextMessge(Map<String, String> map) {
        String replyContent = "Hello! This is my Reply! "+ map.get("Content");
        return replyTextMsg(map, replyContent);
//        replyWhatYouSay(map);

    }

    public String replyTextMsg(Map<String,String> map, String replyContent) {

        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");
        TextMessageResp textMessageResp = new TextMessageResp();
        textMessageResp.setToUserName(fromUserName);
        textMessageResp.setFromUserName(toUserName);
        textMessageResp.setCreateTime(new Date().getTime());
        textMessageResp.setMsgType(MessageUtils.RESP_MESSAGE_TYPE_TEXT);
        textMessageResp.setFuncFlag(0);
        textMessageResp.setContent(replyContent);
        return XmlUtils.textMessageToXml(textMessageResp);
    }

    public String replyWhatYouSay(Map<String,String> map){
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");
        TextMessageResp textMessageResp = new TextMessageResp();
        textMessageResp.setToUserName(fromUserName);
        textMessageResp.setFromUserName(toUserName);
        textMessageResp.setCreateTime(new Date().getTime());
        textMessageResp.setMsgType(MessageUtils.RESP_MESSAGE_TYPE_TEXT);
        textMessageResp.setFuncFlag(0);
        textMessageResp.setContent(map.get("Content"));
        return XmlUtils.textMessageToXml(textMessageResp);

    }
}
