package com.kingyee.common.medlive;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.kingyee.common.jackson.JacksonMapper;
import org.apache.xmlbeans.impl.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.zip.CRC32;

/**
 * 医脉通登录
 * 文档
 * hosts 211.103.157.135 doc.medlivedev.cn
 * http://doc.medlivedev.cn/web/#/item/index
 *
 * @date 2020/11/9
 * @desc
 * @功能1: 实现医脉通第三方授权登录并获得用户信息
 * @功能2: 根据医脉通id获取用户信息
 * <p>
 * 步骤:
 * 1: 前台展示 EMT_AUTH,回调到自己的一个url
 * 2: 在回调的url中 redirect到 EMT_USER_INFO 继续回调至自己的url,此时可得到用户参数(包含id,昵称,头像,认证,注册时间)
 * 3: 如需完整的用户信息,请继续使用功能2
 */
public class MedliveAuth {
    //userinfo解密key
    private static final String AES_KEY = "kEV7TXRS6k8z1uEr";

    //登录授权接口
    private static final String EMT_AUTH = "https://www.medlive.cn/auth/login?service=";
    //退出
    private static final String EMT_LOGOUT = "https://www.medlive.cn/auth/logout?service=";
    //登录接口获取用户信息
    private static final String EMT_USER_INFO = "http://www.medlive.cn/pgtoken/userinfo.php?rtn_url=";
    //根据医脉通id获取用户信息
    private static final String GET_USER_INFO = "https://api.medlive.cn/user/get_user_info.php?hashid=%s&checkid=%s";
    /**
     * 认证
     * https://m.medlive.cn/certify?url={url}&source=wap&app_name=nuohua&from_spread=itphome
     */
    public static final String EMT_USER_CERTIFY = "https://m.medlive.cn/certify";

    /**
     * 获取完整用户信息
     *
     * @param userInfo
     * @return
     */
    public static MedliveUserBean getUserInfo(String userInfo) {
        MedliveUserBean bean = decryptUserInfo(userInfo);
        return getUserInfo(bean.getUser_id());
    }

    /**
     * 根据医脉通ID取得完整用户信息
     *
     * @param userId
     * @return
     */
    public static MedliveUserBean getUserInfo(Long userId) {
        try {
            String[] userHashInfo = hashUserInfo(userId);
            String userInfo = HttpUtil.get(String.format(GET_USER_INFO, userHashInfo[0], userHashInfo[1]));
            JsonNode data = JacksonMapper.parse(userInfo);
            if (data.has("data")) {
                return JacksonMapper.jsonToBean(data.get("data"), MedliveUserBean.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密用户信息
     * 本接口仅能获取
     * 用户id,昵称,头像,认证情况,注册时间,注册来源
     * 如需更详细信息请根据id 获取
     * {"user_id":"109471","nick":"\u9646\u5c0f\u51e4","thumb":"http:\/\/webres.medlive.cn\/upload\/thumb\/000\/176\/904_big",
     * "certify_flg":"","reg_time":"2008-07-04 15:37:14","reg_from":"medlive"}
     *
     * @param userinfo
     * @return
     */
    public static MedliveUserBean decryptUserInfo(String userinfo) {
        try {
            userinfo = decryptAES(userinfo);
            JsonNode data = JacksonMapper.parse(userinfo);
            return JacksonMapper.jsonToBean(data, MedliveUserBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算参数
     *
     * @param userid
     * @param encodeStr
     * @return
     */
    private static String hashUser(Long userid, String encodeStr) {
        final String key = "asdfwrew.USER_SEED";
        final String key2 = "werhhs.USER_SEED2";

        CRC32 crc32 = new CRC32();
        crc32.update((encodeStr + key).getBytes());
        long hash = crc32.getValue() - userid;

        crc32 = new CRC32();
        crc32.update((hash + key2).getBytes());
        long hash2 = crc32.getValue();

        String temp = String.valueOf(hash2);
        String k1 = temp.substring(0, 3);
        String k2 = temp.substring(temp.length() - 2);
        return k1 + hash + k2;
    }
    /**
     * 构造参数
     *
     * @param userid
     * @return
     */
    private static String[] hashUserInfo(Long userid) {
        String[] infos = new String[2];
        //hashId固定参数
        infos[0] = hashUser(userid, "dasfgfsdbz");
        //checkId固定参数
        infos[1] = hashUser(userid, "hiewrsbzxc");
        return infos;
    }

    /**
     * 解密用户信息
     *
     * @param input
     * @return
     */
    private static final String decryptAES(String input) {
        String key = AES_KEY;
        byte[] output = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64.decode(input.getBytes()));
        } catch (Exception e) {
            return null;
        }

        return new String(output);
    }

    /**
     * 登录页面
     *
     * @param callBackUrl
     * @return
     */
    public static String getAuthUrl(String callBackUrl) {
        try {
            return EMT_AUTH + URLEncoder.encode(callBackUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 退出
     *
     * @param callBackUrl
     * @return
     */
    public static String getLogoutUrl(String callBackUrl) {
        try {
            return EMT_LOGOUT + URLEncoder.encode(callBackUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 登录成功后获取用户信息
     *
     * @param callBackUrl
     * @return
     */
    public static String getUserInfoUrl(String callBackUrl) {
        try {
            return EMT_USER_INFO + URLEncoder.encode(callBackUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 认证
     *
     * @param callBackUrl
     * @return
     */
    public static String getCertifyUrl(String callBackUrl, String appName) {
        try {
            return EMT_USER_CERTIFY + "?url=" + URLEncoder.encode(callBackUrl, "utf-8") + "&source=wap&app_name=" + appName +
                    "&from_spread=" + appName;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        //System.out.println(getUserInfo(109471L));
        //System.out.println(getAuthUrl("http://dev1-lm.kydev.net/doctor/auth"));
        MedliveUserBean bean = MedliveAuth.decryptUserInfo("NZzsgwnTgFGok06P1%2FdIi9oVP53mCMYbEXs%2F3uhg3MRkcajv8UYeFGpBC1EJ4sdD");
        System.out.println(bean);
    }
}
