package com.fdkj.ky.api.model.ky.xm.zxxm;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fdkj.ky.constant.EditGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 纵向项目(基本信息)
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class Zxxm {
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
     * 项目编号
     */
    @NotBlank(message = "项目编号不能为空")
    private String no;

    /**
     * 批准号
     */
    private String pzh;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空")
    private String name;

    /**
     * 负责人
     */
    @NotBlank(message = "项目负责人不能为空")
    private String fzr;

    /**
     * 负责人类型
     */
    private String fzrlx;

    /**
     * 学院id
     */
    @NotBlank(message = "学院id不能为空")
    private String fk_xyid;

    /**
     * 科研机构id
     */
    private String fk_jgid;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 项目级别
     */
    private String level;

    /**
     * 项目状态
     */
    private String zt;

    /**
     * 项目进度
     */
    private String jd;

    /**
     * 立项日期
     */
    @JSONField(format="yyyy-MM-dd")
    private Date lxrq;

    /**
     * 开始日期
     */
    @JSONField(format="yyyy-MM-dd")
    private Date ksrq;

    /**
     * 计划结项日期
     */
    @JSONField(format="yyyy-MM-dd")
    private Date jhjxrq;

    /**
     * 实际结项日期
     */
    @JSONField(format="yyyy-MM-dd")
    private Date sjjxrq;

    /**
     * 预算(万元)
     */
    private String ys;

    /**
     * 批准经费(万元)
     */
    private String pzjf;

    /**
     * 配套经费(万元)
     */
    private String ptjf;

    /**
     * 外拨经费(万元)
     */
    private String wbjf;

    /**
     * 成果形式
     */
    private String cgxs;

    /**
     * 备注
     */
    private String remark;

    /**
     * 成员list
     */
    private List<Zxxm_cy> cyList;

    /**
     * 单位list
     */
    private List<Zxxm_dw> dwList;

    /**
     * 附件list
     */
    private List<Zxxm_fj> fjList;

    /**
     * 转JSONObject
     *
     * @return res
     */
    public JSONObject toJson() {
        return JSONObject.parseObject(JSONObject.toJSONString(this));
    }
}
