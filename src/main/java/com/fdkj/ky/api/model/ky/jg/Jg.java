package com.fdkj.ky.api.model.ky.jg;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.constant.EditGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 科研机构
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class Jg {
    @NotBlank(message = "机构id不能为空", groups = {EditGroup.class})
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
     * 机构编号
     */
    private String code;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 负责人
     */
    private String fzr;

    /**
     * 联系人
     */
    private String lxr;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 成立日期
     */
    private String clrq;

    /**
     * 挂靠单位
     */
    private String gkdw;

    /**
     * 人事单位
     */
    private String rsdw;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String tel;

    /**
     * 传真
     */
    private String fax;

    /**
     * 地址
     */
    private String addr;

    /**
     * 邮编
     */
    private String postcode;

    /**
     * 网址
     */
    private String url;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String zt;

    /**
     * 学院id
     */
    @NotBlank(message = "机构id不能为空")
    private String fk_xyid;

    /**
     * 转JSONObject
     *
     * @return res
     */
    public JSONObject toJson() {
        return JSONObject.parseObject(JSONObject.toJSONString(this));
    }
}
