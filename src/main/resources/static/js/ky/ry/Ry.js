const RY = {
  //获取科研人员详情
  getDetail: function (id, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYDW/KYRY/getDetail/" + id,
      type: "GET",
      async: false,
      contentType: "application/json;charset=utf-8",
      success: function (data) {
        top.layer.close(loadi); //关闭弹出框
        if (callBack) {
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
  //添加科研人员
  add: function (zj, callBack) {
    let loadi = top.layer.load();
    debugger;
    $.ajax({
      url: ctx + "CZF/KYDW/KYRY/add",
      type: "POST",
      contentType: "application/json;charset=utf-8",
      data: JSON.stringify(zj),
      success: function (data) {
        debugger;
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(data.msg);
        if (callBack) {
          callBack();
        }
        return false;
      },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
        debugger;
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(XMLHttpRequest.responseJSON.msg ? XMLHttpRequest.responseJSON.msg : "操作失败!");
        return false;
      }
    });
    return false;
  },
  //编辑科研人员
  edit: function (xm, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYDW/KYRY/edit",
      type: "PUT",
      contentType: "application/json;charset=utf-8",
      data: JSON.stringify(xm),
      success: function (data) {
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(data.msg);
        if (callBack) {
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
  //删除科研人员
  del: function (id, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYDW/KYRY/del/" + id,
      type: "DELETE",
      success: function (data) {
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(data.msg);
        if (callBack) {
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
  //审核科研人员
  review: function (rysh, callBack) {
    let loadi = top.layer.load();
    $.ajax({
      url: ctx + "CZF/KYDW/KYRY/review",
      type: "PUT",
      contentType: "application/json;charset=utf-8",
      data: JSON.stringify(rysh),
      success: function (data) {
        top.layer.close(loadi); //关闭弹出框
        top.layer.msg(data.msg);
        if (callBack) {
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
  }
}