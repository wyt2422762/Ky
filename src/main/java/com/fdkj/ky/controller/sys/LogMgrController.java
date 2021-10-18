package com.fdkj.ky.controller.sys;

import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.model.system.Xtrz;
import com.fdkj.ky.api.util.LogApi;
import com.fdkj.ky.base.CusResponseBody;
import com.fdkj.ky.base.Page;
import com.fdkj.ky.constant.Constants;
import com.fdkj.ky.error.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 日志管理
 *
 * @author wyt
 */
@Controller
@RequestMapping("GYFW/PT_XTRZ")
@Log(module = "系统日志")
public class LogMgrController {
    private static final Logger log = LoggerFactory.getLogger(LogMgrController.class);

    @Autowired
    private LogApi logApi;

    @RequestMapping("Index")
    public ModelAndView index(HttpServletRequest request, @RequestParam(value = "opts", required = false) List<String> opts) throws Exception {
        request.setAttribute("user", logApi.getUserFromCookie(request));
        request.setAttribute("opts", opts);
        return new ModelAndView("system/logMgr/logMgr_index");
    }

    @RequestMapping("getList")
    @Log(module = "系统日志", desc = "获取系统日志列表", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getLogList(HttpServletRequest request,
                                                      @RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                                      @RequestParam(value = "startTime", required = false) String startTime,
                                                      @RequestParam(value = "endTime", required = false) String endTime) {
        try {
            Page<Xtrz> logPage = logApi.getLogList(request, page, limit, startTime, endTime);
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取系统日志列表成功", logPage);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取系统日志失败", e);
            throw new BusinessException("获取系统日志失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

}
