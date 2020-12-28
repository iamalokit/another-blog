$(function () {
    $('#updateWebsiteButton').click(function () {
        $("#updateWebsiteButton").attr("disabled", true);
        var params = $("#websiteForm").serialize();
        $.ajax({
            type: "POST",
            url: "/admin/configurations/website",
            data: params,
            success: function (result) {
                if (result.resultCode == 200 && result.data) {
                    swal("保存成功", {
                        icon: "success",
                    });
                }
                else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });
    });

    $('#updateUserInfoButton').click(function () {
        $("#updateUserInfoButton").attr("disabled", true);
        var params = $("#userInfoForm").serialize();
        $.ajax({
            type: "POST",
            url: "/admin/configurations/userInfo",
            data: params,
            success: function (result) {
                if (result.resultCode == 200&& result.data) {
                    swal("保存成功", {
                        icon: "success",
                    });
                }
                else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });
    });

    $('#updateFooterButton').click(function () {
        $("#updateFooterButton").attr("disabled", true);
        var params = $("#footerForm").serialize();
        $.ajax({
            type: "POST",
            url: "/admin/configurations/footer",
            data: params,
            success: function (result) {
                if (result.resultCode == 200&& result.data) {
                    swal("保存成功", {
                        icon: "success",
                    });
                }
                else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });
    });

})
