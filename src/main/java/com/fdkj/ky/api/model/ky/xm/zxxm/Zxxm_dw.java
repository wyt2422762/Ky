package com.fdkj.ky.api.model.ky.xm.zxxm;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.constant.EditGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 纵向项目(单位)
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class Zxxm_dw {
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
     * 单位名称
     */
    private String dwmc;

    /**
     * 联系人
     */
    private String lxr;

    /**
     * 联系电话
     */
    private String lxdh;

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
