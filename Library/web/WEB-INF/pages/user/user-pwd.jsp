<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="static/plugins/bootstrap-validator/css/bootstrapValidator.css">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title">修改密码</h4>
</div>
<div class="modal-body">
    <form class="form-horizontal" id="infoForm">
        <input type="hidden" name="id" id="id" value="${lu.id}">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label"><span class="text-danger">*</span>用户名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="username" disabled id="username" placeholder="请输入用户名"
                       value="${lu.username}">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label"><span class="text-danger">*</span>原密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="oldPwd" name="oldPwd" placeholder="请输入原密码">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label"><span class="text-danger">*</span>新密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
            </div>
        </div>
        <div class="form-group">
            <label for="pwd" class="col-sm-2 control-label"><span class="text-danger">*</span>确认密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="pwd" name="pwd" placeholder="请再次输入密码">
            </div>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    <button type="button" id="saveBtn" class="btn btn-primary">保存</button>
</div>
<script src="static/plugins/bootstrap-validator/js/bootstrapValidator.js"></script>
<script src="static/plugins/bootstrap-validator/js/language/zh_CN.js"></script>
<script>
    $(function () {
        $('#infoForm').bootstrapValidator({
            /*验证状态图标设置*/
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',             //验证成功状态
                invalid: 'glyphicon glyphicon-remove',       //验证失败状态
                validating: 'glyphicon glyphicon-refresh'    //正在验证状态
            },
            /*配置要验证的属性*/
            fields: {
                oldPwd: {
                    validators: {
                        notEmpty: {
                            message: '原密码不能为空'
                        },
                        threshold: 5,//5个字符才开始发送ajax请求验证。
                        remote: {
                            url: 'user',
                            data: function () {
                                var password = $('#oldPwd').val();
                                var id=$('#id').val();
                                return {
                                    type: 'checkPwd',
                                    password: password,
                                    id:id
                                };
                            },
                            type: 'post',
                            message: '原密码不正确'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '密码长度在6-30之间'
                        },
                        different:{
                            field:'oldPwd',
                            message:'新密码不能与原密码相同！'
                        }
                    }
                },
                pwd: {
                    validators: {
                        notEmpty: {
                            message: '请再次输入密码'
                        },
                        identical: {//验证相同
                            field: 'password',
                            message: '两次密码不一致'
                        }
                    }
                }
            }
        });
        $('#saveBtn').click(function () {
            var bootstrapValidator = $("#infoForm").data('bootstrapValidator');
            bootstrapValidator.validate();
            if (bootstrapValidator.isValid()) {
                $.ajax({
                    url: 'user?type=changePwd',
                    data: $("#infoForm").serialize(),
                    method: 'post',
                    dataType: 'json',
                    success: function (res) {
                        alert(res.msg+",请重新登录！");
                        if (res.code > 0) {
                            $('#saveBtn').parents('.modal').modal('hide');
                            top.window.location.replace('login?type=toLogin');
                        }
                    },
                    error: function (e) {
                        alert('与服务器连接失败，请稍后再试...');
                        console.log(e);
                    }
                });
            }
        });
    });
</script>
