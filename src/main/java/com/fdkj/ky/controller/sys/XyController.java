package com.fdkj.ky.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.model.system.Xy;
import com.fdkj.ky.api.util.sys.XyApi;
import com.fdkj.ky.base.CusResponseBody;
import com.fdkj.ky.base.Page;
import com.fdkj.ky.constant.Constants;
import com.fdkj.ky.constant.CreateGroup;
import com.fdkj.ky.constant.EditGroup;
import com.fdkj.ky.controller.BaseController;
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
import javax.validation.Valid;
import java.util.List;

/**
 * 学院管理
 *
 * @author wyt
 */
@Controller
@RequestMapping("CZF/XYGL")
@Log(module = "学院管理")
public class XyController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(XyController.class);

    @Autowired
    private XyApi xyApi;

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
        request.setAttribute("cuser", xyApi.getUserFromCookie(request));
        request.setAttribute("opts", opts);
        if (opts != null && !opts.isEmpty()) {
            String s = StringUtils.join(opts, ",");
            request.setAttribute("optsStr", s);
        }
        return new ModelAndView("system/xyMgr/xy_index");
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
        request.setAttribute("cuser", xyApi.getUserFromCookie(request));
        return new ModelAndView("system/xyMgr/xy_add");
    }

    /**
     * 跳转到编辑页面
     *
     * @param request req
     * @param id      学院id
     * @return res
     * @throws Exception err
     */
    @RequestMapping("toEdit/{id}")
    public ModelAndView toEdit(HttpServletRequest request, @PathVariable String id) throws Exception {
        request.setAttribute("cuser", xyApi.getUserFromCookie(request));
        request.setAttribute("id", id);
        return new ModelAndView("system/xyMgr/xy_edit");
    }

    /**
     * 跳转到详情页面
     *
     * @param request req
     * @param id      学院id
     * @return res
     * @throws Exception err
     */
    @RequestMapping("toInfo/{id}")
    public ModelAndView toInfo(HttpServletRequest request, @PathVariable String id) throws Exception {
        request.setAttribute("cuser", xyApi.getUserFromCookie(request));
        request.setAttribute("id", id);
        return new ModelAndView("system/xyMgr/xy_info");
    }

    /**
     * 获取学院列表(分页)
     *
     * @param request req
     * @param xy      请求体
     * @param page    第几页
     * @param limit   每页显示的记录数
     * @return res
     */
    @RequestMapping("getList")
    @ResponseBody
    @Log(module = "学院", desc = "获取学院列表(分页)", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getList(HttpServletRequest request, Xy xy,
                                                   @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        try {
            //User cuser = xyApi.getUserFromCookie(request);
            Page<Xy> xyList = xyApi.getXyList(request, null, xy.toJson(), page, limit);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取学院列表成功", xyList);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取学院列表失败", e);
            throw new BusinessException("获取学院列表失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 获取学院详情
     *
     * @param request req
     * @param id      学院id
     * @return res
     */
    @RequestMapping("getDetail/{id}")
    @ResponseBody
    @Log(module = "学院", desc = "获取学院详情", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getDetail(HttpServletRequest request, @PathVariable String id) {
        try {
            Xy xy = xyApi.getXyDetail(request, id);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取学院详情成功", xy);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取学院详情失败", e);
            throw new BusinessException("获取学院详情失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 添加学院
     *
     * @param request req
     * @param xy      请求体
     * @return res
     */
    @RequestMapping("add")
    @ResponseBody
    @Log(module = "学院", desc = "添加学院", optType = Constants.OptType.ADD)
    public ResponseEntity<CusResponseBody> add(HttpServletRequest request,
                                               @Validated(CreateGroup.class) @RequestBody  Xy xy) {
        return ae(request, xy);
    }

    /**
     * 编辑学院
     *
     * @param request req
     * @param xy      请求体
     * @return res
     */
    @RequestMapping("edit")
    @ResponseBody
    @Log(module = "学院", desc = "编辑学院", optType = Constants.OptType.EDIT)
    public ResponseEntity<CusResponseBody> edit(HttpServletRequest request,
                                                @Validated(EditGroup.class) @RequestBody Xy xy) {
        return ae(request, xy);
    }

    private ResponseEntity<CusResponseBody> ae(HttpServletRequest request,
                                               Xy xy) {

        //User cuser = xyApi.getUserFromCookie(request);

        //检查学院编码
        JSONObject codeJson = new JSONObject();
        codeJson.put("code", xy.getCode());
        List<Xy> xyList1 = xyApi.getXyList(request, null, codeJson);
        if (xyList1 != null && !xyList1.isEmpty()) {
            for (Xy xy1 : xyList1) {
                if (xy1.getCode().equals(xy.getCode()) && !xy1.getId().equals(xy.getId())) {
                    throw new BusinessException("学院编码重复", HttpStatus.BAD_REQUEST.value());
                }
            }
        }
        //检查学院名称
        JSONObject nameJson = new JSONObject();
        nameJson.put("name", xy.getName());
        List<Xy> xyList2 = xyApi.getXyList(request, null, nameJson);
        if (xyList2 != null && !xyList2.isEmpty()) {
            for (Xy xy1 : xyList2) {
                if (xy1.getName().equals(xy.getName()) && !xy1.getId().equals(xy.getId())) {
                    throw new BusinessException("学院名称重复", HttpStatus.BAD_REQUEST.value());
                }
            }
        }

        xyApi.aeXy(request, xy.toJson());
        //构造返回数据
        CusResponseBody cusResponseBody = CusResponseBody.success("更新学院成功");
        return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);

    }

    /**
     * 删除学院
     *
     * @param request req
     * @param id      学院id
     * @return res
     */
    @RequestMapping("del/{id}")
    @ResponseBody
    @Log(module = "学院", desc = "删除学院", optType = Constants.OptType.DEL)
    public ResponseEntity<CusResponseBody> del(HttpServletRequest request, @PathVariable String id) {
        try {
            xyApi.delXy(request, id.trim());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("删除学院成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除学院失败", e);
            throw new BusinessException("删除学院失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
}
