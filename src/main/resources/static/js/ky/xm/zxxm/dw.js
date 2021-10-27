const ZXXM_DW = {
  //获取
  getDetail: function (id, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYXM/ZXXM/DW/getDetail/" + id,
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
  //保存
  add: function (zxxm_dw, callBack) {
    debugger;
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYXM/ZXXM/DW/add",
      type: "POST",
      dataType: "json",
      contentType: "application/json;charset=utf-8",
      data: JSON.stringify(zxxm_dw),
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
  //编辑
  edit: function (zxxm_dw, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYXM/ZXXM/DW/edit",
      type: "PUT",
      dataType: "json",
      contentType: "application/json;charset=utf-8",
      data: JSON.stringify(zxxm_dw),
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
  //删除
  del: function (id, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYXM/ZXXM/DW/del/" + id,
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