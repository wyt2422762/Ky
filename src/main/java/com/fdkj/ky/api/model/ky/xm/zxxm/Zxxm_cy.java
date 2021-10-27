package com.fdkj.ky.api.model.ky.xm.zxxm;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.constant.EditGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 纵向项目(成员)
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class Zxxm_cy {
    @NotBlank(message = "id不能为空", groups = {EditGroup.class})
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
     * 项目id
     */
    private String fk_xmid;

    /**
     * 成员编号
     */
    private String code;

    /**
     * 成员姓名
     */
    private String name;

    /**
     * 工作单位
     */
    private String gzdw;

    /**
     * 职称
     */
    private String zc;

    /**
     * 学位
     */
    private String degree;

    /**
     * 承担类型
     */
    private String cdlx;

    /**
     * 贡献率
     */
    private String gxl;

    /**
     * 备注
     */
    private String remark;

    /**
     * 转JSONObject
     *
     * @return res
     */
    public JSONObject toJson() {
        return JSONObject.parseObject(JSONObject.toJSONString(this));
    }
}
