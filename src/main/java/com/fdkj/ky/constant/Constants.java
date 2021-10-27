package com.fdkj.ky.constant;

/**
 * 通用常量信息
 *
 * @author wyt
 */
public class Constants {

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 字典
     */
    public static class Dict {
        /**
         * 性别
         */
        public static final String GENDER = "a7dd235d-d5f2-401e-9374-ea9f93d9881a";
        /**
         * 导师类型
         */
        public static final String TUTOR = "51c66d4a-f376-4f00-907c-0ac9a243992f";
        /**
         * 学历
         */
        public static final String EDU = "d7e65fc5-af31-4d8c-a828-9145ad56ada0";
        /**
         * 学位
         */
        public static final String DEGREE = "012f4248-cec4-4df1-aae3-ea96a86f7420";
        /**
         * 项目状态
         */
        public static final String XMZT = "0f139cf6-5044-4828-8a89-0d0d94faea94";
        /**
         * 项目级别
         */
        public static final String XMJB = "51f6cf7a-ced4-4073-9fd9-f07aeb1396f9";
        /**
         * 负责人类型
         */
        public static final String FZRLX = "7a844ed3-059f-44c9-807c-4113c49fc712";
    }

    /**
     * 操作类型
     */
    public static class OptType {
        /**
         * 查询
         */
        public static final String SELECT = "查询";

        /**
         * 新增
         */
        public static final String ADD = "新增";

        /**
         * 编辑
         */
        public static final String EDIT = "编辑";

        /**
         * 添加/编辑
         */
        public static final String AE = "添加/编辑";

        /**
         * 删除
         */
        public static final String DEL = "删除";

        /**
         * 清空
         */
        public static final String CLEAR = "清空";

        /**
         * 导入
         */
        public static final String IMPORT = "导入";

        /**
         * 导出
         */
        public static final String EXPORT = "导出";

        /**
         * 上传
         */
        public static final String UPLOAD = "上传";

        /**
         * 下载
         */
        public static final String DOWNLOAD = "下载";

        /**
         * 删除文件
         */
        public static final String REMOVE = "删除文件";

        /**
         * 提交审核
         */
        public static final String TO_REVIEW = "提交审核";

        /**
         * 审核
         */
        public static final String REVIEW = "审核";

        /**
         * 登录
         */
        public static final String LOGIN = "登录";

        /**
         * 登出
         */
        public static final String LOGOUT = "登出";

        /**
         * 确认
         */
        public static final String CONFIRM = "确认";
    }

    /**
     * 人员状态
     */
    public static class RYZT {
        /**
         * 已保存
         */
        public static final String YBC = "已保存";

        /**
         * 提交
         */
        public static final String TJ = "提交";

        /**
         * 退回
         */
        public static final String TH = "退回";

        /**
         * 通过
         */
        public static final String TG = "通过";
    }

    /**
     * 人员状态
     */
    public static class REVIEW_YPE {
        /**
         * 提交
         */
        public static final String SUBMIT = "提交";

        /**
         * 同意
         */
        public static final String AGREE = "通过";

        /**
         * 退回
         */
        public static final String BACK = "退回";
    }
}
