<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>图书管理系统登录</title>
    <base href="${basePath}">
    <link rel="stylesheet" href="static/plugins/bootstrap/css/bootstrap.css">
    <link rel="icon" href="static/imgs/icon.jpg">
    <style>
        #img {
            width: 100%;
            position: fixed;
            right: 0;
            bottom: 0;
            min-width: 100%;
            min-height: 100%;
            height: auto;
            z-index: -100;
            background-size: cover;
        }

        .form {
            background: rgba(255, 255, 255, 0.2);
            width: 420px;
            margin: 120px auto;
        }

        /*阴影*/
        .glyphicon {
            display: inline-block;
            top: 28px;
            left: 8px;
            position: relative;
            color: #ccc;
        }

        input[type="text"], input[type="password"] {
            padding-left: 26px;
        }

        .checkbox {
            padding-left: 21px;
        }

        h2 {
            color: white;
        }
    </style>
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
                        <i class="glyphicon glyphicon-user"></i>
                        <input class="form-control required" type="text" placeholder="请输入登录名" id="loginName"
                               name="username" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <i class="glyphicon glyphicon-lock"></i>
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
<script>
    $(function () {
        function doLogin() {
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
        $('#loginBtn').click(function () {
            doLogin();
        });
        $('body').keydown(function (e) {
            if(e.keyCode==13){
                doLogin();
            }
        });
    });
</script>
</html>
