package com.fdkj.ky.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.model.system.Tz;
import com.fdkj.ky.api.model.system.TzFj;
import com.fdkj.ky.api.model.system.User;
import com.fdkj.ky.api.model.system.Xy;
import com.fdkj.ky.api.util.sys.TzApi;
import com.fdkj.ky.base.CusResponseBody;
import com.fdkj.ky.base.Page;
import com.fdkj.ky.config.BusConfig;
import com.fdkj.ky.constant.Constants;
import com.fdkj.ky.controller.BaseController;
import com.fdkj.ky.error.BusinessException;
import com.fdkj.ky.utils.DateUtils;
import com.fdkj.ky.utils.StringUtils;
import com.fdkj.ky.utils.file.FileUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 通知公告
 * @author wyt
 */
@Controller
@RequestMapping("CZF/TZ")
@Log(module = "通知")
public class TzController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(TzController.class);

    @Autowired
    private TzApi tzApi;

    /**
     * 跳转
     *
     * @param request req
     * @param opts    操作权限
     * @return res
     * @throws Exception err
     */
    @RequestMapping("Index")
    public ModelAndView index(HttpServletRequest request,
                              @RequestParam(value = "opts", required = false) List<String> opts) throws Exception {
        request.setAttribute("cuser", tzApi.getUserFromCookie(request));
        request.setAttribute("opts", opts);
        if (opts != null && !opts.isEmpty()) {
            String s = StringUtils.join(opts, ",");
            request.setAttribute("optsStr", s);
        }
        return new ModelAndView("system/tzMgr/tz_index");
    }

    /**
     * 跳转到添加页面
     *
     * @param request req
     * @return res
     * @throws Exception err
     */
    @RequestMapping("toInfo/{id}")
    public ModelAndView toAdd(HttpServletRequest request, @PathVariable String id) throws Exception {
        request.setAttribute("cuser", tzApi.getUserFromCookie(request));
        //request.setAttribute("id", id);
        JSONObject tz = tzApi.getDetail(request, id);
        request.setAttribute("tz", tz);
        return new ModelAndView("system/tzMgr/tz_info");
    }

    /**
     * 跳转到添加页面
     *
     * @param request req
     * @return res
     * @throws Exception err
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(HttpServletRequest request) throws Exception {
        request.setAttribute("cuser", tzApi.getUserFromCookie(request));
        return new ModelAndView("system/tzMgr/tz_add");
    }

    /**
     * 跳转到编辑页面
     *
     * @param request req
     * @return res
     * @throws Exception err
     */
    @RequestMapping("toEdit/{id}")
    public ModelAndView toEdit(HttpServletRequest request, @PathVariable String id) throws Exception {
        request.setAttribute("cuser", tzApi.getUserFromCookie(request));
        request.setAttribute("id", id);
        return new ModelAndView("system/tzMgr/tz_edit");
    }

    /**
     * 获取通知列表(分页)
     *
     * @param request req
     * @param tz      请求体
     * @param page    第几页
     * @param limit   每页显示的记录数
     * @return res
     */
    @RequestMapping("getList")
    @ResponseBody
    @Log(module = "通知", desc = "获取通知列表(分页)", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getList(HttpServletRequest request, Tz tz,
                                                   @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        try {
            //User cuser = xyApi.getUserFromCookie(request);
            Page<Tz> xyList = tzApi.getTzList(request, null, tz.toJson(), page, limit);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取通知列表成功", xyList);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取通知列表失败", e);
            throw new BusinessException("获取通知列表失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 获取通知详情
     *
     * @param request req
     * @param id      通知id
     * @return res
     */
    @RequestMapping("getDetail/{id}")
    @ResponseBody
    @Log(module = "通知", desc = "获取通知详情", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getDetail(HttpServletRequest request, @PathVariable String id) {
        try {
            JSONObject tz = tzApi.getDetail(request, id);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取通知详情成功", tz);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取通知详情失败", e);
            throw new BusinessException("获取通知详情失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 添加通知
     *
     * @param request req
     * @param json    请求体
     * @return res
     */
    @RequestMapping("add")
    @ResponseBody
    @Log(module = "通知", desc = "添加通知", optType = Constants.OptType.ADD)
    public ResponseEntity<CusResponseBody> addXm(HttpServletRequest request,
                                                 @RequestBody JSONObject json) {
        return aeTz(request, json);
    }

    /**
     * 编辑通知
     *
     * @param request req
     * @param json    请求体
     * @return res
     */
    @RequestMapping("edit")
    @ResponseBody
    @Log(module = "通知", desc = "添加通知", optType = Constants.OptType.EDIT)
    public ResponseEntity<CusResponseBody> editXm(HttpServletRequest request,
                                                  @RequestBody JSONObject json) {
        return aeTz(request, json);
    }

    private ResponseEntity<CusResponseBody> aeTz(HttpServletRequest request,
                                                 JSONObject json) {
        try {
            User cuser = tzApi.getUserFromCookie(request);
            String dateToStr = DateUtils.parseDateToStr("yyyy-MM-dd'T'HH:mm:ss.sss", new Date());

            //通知基本信息
            Tz model = json.getObject("model", Tz.class);
            //通知附件
            List<TzFj> fjList = json.getJSONArray("list").toJavaList(TzFj.class);

            if (StringUtils.isBlank(model.getFk_xtglid())) {
                model.setFk_xtglid(cuser.getFk_xtglid());
            }

            if (fjList != null && !fjList.isEmpty()) {
                for (TzFj tzFj : fjList) {
                    tzFj.setFk_xtglid(cuser.getFk_xtglid());
                }
            }

            JSONObject jsonReq = new JSONObject();
            jsonReq.put("model", model.toJson());
            jsonReq.put("list", JSONArray.parseArray(JSONArray.toJSONString(fjList)));

            tzApi.aeTz(request, jsonReq);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("更新通知成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("更新通知失败", e);
            throw new BusinessException("更新通知失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 删除通知
     *
     * @param request req
     * @param id      通知id
     * @return res
     */
    @RequestMapping("del/{id}")
    @ResponseBody
    @Log(module = "通知", desc = "删除通知", optType = Constants.OptType.DEL)
    public ResponseEntity<CusResponseBody> del(HttpServletRequest request, @PathVariable String id) {
        try {
            tzApi.delTz(request, id.trim());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("删除通知成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除通知失败", e);
            throw new BusinessException("删除通知失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @param type 文件类型
     * @return res
     */
    @RequestMapping("upload")
    @ResponseBody
    @Log(module = "通知", desc = "上传附件", optType = Constants.OptType.UPLOAD)
    public ResponseEntity<CusResponseBody> uploadFile(MultipartFile file, String type) {
        try {
            // 上传文件路径
            String filePath = BusConfig.getUploadBaseDir() + File.separator + "tz";

            //文件名
            String fileName = file.getOriginalFilename();
            // 上传并返回新文件路劲
            String fileNamePath = FileUploadUtils.upload(filePath, file);
            //构造附件实体类
            TzFj tzFj = new TzFj();
            tzFj.setName(fileName).setPath(fileNamePath);

            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("上传成功", tzFj);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("上传失败", e);
            throw new BusinessException("上传失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
}
