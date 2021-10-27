package com.fdkj.ky.api.model.ky.xm.zxxm;

import com.alibaba.fastjson.annotation.JSONField;
import com.fdkj.ky.constant.EditGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 纵向项目 - 中检
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class Zxxm_zj {
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
     * 中检时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private String zjsj;

    /**
     * 中检内容
     */
    private String zjnr;

    /**
     * 备注
     */
    private String remark;
}
