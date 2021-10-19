package com.fdkj.ky.api.util.sys;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.api.model.system.User;
import com.fdkj.ky.api.model.system.Xy;
import com.fdkj.ky.api.util.BaseApi;
import com.fdkj.ky.base.Page;
import com.fdkj.ky.error.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学院接口
 *
 * @author wyt
 */
@Component
public class XyApi extends BaseApi {
    private static final Logger logger = LoggerFactory.getLogger(XyApi.class);

    /**
     * 获取学院信息列表(分页)
     *
     * @param request   req
     * @param reqParams 请求参数
     * @param reqBody   请求体
     * @param pageNo    第几页
     * @param pageSize  每页显示多少条
     * @return res
     * @throws Exception err
     */
    public Page<Xy> getXyList(HttpServletRequest request, Map<String, Object> reqParams, Map<String, Object> reqBody, Integer pageNo, Integer pageSize) throws Exception {
        User user = getUserFromCookie(request);
        //请求头
        HttpHeaders headers = getHttpHeaders(request);

        //请求体
        JSONObject body = new JSONObject();
        body.put("fk_xtglid", user.getFk_xtglid());
        if (reqBody != null && !reqBody.isEmpty()) {
            body.putAll(reqBody);
        }

        //组装请求体
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(body, headers);

        //请求参数
        Map<String, Object> params = new HashMap<>(4);
        params.put("page", pageNo == null ? 1 : pageNo);
        params.put("pageNum", pageSize == null ? 10 : pageSize);

        String url = baseUrl + "/api/CZF/KY_XY_List";

        if (reqParams != null && !reqParams.isEmpty()) {
            params.putAll(reqParams);
        }

        List<String> paramList = new ArrayList<>();
        params.forEach((key, value) -> paramList.add(key + "={" + key + "}"));

        if (!paramList.isEmpty()) {
            url += ("?" + String.join("&", paramList));
        }

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, params);

        String responseEntityBody = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(responseEntityBody);

        boolean success = jsonObject.getBooleanValue("Success");
        if (!success) {
            logger.error("获取学院列表失败，请求url: " + baseUrl + "/api/CZF/YS_XMXX_List");
            logger.error("获取学院列表失败，请求参数: " + params);
            logger.error("获取学院列表失败，请求体: " + body.toJSONString());
            logger.error("获取学院列表失败，返回内容: " + responseEntityBody);
            throw new BusinessException(jsonObject.getString("Message"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        //构造返回信息
        Page<Xy> page = new Page<>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize);
        Integer totalRecord = jsonObject.getInteger("TotalCount");
        page.setTotalRecord(totalRecord);
        List<Xy> dataList = jsonObject.getJSONArray("Results").toJavaList(Xy.class);
        page.setDataList(dataList);

        return page;
    }

    /**
     * 获取学院信息列表
     *
     * @param request   req
     * @param reqParams 请求参数
     * @param reqBody   请求体
     * @return res
     * @throws Exception err
     */
    public List<Xy> getXyList(HttpServletRequest request, Map<String, Object> reqParams, Map<String, Object> reqBody) {
        User user = getUserFromCookie(request);
        //请求头
        HttpHeaders headers = getHttpHeaders(request);

        //请求体
        JSONObject body = new JSONObject();
        body.put("fk_xtglid", user.getFk_xtglid());
        if (reqBody != null && !reqBody.isEmpty()) {
            body.putAll(reqBody);
        }

        //组装请求体
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(body, headers);

        //请求参数
        Map<String, Object> params = new HashMap<>(4);

        String url = baseUrl + "/api/CZF/KY_XY_List";

        if (reqParams != null && !reqParams.isEmpty()) {
            params.putAll(reqParams);
        }

        List<String> paramList = new ArrayList<>();
        params.forEach((key, value) -> paramList.add(key + "={" + key + "}"));

        if (!paramList.isEmpty()) {
            url += ("?" + String.join("&", paramList));
        }

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, params);

        String responseEntityBody = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(responseEntityBody);

        boolean success = jsonObject.getBooleanValue("Success");
        if (!success) {
            logger.error("获取学院列表失败，请求url: " + baseUrl + "/api/CZF/YS_XMXX_List");
            logger.error("获取学院列表失败，请求参数: " + params);
            logger.error("获取学院列表失败，请求体: " + body.toJSONString());
            logger.error("获取学院列表失败，返回内容: " + responseEntityBody);
            throw new BusinessException(jsonObject.getString("Message"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        //构造返回信息
        return jsonObject.getJSONArray("Results").toJavaList(Xy.class);
    }

    /**
     * 获取学院详情
     *
     * @param request req
     * @param id      学院id
     * @return res
     */
    public Xy getXyDetail(HttpServletRequest request, String id) throws Exception {
        //请求头
        HttpHeaders headers = getHttpHeaders(request);
        //请求体
        JSONObject body = new JSONObject();
        //body.put("fk_xtglid", user.getFk_xtglid());

        //组装请求体
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(body, headers);

        //请求参数
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);

        String url = baseUrl + "/api/CZF/KY_XY_Model?id={id}";
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(url,
                        HttpMethod.POST, requestEntity, String.class, params);
        String responseEntityBody = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(responseEntityBody);

        boolean success = jsonObject.getBooleanValue("Success");
        if (!success) {
            logger.error("获取学院详情失败，请求url: " + baseUrl + "/api/CZF/KY_XY_Model");
            logger.error("获取学院详情失败，请求体: " + body.toJSONString());
            logger.error("获取学院详情失败，请求参数: " + params);
            logger.error("获取学院详情失败，返回内容: " + responseEntityBody);
            throw new BusinessException(jsonObject.getString("Message"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return jsonObject.getObject("Results", Xy.class);
    }

    /**
     * 更新添加学院
     *
     * @param request req
     * @param body    请求体
     */
    public void aeXy(HttpServletRequest request, JSONObject body) {
        //请求头
        HttpHeaders headers = getHttpHeaders(request);
        //组装请求体
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(baseUrl + "/api/CZF/KY_XY_Update",
                        HttpMethod.POST, requestEntity, String.class);
        String responseEntityBody = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(responseEntityBody);
        boolean success = jsonObject.getBooleanValue("Success");
        if (!success) {
            logger.error("更新添加学院失败，请求url: " + baseUrl + "/api/CZF/KY_XY_Update");
            logger.error("更新添加学院失败，请求体: " + body.toJSONString());
            logger.error("更新添加学院失败，返回内容: " + responseEntityBody);
            throw new BusinessException(jsonObject.getString("Message"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    /**
     * 删除学院
     *
     * @param request req
     * @param id      学院id
     */
    public void delXy(HttpServletRequest request, String id) {
        //请求头
        HttpHeaders headers = getHttpHeaders(request);
        //组装请求体
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(null, headers);
        //参数
        Map<String, String> params = new HashMap<>(1);
        params.put("id", id);
        //请求
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(baseUrl + "/api/CZF/KY_XY_Del?id={id}",
                        HttpMethod.POST, requestEntity, String.class, params);
        String responseEntityBody = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(responseEntityBody);
        boolean success = jsonObject.getBooleanValue("Success");
        if (!success) {
            logger.error("删除学院失败，请求url: " + baseUrl + "/api/CZF/KY_XY_Del");
            logger.error("删除学院失败，请求参数: " + params);
            logger.error("删除学院失败，返回内容: " + responseEntityBody);
            throw new BusinessException(jsonObject.getString("Message"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
