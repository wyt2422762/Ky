package com.fdkj.ky.api.util.ky.xm.zxxm;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.api.model.ky.xm.zxxm.Zxxm;
import com.fdkj.ky.api.model.system.User;
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
 * 纵向项目接口
 *
 * @author wyt
 */
@Component
public class ZxxmApi extends BaseApi {
    private static final Logger logger = LoggerFactory.getLogger(ZxxmApi.class);

    /**
     * 获取纵向项目列表(分页)
     *
     * @param request   req
     * @param reqParams 请求参数
     * @param reqBody   请求体
     * @param pageNo    第几页
     * @param pageSize  每页显示多少条
     * @return res
     */
    public Page<Zxxm> getList(HttpServletRequest request, Map<String, Object> reqParams, Map<String, Object> reqBody, Integer pageNo, Integer pageSize) {
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

        String url = baseUrl + "/api/CZF/KY_ZXXM_List";

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
            logger.error("获取纵向项目列表失败，请求url: " + baseUrl + "/api/CZF/KY_ZXXM_List");
            logger.error("获取纵向项目列表失败，请求参数: " + params);
            logger.error("获取纵向项目列表失败，请求体: " + body.toJSONString());
            logger.error("获取纵向项目列表失败，返回内容: " + responseEntityBody);
            throw new BusinessException(jsonObject.getString("Message"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        //构造返回信息
        Page<Zxxm> page = new Page<>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize);
        Integer totalRecord = jsonObject.getInteger("TotalCount");
        page.setTotalRecord(totalRecord);
        List<Zxxm> dataList = jsonObject.getJSONArray("Results").toJavaList(Zxxm.class);
        page.setDataList(dataList);

        return page;
    }

    /**
     * 获取纵向项目列表
     *
     * @param request   req
     * @param reqParams 请求参数
     * @param reqBody   请求体
     * @return res
     */
    public List<Zxxm> getList(HttpServletRequest request, Map<String, Object> reqParams, Map<String, Object> reqBody) {
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

        String url = baseUrl + "/api/CZF/KY_ZXXM_List";

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
            logger.error("获取纵向项目列表失败，请求url: " + baseUrl + "/api/CZF/KY_ZXXM_List");
            logger.error("获取纵向项目列表失败，请求参数: " + params);
            logger.error("获取纵向项目列表失败，请求体: " + body.toJSONString());
            logger.error("获取纵向项目列表失败，返回内容: " + responseEntityBody);
            throw new BusinessException(jsonObject.getString("Message"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        //构造返回信息
        return jsonObject.getJSONArray("Results").toJavaList(Zxxm.class);
    }

    /**
     * 获取纵向项目详情
     *
     * @param request req
     * @param id      纵向项目id
     * @return res
     */
    public Zxxm getDetail(HttpServletRequest request, String id) throws Exception {
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

        String url = baseUrl + "/api/CZF/KY_ZXXM_Model?id={id}";
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(url,
                        HttpMethod.POST, requestEntity, String.class, params);
        String responseEntityBody = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(responseEntityBody);

        boolean success = jsonObject.getBooleanValue("Success");
        if (!success) {
            logger.error("获取纵向项目详情失败，请求url: " + baseUrl + "/api/CZF/KY_ZXXM_Model");
            logger.error("获取纵向项目详情失败，请求体: " + body.toJSONString());
            logger.error("获取纵向项目详情失败，请求参数: " + params);
            logger.error("获取纵向项目详情失败，返回内容: " + responseEntityBody);
            throw new BusinessException(jsonObject.getString("Message"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return jsonObject.getObject("Results", Zxxm.class);
    }

    /**
     * 删除纵向项目
     *
     * @param request req
     * @param id      纵向项目id
     */
    public void del(HttpServletRequest request, String id) {
        //请求头
        HttpHeaders headers = getHttpHeaders(request);
        //组装请求体
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(null, headers);
        //参数
        Map<String, String> params = new HashMap<>(1);
        params.put("id", id);
        //请求
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(baseUrl + "/api/CZF/KY_ZXXM_Del?id={id}",
                        HttpMethod.POST, requestEntity, String.class, params);
        String responseEntityBody = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(responseEntityBody);
        boolean success = jsonObject.getBooleanValue("Success");
        if (!success) {
            logger.error("删除纵向项目失败，请求url: " + baseUrl + "/api/CZF/KY_ZXXM_Del");
            logger.error("删除纵向项目失败，请求参数: " + params);
            logger.error("删除纵向项目失败，返回内容: " + responseEntityBody);
            throw new BusinessException(jsonObject.getString("Message"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    /**
     * 更新添加纵向项目
     *
     * @param request req
     * @param body    请求体
     */
    public void ae(HttpServletRequest request, JSONObject body) {
        //请求头
        HttpHeaders headers = getHttpHeaders(request);
        //组装请求体
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(baseUrl + "/api/CZF/KY_ZXXM_Update",
                        HttpMethod.POST, requestEntity, String.class);
        String responseEntityBody = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(responseEntityBody);
        boolean success = jsonObject.getBooleanValue("Success");
        if (!success) {
            logger.error("更新添加纵向项目失败，请求url: " + baseUrl + "/api/CZF/KY_ZXXM_Update");
            logger.error("更新添加纵向项目失败，请求体: " + body.toJSONString());
            logger.error("更新添加纵向项目失败，返回内容: " + responseEntityBody);
            throw new BusinessException(jsonObject.getString("Message"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
