package com.fdkj.ky.controller.ky.ry;

import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.model.ky.ry.Ry;
import com.fdkj.ky.api.model.ky.ry.RyReview;
import com.fdkj.ky.api.model.system.User;
import com.fdkj.ky.api.util.ky.ry.RyApi;
import com.fdkj.ky.api.util.sys.DictApi;
import com.fdkj.ky.base.CusResponseBody;
import com.fdkj.ky.constant.Constants;
import com.fdkj.ky.controller.BaseController;
import com.fdkj.ky.error.BusinessException;
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
import java.util.List;

/**
 * 科研人员管理
 *
 * @author wyt
 */
@Controller
@RequestMapping("CZF/KYDW/KYRY/REVIEW")
@Log(module = "科研人员")
public class RyReviewController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(RyReviewController.class);

    @Autowired
    private RyApi ryApi;
    @Autowired
    private DictApi dictApi;

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
        request.setAttribute("cuser", ryApi.getUserFromCookie(request));
        request.setAttribute("opts", opts);
        if (opts != null && !opts.isEmpty()) {
            String s = StringUtils.join(opts, ",");
            request.setAttribute("optsStr", s);
        }
        return new ModelAndView("ky/ry/ryReview/review_index");
    }

    /**
     * 跳转
     *
     * @param request req
     * @param id      人员id
     * @return res
     */
    @RequestMapping("toReview/{id}")
    public ModelAndView toReview(HttpServletRequest request, @PathVariable String id) {
        request.setAttribute("fk_ryid", id);
        return new ModelAndView("ky/ry/ryReview/review");
    }

    @RequestMapping("review/{id}")
    @ResponseBody
    @Log(module = "科研人员", desc = "审核", optType = Constants.OptType.TO_REVIEW)
    public ResponseEntity<CusResponseBody> submit(HttpServletRequest request, @PathVariable String id,
                                                  @RequestBody RyReview ryReview) {
        try {
            User cuser = ryApi.getUserFromCookie(request);
            Ry ryDetail = ryApi.getRyDetail(request, id);
            if (!Constants.RYZT.TJ.equals(ryDetail.getZt())) {
                throw new BusinessException("人员状态变化，审核失败", HttpStatus.BAD_REQUEST.value());
            }
            //添加审核记录
            ryReview.setFk_qybm(ryDetail.getFk_qybm()).setFk_ryid(ryDetail.getId())
                    .setFk_userid(cuser.getId()).setShr(cuser.getUsername())
                    .setFk_xtglid(ryDetail.getFk_xtglid());
            ryApi.shRy(request, ryReview.toJson());

            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("审核成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("审核失败", e);
            throw new BusinessException("审核失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
}
