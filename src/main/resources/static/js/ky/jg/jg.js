const JG = {
  //获取机构详情
  getDetail: function (id, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYDW/KYJG/getDetail/" + id,
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
  //保存机构
  add: function (jg, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYDW/KYJG/add",
      type: "POST",
      dataType: "json",
      contentType: "application/json;charset=utf-8",
      data: JSON.stringify(jg),
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
  //编辑机构
  edit: function (jg, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYDW/KYJG/edit",
      type: "PUT",
      dataType: "json",
      contentType: "application/json;charset=utf-8",
      data: JSON.stringify(jg),
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
  //删除机构
  del: function (id, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYDW/KYJG/del/" + id,
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
  //删除机构附件
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