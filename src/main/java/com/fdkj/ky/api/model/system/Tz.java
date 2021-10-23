package com.fdkj.ky.api.model.system;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.constant.EditGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 通知公告
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class Tz {
    @NotBlank(message = "通知id不能为空", groups = {EditGroup.class})
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
     * 学院id
     */
    private String fk_xyid;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 级别(校级，院级)
     */
    private String level;

    /**
     * 发布时间
     */
    private String fbsj;

    /**
     * 发布状态(已发布，未发布)
     */
    private String zt = "未发布";

    /**
     * 备注
     */
    private String remark;

    /**
     * 附件
     */
    private List<TzFj> fjList;

    /**
     * 转JSONObject
     *
     * @return res
     */
    public JSONObject toJson() {
        return JSONObject.parseObject(JSONObject.toJSONString(this));
    }
}
