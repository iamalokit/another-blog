$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/comments/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'commentId', index: 'commentId', width: 50, key: true, hidden: true},
            {label: 'Body', name: 'commentBody', index: 'commentBody', width: 120},
            {label: 'Create Time', name: 'commentCreateTime', index: 'commentCreateTime', width: 60},
            {label: 'Commentator', name: 'commentator', index: 'commentator', width: 60},
            {label: 'Email', name: 'email', index: 'email', width: 90},
            {label: 'Status', name: 'commentStatus', index: 'commentStatus', width: 60, formatter: statusFormatter},
            {label: 'Reply Body', name: 'replyBody', index: 'replyBody', width: 120},
        ],
        height: 700,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: 'Loading Comments...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
    function statusFormatter(cellvalue) {
        if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 80%;\">Pending</button>";
        }
        else if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 80%;\">Verified</button>";
        }
    }

});


function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}


function checkDoneComments() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认审核通过吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/comments/checkDone",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("审核成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    );
}


function deleteComments() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "Delete",
        text: "Are you sure you want to delete selected comments?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/comments/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("Deleted Successfully", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    );
}


function reply() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    var rowData = $("#jqGrid").jqGrid('getRowData', id);
    console.log(rowData);
    if (rowData.commentStatus.indexOf('Pending') > -1) {
        swal("请先审核该评论再进行回复!", {
            icon: "warning",
        });
        return;
    }
    $("#replyBody").val('');
    $('#replyModal').modal('show');
}

$('#saveButton').click(function () {
    var replyBody = $("#replyBody").val();
    if (!validCN_ENString2_100(replyBody)) {
        swal("Comments are limited to 200 characters!", {
            icon: "warning",
        });
        return;
    } else {
        var url = '/admin/comments/reply';
        var id = getSelectedRow();
        var params = {"commentId": id, "replyBody": replyBody}
        $.ajax({
            type: 'POST',
            url: url,
            data: params,
            success: function (result) {
                if (result.resultCode == 200) {
                    $('#replyModal').modal('hide');
                    swal("Saved Successfully", {
                        icon: "success",
                    });
                    reload();
                }
                else {
                    $('#replyModal').modal('hide');
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("Unable to save reply", {
                    icon: "error",
                });
            }
        });
    }
});
