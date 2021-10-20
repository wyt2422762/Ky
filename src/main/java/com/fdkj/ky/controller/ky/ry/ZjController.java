package com.fdkj.ky.controller.ky.ry;

import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.model.ky.ry.Zj;
import com.fdkj.ky.api.model.system.Zd;
import com.fdkj.ky.api.util.ky.ry.ZjApi;
import com.fdkj.ky.api.util.sys.DictApi;
import com.fdkj.ky.base.CusResponseBody;
import com.fdkj.ky.base.Page;
import com.fdkj.ky.constant.Constants;
import com.fdkj.ky.constant.CreateGroup;
import com.fdkj.ky.constant.EditGroup;
import com.fdkj.ky.controller.BaseController;
import com.fdkj.ky.controller.sys.XyController;
import com.fdkj.ky.error.BusinessException;
import com.fdkj.ky.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专家人才管理
 *
 * @author wyt
 */
@Controller
@RequestMapping("CZF/KYDW/ZJRC")
@Log(module = "专家人才")
public class ZjController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(XyController.class);

    @Autowired
    private ZjApi zjApi;
    @Autowired
    private DictApi dictApi;

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
        request.setAttribute("cuser", zjApi.getUserFromCookie(request));
        request.setAttribute("opts", opts);
        if (opts != null && !opts.isEmpty()) {
            String s = StringUtils.join(opts, ",");
            request.setAttribute("optsStr", s);
        }
        return new ModelAndView("ky/ry/zj/zj_index");
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
        request.setAttribute("cuser", zjApi.getUserFromCookie(request));

        //获取字典信息
        //性别
        Map<String, Object> dictParams = new HashMap<>();
        dictParams.put("fid", Constants.Dict.GENDER);
        List<Zd> gender = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_gender", gender);
        //导师类型
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.TUTOR);
        List<Zd> tutor = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_tutor", tutor);
        //学位
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.DEGREE);
        List<Zd> degree = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_degree", degree);
        //学历
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.EDU);
        List<Zd> edu = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_edu", edu);

        return new ModelAndView("ky/ry/zj/zj_add");
    }

    /**
     * 跳转到编辑页面
     *
     * @param request req
     * @param id      专家人才id
     * @return res
     * @throws Exception err
     */
    @RequestMapping("toEdit/{id}")
    public ModelAndView toEdit(HttpServletRequest request, @PathVariable String id) throws Exception {
        request.setAttribute("cuser", zjApi.getUserFromCookie(request));
        request.setAttribute("id", id);

        //获取字典信息
        //性别
        Map<String, Object> dictParams = new HashMap<>();
        dictParams.put("fid", Constants.Dict.GENDER);
        List<Zd> gender = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_gender", gender);
        //导师类型
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.TUTOR);
        List<Zd> tutor = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_tutor", tutor);
        //学位
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.DEGREE);
        List<Zd> degree = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_degree", degree);
        //学历
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.EDU);
        List<Zd> edu = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_edu", edu);

        return new ModelAndView("ky/ry/zj/zj_edit");
    }

    /**
     * 跳转到详情页面
     *
     * @param request req
     * @param id      专家人才id
     * @return res
     * @throws Exception err
     */
    @RequestMapping("toInfo/{id}")
    public ModelAndView toInfo(HttpServletRequest request, @PathVariable String id) throws Exception {
        request.setAttribute("cuser", zjApi.getUserFromCookie(request));
        request.setAttribute("id", id);

        //获取字典信息
        //性别
        Map<String, Object> dictParams = new HashMap<>();
        dictParams.put("fid", Constants.Dict.GENDER);
        List<Zd> gender = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_gender", gender);
        //导师类型
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.TUTOR);
        List<Zd> tutor = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_tutor", tutor);
        //学位
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.DEGREE);
        List<Zd> degree = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_degree", degree);
        //学历
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.EDU);
        List<Zd> edu = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_edu", edu);

        return new ModelAndView("ky/ry/zj/zj_info");
    }

    /**
     * 获取专家人才列表(分页)
     *
     * @param request req
     * @param zj      请求体
     * @param page    第几页
     * @param limit   每页显示的记录数
     * @return res
     */
    @RequestMapping("getList")
    @ResponseBody
    @Log(module = "专家人才", desc = "获取专家人才列表(分页)", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getList(HttpServletRequest request, Zj zj,
                                                   @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        try {
            //User cuser = xyApi.getUserFromCookie(request);
            Page<Zj> xyList = zjApi.getZjList(request, null, zj.toJson(), page, limit);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取专家人才列表成功", xyList);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取专家人才列表失败", e);
            throw new BusinessException("获取专家人才列表失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 获取专家人才详情
     *
     * @param request req
     * @param id      专家人才id
     * @return res
     */
    @RequestMapping("getDetail/{id}")
    @ResponseBody
    @Log(module = "专家人才", desc = "获取专家人才详情", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getDetail(HttpServletRequest request, @PathVariable String id) {
        try {
            Zj zj = zjApi.getZjDetail(request, id);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取专家人才详情成功", zj);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取专家人才详情失败", e);
            throw new BusinessException("获取专家人才详情失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 添加专家人才
     *
     * @param request req
     * @param zj      请求体
     * @return res
     */
    @RequestMapping("add")
    @ResponseBody
    @Log(module = "专家人才", desc = "添加专家人才", optType = Constants.OptType.ADD)
    public ResponseEntity<CusResponseBody> add(HttpServletRequest request,
                                               @Validated(CreateGroup.class) @RequestBody Zj zj) {
        return ae(request, zj);
    }

    /**
     * 编辑专家人才
     *
     * @param request req
     * @param zj      请求体
     * @return res
     */
    @RequestMapping("edit")
    @ResponseBody
    @Log(module = "专家人才", desc = "编辑专家人才", optType = Constants.OptType.EDIT)
    public ResponseEntity<CusResponseBody> edit(HttpServletRequest request,
                                                @Validated(EditGroup.class) @RequestBody Zj zj) {
        return ae(request, zj);
    }

    private ResponseEntity<CusResponseBody> ae(HttpServletRequest request,
                                               Zj zj) {
        //User cuser = xyApi.getUserFromCookie(request);
        zjApi.aeZj(request, zj.toJson());
        //构造返回数据
        CusResponseBody cusResponseBody = CusResponseBody.success("更新专家人才成功");
        return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);

    }

    /**
     * 删除专家人才
     *
     * @param request req
     * @param id      专家人才id
     * @return res
     */
    @RequestMapping("del/{id}")
    @ResponseBody
    @Log(module = "专家人才", desc = "删除专家人才", optType = Constants.OptType.DEL)
    public ResponseEntity<CusResponseBody> del(HttpServletRequest request, @PathVariable String id) {
        try {
            zjApi.delZj(request, id.trim());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("删除专家人才成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除专家人才失败", e);
            throw new BusinessException("删除专家人才失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
}
