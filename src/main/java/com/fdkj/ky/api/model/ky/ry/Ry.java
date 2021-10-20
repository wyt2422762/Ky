package com.fdkj.ky.api.model.ky.ry;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.constant.EditGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 科研人员
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class Ry {
    @NotBlank(message = "学院id不能为空", groups = {EditGroup.class})
    private String id;

    /**
     * 系统id
     */
    private String fk_xtglid;

    /**
     * 区域编码
     */
    private String fk_qybm;

    /**
     * 添加时间
     */
    private String addtime;

    /**
     * 科研机构id
     */
    private String fk_jgid;

    /**
     * 职工号
     */
    private String zgh;

    /**
     * 人事单位
     */
    private String rsdw;

    /**
     * 姓名
     */
    private String name;

    /**
     * 英文名
     */
    private String name_en;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 生日
     */
    private String birth;

    /**
     * 身份证号
     */
    private String idNo;

    /**
     * 职称
     */
    private String zc;

    /**
     * 学位
     */
    private String degree;

    /**
     * 学历
     */
    private String edu;

    /**
     * 导师类型(博导、硕导、非导师)
     */
    private String tutor;

    /**
     * 研究方向
     */
    private String yjfx;

    /**
     * 荣誉称号
     */
    private String rych;

    /**
     * 学科门类
     */
    private String xkml;

    /**
     * 毕业学校信息
     */
    private String school;

    /**
     * 进修培训情况
     */
    private String train;

    /**
     * 出国经历
     */
    private String cgjl;

    /**
     * 兼职
     */
    private String jz;

    /**
     * 学术特长
     */
    private String xstc;

    /**
     * qq
     */
    private String qq;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String zt = "已保存";

    /**
     * 转JSONObject
     *
     * @return res
     */
    public JSONObject toJson() {
        return JSONObject.parseObject(JSONObject.toJSONString(this));
    }
}
