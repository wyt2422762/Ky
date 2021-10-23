package com.fdkj.ky.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 业务配置
 * @author wyt
 */
@Component
public class BusConfig {

    /**
     * 文件上传下载基本路径
     */
    private static String fileBaseDir;

    private static long fileMaxSize;

    @Value("${bus.file.baseDir}")
    public void setFileBaseDir(String fileBaseDir) {
        BusConfig.fileBaseDir = fileBaseDir;
    }

    @Value("${bus.file.max-size}")
    public void setFileMaxSize(long fileMaxSize) {
        BusConfig.fileMaxSize = fileMaxSize;
    }

    /**
     * 获取文件上传最大大小
     *
     * @return 基础路径
     */
    public static long getUploadMaxSize() {
        return fileMaxSize;
    }

    /**
     * 获取上传文件基础路径
     *
     * @return 基础路径
     */
    public static String getUploadBaseDir() {
        return fileBaseDir + File.separator + "upload";
    }

    /**
     * 获取文件下载基础路径
     *
     * @return 基础路径
     */
    public static String getDownLoadBaseDir() {
        return fileBaseDir + File.separator + "download";
    }

    /**
     * 获取临时文件路径
     */
    public static String getTempBaseDir() {
        return fileBaseDir + File.separator + "temp";
    }
}
