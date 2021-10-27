package com.fdkj.ky.controller.ky.xm.zxxm;

import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.model.ky.xm.zxxm.Zxxm;
import com.fdkj.ky.api.model.ky.xm.zxxm.Zxxm_cy;
import com.fdkj.ky.api.model.system.User;
import com.fdkj.ky.api.util.ky.jg.JgApi;
import com.fdkj.ky.api.util.ky.xm.zxxm.ZxxmApi;
import com.fdkj.ky.api.util.ky.xm.zxxm.Zxxm_cyApi;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 纵向项目成员管理
 *
 * @author wyt
 */
@Controller
@RequestMapping("CZF/KYXM/ZXXM/CY")
@Log(module = "纵向项目成员")
public class Zxxm_cyController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(Zxxm_cyController.class);

    @Autowired
    private ZxxmApi zxxmApi;
    @Autowired
    private Zxxm_cyApi zxxm_cyApi;
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
     * @param fk_xmid 项目id
     * @param opts    操作权限
     * @return res
     */
    @RequestMapping("Index/{fk_xmid}")
    public ModelAndView index(HttpServletRequest request,
                              @PathVariable String fk_xmid,
                              @RequestParam(value = "opts", required = false) List<String> opts) {
        request.setAttribute("cuser", zxxm_cyApi.getUserFromCookie(request));
        request.setAttribute("fk_xmid", fk_xmid);
        request.setAttribute("opts", opts);
        if (opts != null && !opts.isEmpty()) {
            String s = StringUtils.join(opts, ",");
            request.setAttribute("optsStr", s);
        }
        //暂时不考虑数据权限的问题

        return new ModelAndView("ky/xm/zxxm/cy/cy_index");
    }

    /**
     * 跳转到添加页面
     *
     * @param request req
     * @return res
     * @throws Exception err
     */
    @RequestMapping("toInfo/{id}")
    public ModelAndView toInfo(HttpServletRequest request, @PathVariable String id) throws Exception {
        request.setAttribute("cuser", zxxm_cyApi.getUserFromCookie(request));
        request.setAttribute("id", id);
        //暂时不考虑数据权限的问题
        return new ModelAndView("ky/xm/zxxm/cy/cy_info");
    }

    /**
     * 跳转到添加页面
     *
     * @param request req
     * @return res
     */
    @RequestMapping("toAdd/{fk_xmid}")
    public ModelAndView toAdd(HttpServletRequest request, @PathVariable String fk_xmid) {
        request.setAttribute("cuser", zxxm_cyApi.getUserFromCookie(request));
        //暂时不考虑数据权限的问题

        request.setAttribute("fk_xmid", fk_xmid);
        return new ModelAndView("ky/xm/zxxm/cy/cy_add");
    }

    /**
     * 跳转到编辑页面
     *
     * @param request req
     * @return res
     */
    @RequestMapping("toEdit/{id}")
    public ModelAndView toEdit(HttpServletRequest request, @PathVariable String id) {
        request.setAttribute("cuser", zxxm_cyApi.getUserFromCookie(request));
        request.setAttribute("id", id);
        //暂时不考虑数据权限的问题

        return new ModelAndView("ky/xm/zxxm/cy/cy_edit");
    }

    /**
     * 获取纵向项目成员列表(分页)
     *
     * @param request req
     * @param fk_xmid    纵向项目id
     * @return res
     */
    @RequestMapping("getList/{fk_xmid}")
    @ResponseBody
    @Log(module = "纵向项目成员", desc = "获取纵向项目成员列表(分页)", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getList(HttpServletRequest request, @PathVariable String fk_xmid) {
        try {
            //User cuser = xyApi.getUserFromCookie(request);

            Zxxm_cy zxxm_cy = new Zxxm_cy();
            zxxm_cy.setFk_xmid(fk_xmid);

            List<Zxxm_cy> list = zxxm_cyApi.getList(request, null, zxxm_cy.toJson());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取纵向项目成员列表成功", list);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取纵向项目成员列表失败", e);
            throw new BusinessException("获取纵向项目成员列表失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 获取纵向项目成员详情
     *
     * @param request req
     * @param id      纵向项目成员id
     * @return res
     */
    @RequestMapping("getDetail/{id}")
    @ResponseBody
    @Log(module = "纵向项目成员", desc = "获取纵向项目成员详情", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getDetail(HttpServletRequest request, @PathVariable String id) {
        try {
            Zxxm_cy zxxm_cy = zxxm_cyApi.getDetail(request, id);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取纵向项目成员详情成功", zxxm_cy);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取纵向项目成员详情失败", e);
            throw new BusinessException("获取纵向项目成员详情失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 添加纵向项目成员
     *
     * @param request req
     * @param zxxm_cy 请求体
     * @return res
     */
    @RequestMapping("add")
    @ResponseBody
    @Log(module = "纵向项目成员", desc = "添加纵向项目成员", optType = Constants.OptType.ADD)
    public ResponseEntity<CusResponseBody> add(HttpServletRequest request,
                                               @RequestBody Zxxm_cy zxxm_cy) {
        return ae(request, zxxm_cy);
    }

    /**
     * 编辑纵向项目成员
     *
     * @param request req
     * @param zxxm_cy 请求体
     * @return res
     */
    @RequestMapping("edit")
    @ResponseBody
    @Log(module = "纵向项目成员", desc = "编辑纵向项目成员", optType = Constants.OptType.EDIT)
    public ResponseEntity<CusResponseBody> edit(HttpServletRequest request,
                                                @RequestBody Zxxm_cy zxxm_cy) {
        return ae(request, zxxm_cy);
    }

    private ResponseEntity<CusResponseBody> ae(HttpServletRequest request,
                                               Zxxm_cy zxxm_cy) {
        try {
            User cuser = zxxm_cyApi.getUserFromCookie(request);
            String dateToStr = DateUtils.parseDateToStr("yyyy-MM-dd'T'HH:mm:ss.sss", new Date());

            zxxm_cy.setFk_xtglid(cuser.getFk_xtglid());

            zxxm_cyApi.ae(request, zxxm_cy.toJson());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("更新纵向项目成员成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("更新纵向项目成员失败", e);
            throw new BusinessException("更新纵向项目成员失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 删除纵向项目成员
     *
     * @param request req
     * @param id      纵向项目成员id
     * @return res
     */
    @RequestMapping("del/{id}")
    @ResponseBody
    @Log(module = "纵向项目成员", desc = "删除纵向项目成员", optType = Constants.OptType.DEL)
    public ResponseEntity<CusResponseBody> del(HttpServletRequest request, @PathVariable String id) {
        try {
            zxxm_cyApi.del(request, id.trim());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("删除纵向项目成员成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除纵向项目成员失败", e);
            throw new BusinessException("删除纵向项目成员失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
}
