package com.kingyee.starter.wx.mp.handler;

import com.kingyee.starter.SystemConfig;
import com.kingyee.starter.wx.mp.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class ScanHandler extends AbstractSceneHandler {


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        // 扫码事件处理
        // 获取微信用户基本信息
        try {
            WxMpUser wxMpUser = wxMpService.getUserService()
                    .userInfo(wxMessage.getFromUser(), null);
            if (wxMpUser != null) {
                // TODO 可以添加关注用户到本地数据库
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("该公众号没有获取用户信息权限！");
            }
        } catch (Exception e) {
            this.logger.error("添加关注用户到本地数据库出错！");
        }


        WxMpXmlOutMessage responseResult = null;
        String key = wxMessage.getEventKey();
        if (StringUtils.isNotEmpty(key)) {
            try {
                responseResult = this.handleSpecial(wxMessage, wxMpService, key);
            } catch (Exception e) {
                this.logger.error(e.getMessage(), e);
            }
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            StringBuffer messageContent = new StringBuffer();
            messageContent.append("感谢您的关注，欢迎您的到来。请您选择身份进行登录，以便我们可以更好的为您提供服务。\n");
            messageContent.append("<a href='" + SystemConfig.getDomain() + "/admin/index'>点击登录>></a>");
            return new TextBuilder().build(messageContent.toString(), wxMessage, wxMpService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }
        return null;
    }

}
