<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>图书管理系统登录</title>
    <base href="${basePath}">
    <link rel="stylesheet" href="static/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="static/plugins/bootstrap-validator/css/bootstrapValidator.css">
    <link rel="stylesheet" href="static/css/login.css">
    <link rel="icon" href="static/imgs/icon.jpg">
</head>
<body>
<img src="static/imgs/kakarot.jpg" alt="kakarot" id="img">
<div class="container">
    <div>
        <h2>图书管理系统</h2>
    </div>
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">用户登录</h3>
            <form id="form">
                <div class="col-md-9">
                    <div class="form-group">
                        <i class="glyphicon glyphicon-user icon"></i>
                        <input class="form-control required" type="text" placeholder="请输入登录名" id="loginName"
                               name="username" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <i class="glyphicon glyphicon-lock icon"></i>
                        <input class="form-control required" type="password" placeholder="请输入密码" id="password"
                               name="password" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <label class="checkbox pull-left">
                            <input type="checkbox" name="remember" id="remember" value="0"/>记住我
                        </label>
                    </div>
                    <div class="form-group col-md-offset-9">
                        <button type="button" class="btn btn-success pull-right" id="loginBtn">登录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script src="static/plugins/jquery/jquery-3.4.0.js"></script>
<script src="static/plugins/bootstrap/js/bootstrap.js"></script>
<script src="static/plugins/bootstrap-validator/js/bootstrapValidator.js"></script>
<script src="static/plugins/bootstrap-validator/js/language/zh_CN.js"></script>
<script>
    $(function () {
        /*bootstrap验证插件*/
        $('#form').bootstrapValidator({
            /*验证状态图标设置*/
            feedbackIcons:{
                valid:'glyphicon glyphicon-ok',             //验证成功状态
                invalid:'glyphicon glyphicon-remove',       //验证失败状态
                validating:'glyphicon glyphicon-refresh'    //正在验证状态
            },
            /*配置要验证的属性*/
            fields:{
                username:{
                    validators:{
                        notEmpty:{
                            message:'用户名不能为空'
                        }
                    }
                },
                password:{
                    validators:{
                        notEmpty:{
                            message:'密码不能为空'
                        }
                    }
                }
            }
        });
        $('#loginBtn').click(function () {
            doLogin();
        });
        $('body').keydown(function (e) {
            if(e.keyCode==13){
                doLogin();
            }
        });
    });
    function doLogin() {
        var bootstrapValidator = $("#form").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            $.ajax({
                url: 'login?type=doLogin',
                data: $('#form').serialize(),
                method: 'post',
                dataType: 'json',
                success: function (res) {
                    console.log(res);
                    if (res.code == 1) {
                        location.href = 'login?type=toIndex';
                    } else {
                        alert(res.msg);
                    }
                },
                error: function (err) {
                    console.log(err);
                    alert('与服务器连接失败，请稍后再试....');
                }
            });
        }
    }
</script>
</html>
