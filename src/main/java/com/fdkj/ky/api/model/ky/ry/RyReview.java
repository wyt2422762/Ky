package com.fdkj.ky.api.model.ky.ry;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.constant.EditGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 科研人员审核
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class RyReview {
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
     * 科研人员id
     */
    @NotBlank(message = "科研人员id不能为空")
    private String fk_ryid;

    /**
     * 审核时间
     */
    private String shsj;

    /**
     * 审核意见
     */
    @NotBlank(message = "审核意见不能为空")
    private String shyj;

    /**
     * 审核状态(通过/退回)
     */
    @NotBlank(message = "审核状态不能为空")
    private String shzt;

    /**
     * 审核人id
     */
    private String fk_userid;

    /**
     * 审核人(工号?)
     */
    private String shr;

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
