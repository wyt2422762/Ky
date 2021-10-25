package com.fdkj.ky.api.model.ky.jg;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 科研机构附件
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class JgFj {
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
     * 附件名称
     */
    private String name;

    /**
     * 附件路径
     */
    private String path;

    /**
     * 附件类型
     */
    private String type;

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
