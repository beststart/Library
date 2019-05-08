<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="static/imgs/icon.jpg">
    <title>逆时光图书馆后台</title>
    <link href="static/plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="static/css/dashboard.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">逆时光图书馆</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="user?type=toEdit&id=${lu.id}" data-toggle="modal" data-target="#userInfo">${lu.realname}</a></li>
                <li><a href="user?type=toPwd&id=${lu.id}" data-toggle="modal" data-target="#userInfo">修改密码</a></li>
                <li id="exit"><a href="javascript:;">退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="user?type=toList" target="view">用户管理</a></li>
                <li><a href="power?type=toList" target="view">权限管理</a></li>
                <li><a href="book?type=toList" target="view">图书管理</a></li>
                <li><a href="author?type=toList" target="view">作者管理</a></li>
                <li><a href="press?type=toList" target="view">出版社管理</a></li>
                <li><a href="record?type=toList" target="view">借阅记录</a></li>
            </ul>
        </div>
            <iframe src="user?type=toList" width="100%" id="main" name="view" frameborder="0"></iframe>
    </div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" id="userInfo" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        </div>
    </div>
</div>
<script src="static/plugins/jquery/jquery-3.4.0.js"></script>
<script src="static/plugins/bootstrap/js/bootstrap.js"></script>
<script>
    /*iframe高度自适应*/
    function changeFrameHeight(){
        var ifm= document.getElementById("main");
        ifm.height=document.documentElement.clientHeight-56;
    }
    $(function () {
       changeFrameHeight();
       $('.nav-sidebar li').click(function () {
           $(this).addClass('active').siblings().removeClass('active');
       });
       $('#exit').click(function () {
           if(confirm('确定退出本系统吗？')){
                $.ajax({
                    url:'login',
                    data:{type:'exit'},
                    method:'post',
                    dataJson:'json',
                    success:function (res) {
                        if(res.code>0){
                            location.replace('login?type=toLogin');
                        }
                    }
                });
           }
       });
        $('.modal').on('hidden.bs.modal', function () {
            $(this).removeData('bs.modal');
            $('.modal .modal-content').empty();
        });
    });
    $(window).resize(function () {
        changeFrameHeight();
    });
</script>
</body>
</html>
