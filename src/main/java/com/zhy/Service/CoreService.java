package com.zhy.Service;

import java.util.Map;

/**
 * Created by zhy on 2017/5/1.
 */
public interface CoreService {

    public String processTextMessge(Map<String, String> map);

    public String replyTextMsg(Map<String, String> map, String replyContent);


}
