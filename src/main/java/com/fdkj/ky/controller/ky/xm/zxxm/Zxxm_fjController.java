package com.fdkj.ky.controller.ky.xm.zxxm;

import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.model.ky.xm.zxxm.Zxxm_fj;
import com.fdkj.ky.api.model.system.User;
import com.fdkj.ky.api.util.ky.jg.JgApi;
import com.fdkj.ky.api.util.ky.xm.zxxm.ZxxmApi;
import com.fdkj.ky.api.util.ky.xm.zxxm.Zxxm_fjApi;
import com.fdkj.ky.api.util.sys.DictApi;
import com.fdkj.ky.api.util.sys.XyApi;
import com.fdkj.ky.base.CusResponseBody;
import com.fdkj.ky.config.BusConfig;
import com.fdkj.ky.constant.Constants;
import com.fdkj.ky.controller.BaseController;
import com.fdkj.ky.error.BusinessException;
import com.fdkj.ky.utils.StringUtils;
import com.fdkj.ky.utils.file.FileUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * 纵向项目附件管理
 *
 * @author wyt
 */
@Controller
@RequestMapping("CZF/KYXM/ZXXM/FJ")
@Log(module = "纵向项目附件")
public class Zxxm_fjController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(Zxxm_fjController.class);

    @Autowired
    private ZxxmApi zxxmApi;
    @Autowired
    private Zxxm_fjApi zxxm_fjApi;
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
        request.setAttribute("cuser", zxxm_fjApi.getUserFromCookie(request));
        request.setAttribute("fk_xmid", fk_xmid);
        request.setAttribute("opts", opts);
        if (opts != null && !opts.isEmpty()) {
            String s = StringUtils.join(opts, ",");
            request.setAttribute("optsStr", s);
        }
        //暂时不考虑数据权限的问题

        return new ModelAndView("ky/xm/zxxm/fj/fj_index");
    }
    
    /**
     * 获取纵向项目附件列表(分页)
     *
     * @param request req
     * @param fk_xmid    纵向项目id
     * @return res
     */
    @RequestMapping("getList/{fk_xmid}")
    @ResponseBody
    @Log(module = "纵向项目附件", desc = "获取纵向项目附件列表(分页)", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getList(HttpServletRequest request, @PathVariable String fk_xmid) {
        try {
            //User cuser = xyApi.getUserFromCookie(request);

            Zxxm_fj zxxm_fj = new Zxxm_fj();
            zxxm_fj.setFk_xmid(fk_xmid);

            List<Zxxm_fj> list = zxxm_fjApi.getList(request, null, zxxm_fj.toJson());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取纵向项目附件列表成功", list);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取纵向项目附件列表失败", e);
            throw new BusinessException("获取纵向项目附件列表失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 删除纵向项目附件
     *
     * @param request req
     * @param id      纵向项目附件id
     * @return res
     */
    @RequestMapping("del/{id}")
    @ResponseBody
    @Log(module = "纵向项目附件", desc = "删除纵向项目附件", optType = Constants.OptType.DEL)
    public ResponseEntity<CusResponseBody> del(HttpServletRequest request, @PathVariable String id) {
        try {
            zxxm_fjApi.del(request, id.trim());
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("删除纵向项目附件成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除纵向项目附件失败", e);
            throw new BusinessException("删除纵向项目附件失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @param fk_xmid 项目id
     * @return res
     */
    @RequestMapping("upload")
    @ResponseBody
    @Log(module = "纵向项目附件", desc = "上传附件", optType = Constants.OptType.UPLOAD)
    public ResponseEntity<CusResponseBody> uploadFile(HttpServletRequest request, MultipartFile file, String fk_xmid) {
        try {
            User cuser = zxxm_fjApi.getUserFromCookie(request);

            // 上传文件路径
            String filePath = BusConfig.getUploadBaseDir() + File.separator + "xm" + File.separator + "zxxm";

            //文件名
            String fileName = file.getOriginalFilename();
            // 上传并返回新文件路劲
            String fileNamePath = FileUploadUtils.upload(filePath, file);
            //构造附件实体类
            Zxxm_fj zxxm_fj = new Zxxm_fj();
            zxxm_fj.setName(fileName).setPath(fileNamePath)
                    .setFk_xtglid(cuser.getFk_xtglid()).setFk_xmid(fk_xmid);

            zxxm_fjApi.ae(request, zxxm_fj.toJson());

            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("上传成功", zxxm_fj);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("上传失败", e);
            throw new BusinessException("上传失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
}
