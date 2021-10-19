package com.fdkj.ky.api.model.system;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.constant.CreateGroup;
import com.fdkj.ky.constant.EditGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 学院
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class Xy {
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
     * 学院编码
     */
    @NotBlank(message = "学院编码不能为空")
    private String code;

    /**
     * 学院名称
     */
    @NotBlank(message = "学院名称不能为空")
    private String name;

    /**
     * 学院院长
     */
    private String yz;

    /**
     * 父id
     */
    private String fid;

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
