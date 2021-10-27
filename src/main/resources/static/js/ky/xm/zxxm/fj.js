const ZXXM_FJ = {
  //获取附件列表
  getList: function (fk_xmid, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYXM/ZXXM/FJ/getList/" + fk_xmid,
      type: "GET",
      success: function (data) {
        top.layer.close(loadi); //关闭弹出框
        if(callBack){
          callBack(data);
        }
      },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
        top.layer.close(loadi); //关闭弹出框
      }
    });
    return false;
  },
  //删除附件(数据库)
  del: function (id, callBack) {
    debugger;
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYXM/ZXXM/FJ/del/" + id,
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
  },
  //删除附件(文件)
  delFj: function (path, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "common/del/upload/" + path,
      type: "DELETE",
      contentType: "application/json;charset=utf-8",
      success: function (data) {
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(data.msg);
        callBack();
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
  //下载机构附件
  downloadFj: function (path, name) {
    let dUrl = ctx + "common/download/upload?resource=" + path;
    if (name) {
      dUrl += "&name=" + name;
    }
    window.open(dUrl);
  }
}