package com.fdkj.ky.controller;

import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.base.CusResponseBody;
import com.fdkj.ky.config.BusConfig;
import com.fdkj.ky.config.ServerConfig;
import com.fdkj.ky.constant.Constants;
import com.fdkj.ky.error.BusinessException;
import com.fdkj.ky.utils.NoUtil;
import com.fdkj.ky.utils.StringUtils;
import com.fdkj.ky.utils.file.FileUploadUtils;
import com.fdkj.ky.utils.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 通用业务处理
 *
 * @author wyt
 */
@Controller
@RequestMapping("common")
@Log(module = "通用")
public class CommonController {

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private NoUtil noUtil;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @RequestMapping("download")
    @Log(module = "通用", desc = "下载文件", optType = Constants.OptType.DOWNLOAD)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("fileName") String fileName, @RequestParam("delete") Boolean delete) {
        try {
            if (!FileUtils.checkAllowDownload(fileName)) {
                throw new Exception("文件名称" + fileName + "非法，不允许下载。 ");
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = BusConfig.getDownLoadBaseDir() + File.separator + fileName;
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
            throw new BusinessException("下载文件失败", HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 通用上传请求
     */
    @RequestMapping("upload")
    @ResponseBody
    @Log(module = "通用", desc = "上传文件", optType = Constants.OptType.UPLOAD)
    public ResponseEntity<CusResponseBody> uploadFile(MultipartFile file) {
        try {
            // 上传文件路径
            String filePath = BusConfig.getUploadBaseDir();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("上传成功");
            cusResponseBody.put("fileName", fileName);
            cusResponseBody.put("url", url);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("上传失败", e);
            throw new BusinessException("上传失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 本地资源通用下载
     *
     * @param resource 文件路径
     * @param name     文件名
     * @param response resp
     */
    @GetMapping("download/upload")
    @Log(module = "通用", desc = "下载上传资源", optType = Constants.OptType.DOWNLOAD)
    public void resourceDownload(String resource, String name, HttpServletResponse response) {
        try {
            /*if (!FileUtils.checkAllowDownload(resource)) {
                throw new BusinessException("资源文件非法，不允许下载。", HttpStatus.BAD_REQUEST.value());
            }*/
            // 本地资源路径
            String localPath = BusConfig.getUploadBaseDir();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            if (StringUtils.isBlank(name)) {
                FileUtils.setAttachmentResponseHeader(response, downloadName);
            } else {
                FileUtils.setAttachmentResponseHeader(response, name);
            }
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 删除上传的文件
     */
    @RequestMapping("del/upload/{resource}")
    @ResponseBody
    @Log(module = "通用", desc = "删除文件", optType = Constants.OptType.REMOVE)
    public ResponseEntity<CusResponseBody> delUpload(@PathVariable String resource, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!FileUtils.checkAllowDownload(resource)) {
                throw new BusinessException("资源文件非法，不允许删除。", HttpStatus.BAD_REQUEST.value());
            }
            // 本地资源路径
            String localPath = BusConfig.getUploadBaseDir();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);

            File file = new File(downloadPath);
            if (file.exists()) {
                file.delete();
            }

            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("删除文件成功");
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除文件失败", e);
            throw new BusinessException("删除文件失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    /**
     * 获取编号
     */
    @RequestMapping("getRandomNo")
    @ResponseBody
    @Log(module = "通用", desc = "获取编号", optType = Constants.OptType.SELECT)
    public ResponseEntity<CusResponseBody> getRandomNo() {
        try {
            long nextId = noUtil.getNextId();
            //构造返回数据
            CusResponseBody cusResponseBody = CusResponseBody.success("获取编号成功", nextId);
            return new ResponseEntity<>(cusResponseBody, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取编号失败", e);
            throw new BusinessException("获取编号失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
}
