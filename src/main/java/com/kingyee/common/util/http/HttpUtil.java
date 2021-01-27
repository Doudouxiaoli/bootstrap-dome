package com.kingyee.common.util.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * HttpUtil工具类
 */
public class HttpUtil {

    private static final int BUFFER_SIZE = 1024;

    private static String[] SPIDERS = {"Googlebot", "msnbot", "Baiduspider", "bingbot", "Sogou web spider",
            "Sogou inst spider", "Sogou Pic Spider", "JikeSpider", "Sosospider", "Slurp", "360Spider", "YodaoBot",
            "OutfoxBot", "fast-webcrawler", "lycos_spider", "scooter", "ia_archiver", "MJ12bot", "AhrefsBot"};

    /**
     * 判断是否是爬虫的访问请求
     */
    public static boolean isRequestFromSpider(HttpServletRequest request) {
        boolean isSpider = false;
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null && userAgent.trim().length() > 0) {
            userAgent = userAgent.trim().toLowerCase();
            for (String spider : SPIDERS) {
                if (userAgent.contains(spider.toLowerCase())) {
                    isSpider = true;
                    break;
                }
            }
        }
        return isSpider;
    }

    /**
     * 取得请求的IP地址
     */
    public static String getRemoteIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (isValidIpAddr(ip)) {
            return ip.split(",")[0];
        }

        ip = request.getHeader("Proxy-Client-IP");
        if (isValidIpAddr(ip)) {
            return ip;
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if (isValidIpAddr(ip)) {
            return ip;
        }

        ip = request.getHeader("HTTP_CLIENT_IP");
        if (isValidIpAddr(ip)) {
            return ip;
        }

        return request.getRemoteAddr();
    }

    /**
     * 判断IP地址是否有效
     */
    private static boolean isValidIpAddr(String ip) {
        return ip != null && !ip.isEmpty() && !ip.equalsIgnoreCase("unknown");
    }

    /**
     * 判断请求是否是Ajax
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(header);
    }

    /**
     * 取得浏览的base路径
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        int port = request.getServerPort();
        String basePath;
        if (port == 80) {
            basePath = request.getScheme() + "://"
                    + request.getServerName() + path + "/";
        } else {
            basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
        }

        return basePath;
    }

    /**
     * 取得当前请求的完全URL
     *
     * @param request
     * @return
     */
    public static String getFullUrl(HttpServletRequest request) {
        return getFullUrl(request, true);
    }

    /**
     * 取得当前请求的完全URL
     *
     * @param encode 是做urlencode
     */
    public static String getFullUrl(HttpServletRequest request, boolean encode) {
        String orginUrl = request.getRequestURL().toString();
        if (null != request.getQueryString()) {
            orginUrl += "?" + request.getQueryString();
        } else {
            Map<String, String[]> parpMap = request.getParameterMap();
            Set<String> keys = parpMap.keySet();
            String value;
            StringBuilder querys = new StringBuilder();
            for (String key : keys) {
                value = parpMap.get(key)[0];
                if (null != value && !value.isEmpty()) {
                    querys.append(key);
                    querys.append("=");
                    querys.append(value);
                    querys.append("&");
                }
            }
            if (querys.length() != 0) {
                orginUrl += "?" + querys.substring(0, querys.length() - 1);
            }
        }
        try {
            if (encode) {
                orginUrl = URLEncoder.encode(orginUrl, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orginUrl;
    }

    /**
     * 判断是否是微信浏览器的访问请求
     */
    public static boolean isWechat(HttpServletRequest request) {
        boolean isSpider = false;
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null && userAgent.trim().length() > 0) {
            userAgent = userAgent.trim().toLowerCase();
            if (userAgent.contains("micromessenger")) {
                isSpider = true;
            }
        }
        return isSpider;
    }


    public static void writeMsg(HttpServletResponse response, String msg) throws IOException {
        // 如果是异步请求 返回权限不足信息
        response.setContentType("text/plain;");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "inline");

        PrintWriter writer = response.getWriter();
        try (StringReader reader = new StringReader(msg)) {
            char[] buffer = new char[BUFFER_SIZE];
            int charRead;
            while ((charRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, charRead);
            }
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
}
