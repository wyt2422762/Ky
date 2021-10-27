const ZXXM_CY = {
  //获取项目
  getDetail: function (id, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYXM/ZXXM/CY/getDetail/" + id,
      type: "GET",
      async: false,
      contentType: "application/json;charset=utf-8",
      success: function (data) {
        top.layer.close(loadi); //关闭弹出框
        if(callBack){
          callBack(data);
        }
        return false;
      },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(XMLHttpRequest.responseJSON.msg ? XMLHttpRequest.responseJSON.msg : "操作失败!");
        return false;
      }
    });
    return false;
  },
  //保存项目
  add: function (zxxm_cy, callBack) {
    debugger;
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYXM/ZXXM/CY/add",
      type: "POST",
      dataType: "json",
      contentType: "application/json;charset=utf-8",
      data: JSON.stringify(zxxm_cy),
      success: function (data) {
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(data.msg);
        if(callBack){
          callBack();
        }
        return false;
      },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(XMLHttpRequest.responseJSON.msg ? XMLHttpRequest.responseJSON.msg : "操作失败!");
        return false;
      }
    });
    return false;
  },
  //编辑项目
  edit: function (zxxm_cy, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYXM/ZXXM/CY/edit",
      type: "PUT",
      dataType: "json",
      contentType: "application/json;charset=utf-8",
      data: JSON.stringify(zxxm_cy),
      success: function (data) {
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(data.msg);
        if(callBack){
          callBack();
        }
        return false;
      },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(XMLHttpRequest.responseJSON.msg ? XMLHttpRequest.responseJSON.msg : "操作失败!");
        return false;
      }
    });
    return false;
  },
  //删除项目
  del: function (id, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYXM/ZXXM/CY/del/" + id,
      type: "DELETE",
      success: function (data) {
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(data.msg);
        if(callBack){
          callBack();
        }
      },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(XMLHttpRequest.responseJSON.msg ? XMLHttpRequest.responseJSON.msg : "操作失败!");
      }
    });
    return false;
  }
}