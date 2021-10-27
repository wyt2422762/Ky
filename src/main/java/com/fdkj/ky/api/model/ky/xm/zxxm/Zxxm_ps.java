package com.fdkj.ky.api.model.ky.xm.zxxm;

import com.alibaba.fastjson.annotation.JSONField;
import com.fdkj.ky.constant.EditGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 纵向项目 - 评审
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class Zxxm_ps {
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
     * 评审时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date pssj;

    /**
     * 评审内容
     */
    private String psnr;

    /**
     * 评审专家
     */
    private String pszj;

    /**
     * 备注
     */
    private String remark;
}
