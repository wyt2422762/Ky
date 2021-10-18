package com.fdkj.ky.controller;

import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.model.system.View_PT_XT_MK_Model;
import com.fdkj.ky.api.util.SystemApi;
import com.fdkj.ky.base.CusResponseBody;
import com.fdkj.ky.constant.Constants;
import com.fdkj.ky.error.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 主页面
 *
 * @author wyt
 */
@Controller
@RequestMapping("")
@Log(module = "首页")
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private SystemApi systemApi;

    @RequestMapping("")
    public ModelAndView index(HttpServletRequest request) throws Exception {
        request.setAttribute("user", systemApi.getUserFromCookie(request));
        List<View_PT_XT_MK_Model> menu = systemApi.getUserMenu(request);
        request.setAttribute("menu", menu);
        return new ModelAndView("index");
    }

    @RequestMapping("wel")
    public ModelAndView wel(HttpServletRequest request) throws Exception {
        return new ModelAndView("wel");
    }

    @RequestMapping("/index/getMenu")
    @ResponseBody
    @Log(module = "首页", desc = "获取菜单", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getMenu(HttpServletRequest request) {
        try {
            List<View_PT_XT_MK_Model> menu = systemApi.getUserMenu(request);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("退出成功", menu);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取菜单失败", e);
            throw new BusinessException("获取菜单失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
