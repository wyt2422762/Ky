package com.fdkj.ky.api.util;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.api.model.system.User;
import com.fdkj.ky.error.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 接口基础类
 * @author wyt
 */
public class BaseApi {
    private static final Logger logger = LoggerFactory.getLogger(BaseApi.class);

    @Value("${bus.api.baseUrl}")
    public String baseUrl;

    @Autowired
    public RestTemplate restTemplate;

    /**
     * 获取用户token
     *
     * @param userName 账号
     * @param password 密码
     * @return token
     */
    public JSONObject getUserToken(String userName, String password) {
        //请求体参数
        JSONObject jo = new JSONObject();
        jo.put("username", userName);
        jo.put("password", password);
        jo.put("fk_userid", "");
        //组装请求体
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(jo, null);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(baseUrl + "/api/User/YHGetToken",
                        HttpMethod.POST, requestEntity, String.class);
        String responseEntityBody = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(responseEntityBody);

        boolean success = jsonObject.getBooleanValue("Success");
        if (!success) {
            logger.error("获取用户token失败，请求url: " + baseUrl + "/api/User/YHGetToken");
            logger.error("获取用户token失败，请求体: " + jo.toJSONString());
            logger.error("获取用户token失败，返回内容: " + responseEntityBody);
            throw new BusinessException(jsonObject.getString("Message"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        String token = jsonObject.getString("Message");
        User user = jsonObject.getObject("Results", User.class);

        JSONObject res = new JSONObject();
        res.put("token", token);
        res.put("user", user);
        return res;
    }

    /**
     * 从cookie中获取用户
     *
     * @param request req
     * @return 用户user
     */
    public User getUserFromCookie(HttpServletRequest request)  {
        try {
            User user = null;
            //获取当前登陆的用户信息(从cookie中获取)
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("user".equals(cookie.getName())) {
                        user = JSONObject.parseObject(URLDecoder.decode(cookie.getValue(), "utf-8"), User.class);
                    }
                }
            }
            return user;
        } catch (Exception e) {
            throw new BusinessException("获取用户失败", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    /**
     * 从cookie中获取token
     *
     * @param request req
     * @return token
     */
    public String getTokenFromCookie(HttpServletRequest request) {
        String token = null;
        //获取当前登陆的用户信息(从cookie中获取)
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }

    /**
     * 获取请求头
     * @param request req
     * @return res
     */
    public HttpHeaders getHttpHeaders(HttpServletRequest request) {
        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Bearer", getTokenFromCookie(request));
        return headers;
    }
}
