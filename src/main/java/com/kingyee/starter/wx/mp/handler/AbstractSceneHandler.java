package com.kingyee.starter.wx.mp.handler;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public abstract class AbstractSceneHandler extends AbstractHandler {


    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage, WxMpService wxMpService, String scene) throws Exception {
        // scene：代表id
        Long representId = Long.parseLong(scene);
        String openId = wxMessage.getFromUser();
        //TODO 做处理
        return null;
    }
}