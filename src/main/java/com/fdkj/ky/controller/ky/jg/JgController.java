package com.fdkj.ky.controller.ky.jg;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.model.ky.jg.Jg;
import com.fdkj.ky.api.model.ky.jg.JgFj;
import com.fdkj.ky.api.model.system.User;
import com.fdkj.ky.api.model.system.Xy;
import com.fdkj.ky.api.util.ky.jg.JgApi;
import com.fdkj.ky.api.util.sys.DictApi;
import com.fdkj.ky.api.util.sys.XyApi;
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
 * 科研机构管理
 *
 * @author wyt
 */
@Controller
@RequestMapping("CZF/KYDW/KYJG")
@Log(module = "科研机构")
public class JgController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(JgController.class);

    @Autowired
    private JgApi jgApi;
    @Autowired
    private DictApi dictApi;
    @Autowired
    private XyApi xyApi;

    /**
     * 跳转
     *
     * @param request req
     * @param opts    操作权限
     * @return res
     */
    @RequestMapping("Index")
    public ModelAndView index(HttpServletRequest request,
                              @RequestParam(value = "opts", required = false) List<String> opts) {
        request.setAttribute("cuser", jgApi.getUserFromCookie(request));
        request.setAttribute("opts", opts);
        if (opts != null && !opts.isEmpty()) {
            String s = StringUtils.join(opts, ",");
            request.setAttribute("optsStr", s);
        }
        //暂时不考虑数据权限的问题
        //获取所有的学院
        List<Xy> xyList = xyApi.getXyList(request, null, null);
        request.setAttribute("xyList", xyList);
        return new ModelAndView("ky/jg/jg_index");
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
        request.setAttribute("cuser", jgApi.getUserFromCookie(request));
        //request.setAttribute("id", id);
        JSONObject jg = jgApi.getDetail(request, id);
        request.setAttribute("jg", jg);
        //暂时不考虑数据权限的问题
        //获取所有的学院
        List<Xy> xyList = xyApi.getXyList(request, null, null);
        request.setAttribute("xyList", xyList);
        return new ModelAndView("ky/jg/jg_info");
    }

    /**
     * 跳转到添加页面
     *
     * @param request req
     * @return res
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        request.setAttribute("cuser", jgApi.getUserFromCookie(request));
        //暂时不考虑数据权限的问题
        //获取所有的学院
        List<Xy> xyList = xyApi.getXyList(request, null, null);
        request.setAttribute("xyList", xyList);
        return new ModelAndView("ky/jg/jg_add");
    }

    /**
     * 跳转到编辑页面
     *
     * @param request req
     * @return res
     */
    @RequestMapping("toEdit/{id}")
    public ModelAndView toEdit(HttpServletRequest request, @PathVariable String id) {
        request.setAttribute("cuser", jgApi.getUserFromCookie(request));
        request.setAttribute("id", id);
        //暂时不考虑数据权限的问题
        //获取所有的学院
        List<Xy> xyList = xyApi.getXyList(request, null, null);
        request.setAttribute("xyList", xyList);
        return new ModelAndView("ky/jg/jg_edit");
    }

    /**
     * 获取科研机构列表(分页)
     *
     * @param request req
     * @param jg      请求体
     * @param page    第几页
     * @param limit   每页显示的记录数
     * @return res
     */
    @RequestMapping("getList")
    @ResponseBody
    @Log(module = "科研机构", desc = "获取科研机构列表(分页)", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getList(HttpServletRequest request, Jg jg,
                                                   @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        try {
            //User cuser = xyApi.getUserFromCookie(request);
            Page<Jg> xyList = jgApi.getList(request, null, jg.toJson(), page, limit);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取科研机构列表成功", xyList);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取科研机构列表失败", e);
            throw new BusinessException("获取科研机构列表失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
    
    /**
     * 获取科研机构详情
     *
     * @param request req
     * @param id      科研机构id
     * @return res
     */
    @RequestMapping("getDetail/{id}")
    @ResponseBody
    @Log(module = "科研机构", desc = "获取科研机构详情", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getDetail(HttpServletRequest request, @PathVariable String id) {
        try {
            JSONObject jg = jgApi.getDetail(request, id);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取科研机构详情成功", jg);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取科研机构详情失败", e);
            throw new BusinessException("获取科研机构详情失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 添加科研机构
     *
     * @param request req
     * @param json    请求体
     * @return res
     */
    @RequestMapping("add")
    @ResponseBody
    @Log(module = "科研机构", desc = "添加科研机构", optType = Constants.OptType.ADD)
    public ResponseEntity<CusResponseBody> addXm(HttpServletRequest request,
                                                 @RequestBody JSONObject json) {
        return ae(request, json);
    }

    /**
     * 编辑科研机构
     *
     * @param request req
     * @param json    请求体
     * @return res
     */
    @RequestMapping("edit")
    @ResponseBody
    @Log(module = "科研机构", desc = "添加科研机构", optType = Constants.OptType.EDIT)
    public ResponseEntity<CusResponseBody> editXm(HttpServletRequest request,
                                                  @RequestBody JSONObject json) {
        return ae(request, json);
    }

    private ResponseEntity<CusResponseBody> ae(HttpServletRequest request,
                                                 JSONObject json) {
        try {
            User cuser = jgApi.getUserFromCookie(request);
            String dateToStr = DateUtils.parseDateToStr("yyyy-MM-dd'T'HH:mm:ss.sss", new Date());

            //科研机构基本信息
            Jg model = json.getObject("model", Jg.class);
            //科研机构附件
            List<JgFj> fjList = json.getJSONArray("list").toJavaList(JgFj.class);

            if (StringUtils.isBlank(model.getFk_xtglid())) {
                model.setFk_xtglid(cuser.getFk_xtglid());
            }

            if (fjList != null && !fjList.isEmpty()) {
                for (JgFj jgFj : fjList) {
                    jgFj.setFk_xtglid(cuser.getFk_xtglid());
                }
            }

            JSONObject jsonReq = new JSONObject();
            jsonReq.put("model", model.toJson());
            jsonReq.put("list", JSONArray.parseArray(JSONArray.toJSONString(fjList)));

            jgApi.ae(request, jsonReq);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("更新科研机构成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("更新科研机构失败", e);
            throw new BusinessException("更新科研机构失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
    
    /**
     * 删除科研机构
     *
     * @param request req
     * @param id      科研机构id
     * @return res
     */
    @RequestMapping("del/{id}")
    @ResponseBody
    @Log(module = "科研机构", desc = "删除科研机构", optType = Constants.OptType.DEL)
    public ResponseEntity<CusResponseBody> del(HttpServletRequest request, @PathVariable String id) {
        try {
            jgApi.del(request, id.trim());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("删除科研机构成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除科研机构失败", e);
            throw new BusinessException("删除科研机构失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
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
    @Log(module = "科研机构", desc = "上传附件", optType = Constants.OptType.UPLOAD)
    public ResponseEntity<CusResponseBody> uploadFile(MultipartFile file, String type) {
        try {
            // 上传文件路径
            String filePath = BusConfig.getUploadBaseDir() + File.separator + "kyjg";

            //文件名
            String fileName = file.getOriginalFilename();
            // 上传并返回新文件路劲
            String fileNamePath = FileUploadUtils.upload(filePath, file);
            //构造附件实体类
            JgFj jgFj = new JgFj();
            jgFj.setName(fileName).setPath(fileNamePath);

            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("上传成功", jgFj);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("上传失败", e);
            throw new BusinessException("上传失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
}
