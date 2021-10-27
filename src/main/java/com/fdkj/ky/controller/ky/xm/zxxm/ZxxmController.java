package com.fdkj.ky.controller.ky.xm.zxxm;

import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.model.ky.ry.Ry;
import com.fdkj.ky.api.model.ky.ry.RyReview;
import com.fdkj.ky.api.model.ky.xm.zxxm.Zxxm;
import com.fdkj.ky.api.model.system.User;
import com.fdkj.ky.api.model.system.Xy;
import com.fdkj.ky.api.model.system.Zd;
import com.fdkj.ky.api.util.ky.jg.JgApi;
import com.fdkj.ky.api.util.ky.xm.zxxm.ZxxmApi;
import com.fdkj.ky.api.util.sys.DictApi;
import com.fdkj.ky.api.util.sys.XyApi;
import com.fdkj.ky.base.CusResponseBody;
import com.fdkj.ky.base.Page;
import com.fdkj.ky.constant.Constants;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 纵向项目管理
 *
 * @author wyt
 */
@Controller
@RequestMapping("CZF/KYXM/ZXXM")
@Log(module = "纵向项目")
public class ZxxmController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ZxxmController.class);

    @Autowired
    private ZxxmApi zxxmApi;
    @Autowired
    private DictApi dictApi;
    @Autowired
    private XyApi xyApi;
    @Autowired
    private JgApi jgApi;

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
        request.setAttribute("cuser", zxxmApi.getUserFromCookie(request));
        request.setAttribute("opts", opts);
        if (opts != null && !opts.isEmpty()) {
            String s = StringUtils.join(opts, ",");
            request.setAttribute("optsStr", s);
        }
        //暂时不考虑数据权限的问题

        return new ModelAndView("ky/xm/zxxm/zxxm_index");
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
        request.setAttribute("id", id);
        //暂时不考虑数据权限的问题

        //获取字典信息
        //项目状态
        Map<String, Object> dictParams = new HashMap<>();
        dictParams.put("fid", Constants.Dict.XMZT);
        List<Zd> xmzt = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_xmzt", xmzt);
        //项目级别
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.XMJB);
        List<Zd> xmjb = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_xmjb", xmjb);
        //负责人类型
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.FZRLX);
        List<Zd> fzrlx = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_fzrlx", fzrlx);

        //获取所有的学院
        List<Xy> xyList = xyApi.getXyList(request, null, null);
        request.setAttribute("xyList", xyList);

        return new ModelAndView("ky/xm/zxxm/zxxm_info");
    }

    /**
     * 跳转到添加页面
     *
     * @param request req
     * @return res
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        request.setAttribute("cuser", zxxmApi.getUserFromCookie(request));
        //暂时不考虑数据权限的问题

        //获取字典信息
        //项目状态
        Map<String, Object> dictParams = new HashMap<>();
        dictParams.put("fid", Constants.Dict.XMZT);
        List<Zd> xmzt = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_xmzt", xmzt);
        //项目级别
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.XMJB);
        List<Zd> xmjb = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_xmjb", xmjb);
        //负责人类型
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.FZRLX);
        List<Zd> fzrlx = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_fzrlx", fzrlx);

        //获取所有的学院
        List<Xy> xyList = xyApi.getXyList(request, null, null);
        request.setAttribute("xyList", xyList);

        return new ModelAndView("ky/xm/zxxm/zxxm_add");
    }

    /**
     * 跳转到编辑页面
     *
     * @param request req
     * @return res
     */
    @RequestMapping("toEdit/{id}")
    public ModelAndView toEdit(HttpServletRequest request, @PathVariable String id) {
        request.setAttribute("cuser", zxxmApi.getUserFromCookie(request));
        request.setAttribute("id", id);
        //暂时不考虑数据权限的问题

        //获取字典信息
        //项目状态
        Map<String, Object> dictParams = new HashMap<>();
        dictParams.put("fid", Constants.Dict.XMZT);
        List<Zd> xmzt = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_xmzt", xmzt);
        //项目级别
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.XMJB);
        List<Zd> xmjb = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_xmjb", xmjb);
        //负责人类型
        dictParams.clear();
        dictParams.put("fid", Constants.Dict.FZRLX);
        List<Zd> fzrlx = dictApi.getZdList(request, dictParams);
        request.setAttribute("dict_fzrlx", fzrlx);

        //获取所有的学院
        List<Xy> xyList = xyApi.getXyList(request, null, null);
        request.setAttribute("xyList", xyList);

        return new ModelAndView("ky/xm/zxxm/zxxm_edit");
    }

    /**
     * 获取纵向项目列表(分页)
     *
     * @param request req
     * @param zxxm    请求体
     * @param page    第几页
     * @param limit   每页显示的记录数
     * @return res
     */
    @RequestMapping("getList")
    @ResponseBody
    @Log(module = "纵向项目", desc = "获取纵向项目列表(分页)", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getList(HttpServletRequest request, Zxxm zxxm,
                                                   @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        try {
            //User cuser = xyApi.getUserFromCookie(request);
            Page<Zxxm> xyList = zxxmApi.getList(request, null, zxxm.toJson(), page, limit);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取纵向项目列表成功", xyList);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取纵向项目列表失败", e);
            throw new BusinessException("获取纵向项目列表失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 获取纵向项目详情
     *
     * @param request req
     * @param id      纵向项目id
     * @return res
     */
    @RequestMapping("getDetail/{id}")
    @ResponseBody
    @Log(module = "纵向项目", desc = "获取纵向项目详情", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getDetail(HttpServletRequest request, @PathVariable String id) {
        try {
            Zxxm zxxm = zxxmApi.getDetail(request, id);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取纵向项目详情成功", zxxm);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取纵向项目详情失败", e);
            throw new BusinessException("获取纵向项目详情失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 添加纵向项目
     *
     * @param request req
     * @param zxxm    请求体
     * @return res
     */
    @RequestMapping("add")
    @ResponseBody
    @Log(module = "纵向项目", desc = "添加纵向项目", optType = Constants.OptType.ADD)
    public ResponseEntity<CusResponseBody> add(HttpServletRequest request,
                                               @Validated @RequestBody Zxxm zxxm) {
        return ae(request, zxxm);
    }

    /**
     * 编辑纵向项目
     *
     * @param request req
     * @param zxxm    请求体
     * @return res
     */
    @RequestMapping("edit")
    @ResponseBody
    @Log(module = "纵向项目", desc = "编辑纵向项目", optType = Constants.OptType.EDIT)
    public ResponseEntity<CusResponseBody> edit(HttpServletRequest request,
                                                @Validated @RequestBody Zxxm zxxm) {
        return ae(request, zxxm);
    }

    private ResponseEntity<CusResponseBody> ae(HttpServletRequest request,
                                               Zxxm zxxm) {
        try {
            User cuser = zxxmApi.getUserFromCookie(request);
            String dateToStr = DateUtils.parseDateToStr("yyyy-MM-dd'T'HH:mm:ss.sss", new Date());

            zxxm.setFk_xtglid(cuser.getFk_xtglid());

            zxxmApi.ae(request, zxxm.toJson());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("更新纵向项目成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("更新纵向项目失败", e);
            throw new BusinessException("更新纵向项目失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 删除纵向项目
     *
     * @param request req
     * @param id      纵向项目id
     * @return res
     */
    @RequestMapping("del/{id}")
    @ResponseBody
    @Log(module = "纵向项目", desc = "删除纵向项目", optType = Constants.OptType.DEL)
    public ResponseEntity<CusResponseBody> del(HttpServletRequest request, @PathVariable String id) {
        try {
            jgApi.del(request, id.trim());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("删除纵向项目成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除纵向项目失败", e);
            throw new BusinessException("删除纵向项目失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    @RequestMapping("submit/{id}")
    @ResponseBody
    @Log(module = "纵向项目", desc = "提交审核", optType = Constants.OptType.TO_REVIEW)
    public ResponseEntity<CusResponseBody> submit(HttpServletRequest request, @PathVariable String id) {
        try {
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("提交审核成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("提交审核失败", e);
            throw new BusinessException("提交审核失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
}
