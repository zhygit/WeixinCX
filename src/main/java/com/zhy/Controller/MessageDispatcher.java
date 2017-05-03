package com.zhy.Controller;

import com.zhy.Service.CoreService;
import com.zhy.Service.impl.CoreServiceImpl;
import com.zhy.Utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zhy on 2017/5/1.
 */
public class MessageDispatcher {
    public static Logger log = LoggerFactory.getLogger(MessageDispatcher.class);


    private CoreServiceImpl coreServiceImpl;


    public String processMessage(Map<String, String> map) {
        String msgType = map.get("MsgType");
        coreServiceImpl = new CoreServiceImpl();
        String reply = "";
        switch (msgType) {
            case MessageUtils.REQ_MESSAGE_TYPE_TEXT:
                log.info("======  Text Message  ======");
                reply = coreServiceImpl.processTextMessge(map);
                break;
            case MessageUtils.REQ_MESSAGE_TYPE_VOICE:
                log.info("======  Voice Message  ======");
                break;
            case MessageUtils.REQ_MESSAGE_TYPE_IMAGE:
                log.info("======  Image Message  ======");
                break;
            case MessageUtils.REQ_MESSAGE_TYPE_LOCATION:
                log.info("======  Location Message  ======");
                break;
            case MessageUtils.REQ_MESSAGE_TYPE_LINK:
                log.info("======  Link Message  ======");
                break;
            case MessageUtils.REQ_MESSAGE_TYPE_VIDEO:
                log.info("======  Video Message  ======");
                break;
            case MessageUtils.REQ_MESSAGE_TYPE_EVENT:
                log.info("======  Enent Message  ======");
                break;
        }
        return reply;
    }
}
