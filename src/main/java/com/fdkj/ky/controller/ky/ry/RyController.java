package com.fdkj.ky.controller.ky.ry;

import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.model.ky.jg.Jg;
import com.fdkj.ky.api.model.ky.ry.Ry;
import com.fdkj.ky.api.model.ky.ry.RyReview;
import com.fdkj.ky.api.model.ky.ry.Zj;
import com.fdkj.ky.api.model.system.User;
import com.fdkj.ky.api.model.system.Zd;
import com.fdkj.ky.api.util.ky.jg.JgApi;
import com.fdkj.ky.api.util.ky.ry.RyApi;
import com.fdkj.ky.api.util.sys.DictApi;
import com.fdkj.ky.base.CusResponseBody;
import com.fdkj.ky.base.Page;
import com.fdkj.ky.constant.Constants;
import com.fdkj.ky.constant.CreateGroup;
import com.fdkj.ky.constant.EditGroup;
import com.fdkj.ky.controller.BaseController;
import com.fdkj.ky.error.BusinessException;
import com.fdkj.ky.utils.DateUtils;
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
 * 科研人员管理
 *
 * @author wyt
 */
@Controller
@RequestMapping("CZF/KYDW/KYRY")
@Log(module = "科研人员")
public class RyController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(RyController.class);

    @Autowired
    private RyApi ryApi;
    @Autowired
    private DictApi dictApi;
    @Autowired
    private JgApi jgApi;

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
                              @RequestParam(value = "opts", required = false) List<String> opts) {
        request.setAttribute("cuser", ryApi.getUserFromCookie(request));
        request.setAttribute("opts", opts);
        if (opts != null && !opts.isEmpty()) {
            String s = StringUtils.join(opts, ",");
            request.setAttribute("optsStr", s);
        }
        return new ModelAndView("ky/ry/ry/ry_index");
    }

    /**
     * 跳转到添加页面
     *
     * @param request req
     * @return res
     * @throws Exception err
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        request.setAttribute("cuser", ryApi.getUserFromCookie(request));

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

        //获取机构信息
        List<Jg> jgList = jgApi.getList(request, null, null);
        request.setAttribute("jgList", jgList);

        return new ModelAndView("ky/ry/ry/ry_add");
    }

    /**
     * 跳转到编辑页面
     *
     * @param request req
     * @param id      科研人员id
     * @return res
     * @throws Exception err
     */
    @RequestMapping("toEdit/{id}")
    public ModelAndView toEdit(HttpServletRequest request, @PathVariable String id) {
        request.setAttribute("cuser", ryApi.getUserFromCookie(request));
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

        //获取机构信息
        List<Jg> jgList = jgApi.getList(request, null, null);
        request.setAttribute("jgList", jgList);

        return new ModelAndView("ky/ry/ry/ry_edit");
    }

    /**
     * 跳转到详情页面
     *
     * @param request req
     * @param id      科研人员id
     * @return res
     * @throws Exception err
     */
    @RequestMapping("toInfo/{id}")
    public ModelAndView toInfo(HttpServletRequest request, @PathVariable String id) {
        request.setAttribute("cuser", ryApi.getUserFromCookie(request));
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

        //获取机构信息
        List<Jg> jgList = jgApi.getList(request, null, null);
        request.setAttribute("jgList", jgList);

        return new ModelAndView("ky/ry/ry/ry_info");
    }

    /**
     * 获取科研人员列表(分页)
     *
     * @param request req
     * @param ry      请求体
     * @param page    第几页
     * @param limit   每页显示的记录数
     * @return res
     */
    @RequestMapping("getList")
    @ResponseBody
    @Log(module = "科研人员", desc = "获取科研人员列表(分页)", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getList(HttpServletRequest request, Ry ry,
                                                   @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        try {
            //User cuser = xyApi.getUserFromCookie(request);
            Page<Ry> ryList = ryApi.getRyList(request, null, ry.toJson(), page, limit);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取科研人员列表成功", ryList);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取科研人员列表失败", e);
            throw new BusinessException("获取科研人员列表失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 获取科研人员详情
     *
     * @param request req
     * @param id      科研人员id
     * @return res
     */
    @RequestMapping("getDetail/{id}")
    @ResponseBody
    @Log(module = "科研人员", desc = "获取科研人员详情", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getDetail(HttpServletRequest request, @PathVariable String id) {
        try {
            Ry ry = ryApi.getRyDetail(request, id);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取科研人员详情成功", ry);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取科研人员详情失败", e);
            throw new BusinessException("获取科研人员详情失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 添加科研人员
     *
     * @param request req
     * @param ry      请求体
     * @return res
     */
    @RequestMapping("add")
    @ResponseBody
    @Log(module = "科研人员", desc = "添加科研人员", optType = Constants.OptType.ADD)
    public ResponseEntity<CusResponseBody> add(HttpServletRequest request,
                                               @Validated(CreateGroup.class) @RequestBody Ry ry) {
        return ae(request, ry);
    }

    /**
     * 编辑科研人员
     *
     * @param request req
     * @param ry      请求体
     * @return res
     */
    @RequestMapping("edit")
    @ResponseBody
    @Log(module = "科研人员", desc = "编辑科研人员", optType = Constants.OptType.EDIT)
    public ResponseEntity<CusResponseBody> edit(HttpServletRequest request,
                                                @Validated(EditGroup.class) @RequestBody Ry ry) {
        return ae(request, ry);
    }

    private ResponseEntity<CusResponseBody> ae(HttpServletRequest request,
                                               Ry ry) {
        //User cuser = xyApi.getUserFromCookie(request);
        ryApi.aeRy(request, ry.toJson());
        //构造返回数据
        CusResponseBody cusResponseBody = CusResponseBody.success("更新科研人员成功");
        return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
    }

    /**
     * 删除科研人员
     *
     * @param request req
     * @param id      科研人员id
     * @return res
     */
    @RequestMapping("del/{id}")
    @ResponseBody
    @Log(module = "科研人员", desc = "删除科研人员", optType = Constants.OptType.DEL)
    public ResponseEntity<CusResponseBody> del(HttpServletRequest request, @PathVariable String id) {
        try {
            ryApi.delRy(request, id.trim());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("删除科研人员成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除科研人员失败", e);
            throw new BusinessException("删除科研人员失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    @RequestMapping("submit/{id}")
    @ResponseBody
    @Log(module = "科研人员", desc = "提交审核", optType = Constants.OptType.TO_REVIEW)
    public ResponseEntity<CusResponseBody> submit(HttpServletRequest request, @PathVariable String id) {
        try {
            User cuser = ryApi.getUserFromCookie(request);
            Ry ryDetail = ryApi.getRyDetail(request, id);
            if (!(Constants.RYZT.YBC.equals(ryDetail.getZt()) || Constants.RYZT.TH.equals(ryDetail.getZt()))) {
                throw new BusinessException("人员状态变化，提交失败", HttpStatus.BAD_REQUEST.value());
            }
            //添加审核记录
            RyReview ryReview = new RyReview();
            ryReview.setFk_qybm(ryDetail.getFk_qybm()).setFk_ryid(ryDetail.getId())
                    .setFk_userid(cuser.getId()).setShr(cuser.getUsername())
                    .setFk_xtglid(ryDetail.getFk_xtglid()).setShzt(Constants.REVIEW_YPE.SUBMIT);
            ryApi.shRy(request, ryReview.toJson());

            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("提交审核成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("提交审核失败", e);
            throw new BusinessException("提交审核失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
}
