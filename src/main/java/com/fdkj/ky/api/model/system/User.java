package com.fdkj.ky.api.model.system;

import com.alibaba.fastjson.JSONObject;
import com.fdkj.ky.constant.EditGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 接口返回的用户信息实体类
 *
 * @author wyt
 */
@Data
@Accessors(chain = true)
public class User {
    @NotBlank(message = "用户id不能为空", groups = {EditGroup.class})
    private String id;
    private String addtime;
    private String fk_qybm;
    private String fk_xtglid;

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String salt;
    @NotBlank(message = "角色id不能为空")
    private String fk_jsid;
    private Boolean is_lock;
    private String lxdh;
    private String lxr;

    private String roleType;

    /**
     * 学院id
     */
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
