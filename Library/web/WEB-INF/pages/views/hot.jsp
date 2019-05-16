<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>逆时光图书馆</title>
    <base href="${basePath}">
    <link rel="stylesheet" href="static/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="static/plugins/bootstrap-validator/css/bootstrapValidator.css">
    <link rel="icon" href="static/imgs/logoko.png">
    <%--https://blog.csdn.net/superperson976/article/details/83354933--%>
    <style>
        html,body{
            height: 100%;
            width: 100%;
        }
        body{
            padding-top: 70px;
        }
        #body{
            max-width: 780px;
            margin: 0 auto;
        }
        #body>nav{
            max-width: 780px;
            margin: 0 auto;
        }
        .thumbnail{
            height: 420px;
        }
        .thumbnail .intro{
            height: 60px;
        }
    </style>
</head>
<body>
    <div id="body">
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${basePath}"><img style="width: 40px;" src="static/imgs/logoko.png" alt="逆时光图书馆" title="逆时光图书馆"></a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">热销图书</a></li>
                        <li><a href="#">所有图书</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">图书分类 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="index?type=sort">出版社</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">作者</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li id="loginMenu">
                            <c:if test="${not empty lu}">
                                <a href="${basePath}login?type=toIndex" target="_blank">${lu.realname}</a>
                            </c:if>
                            <c:if test="${empty lu}">
                                <a href="#" data-toggle="modal" data-target="#modal">登录</a>
                            </c:if>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="row">
            <c:forEach var="hot" items="${hotList}">
                <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="static/upload/${hot.img}" alt="${hot.name}" style="height: 175px;">
                    <div class="caption">
                        <h3>${hot.name}</h3>
                        <p>作者：${hot.aname}</p>
                        <p>出版社：${hot.pname}</p>
                        <p class="intro" title="${hot.intro}">
                            <c:choose>
                                <c:when test="${fn:length(hot.intro)>30}">
                                    ${fn:substring(hot.intro, 0,30 )}...
                                </c:when>
                                <c:otherwise>${hot.intro}</c:otherwise>
                            </c:choose>
                        </p>
                        <p>
                            <a href="index?type=detail&id=${hot.id}" class="btn btn-default" role="button" data-toggle="modal" data-target="#modal">详情</a>
                            <a href="javascript:;" onclick="borBook(${hot.id});" class="btn btn-default" role="button">借阅</a>
                            <span>被借${hot.count}次</span>
                        </p>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
    <div class="modal fade" tabindex="-1" role="dialog" id="modal" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">用户登录</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="infoForm">
                        <div class="form-group">
                            <label for="username" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="loginBtn" class="btn btn-primary">登录</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="static/plugins/jquery/jquery-3.4.0.js"></script>
<script src="static/plugins/bootstrap/js/bootstrap.js"></script>
<script src="static/plugins/bootstrap-validator/js/bootstrapValidator.js"></script>
<script src="static/plugins/bootstrap-validator/js/language/zh_CN.js"></script>
<script>
    var userid='${lu.id}';
    var powerid='${lu.powerid}';
    $(function () {
        $('.modal').on('hidden.bs.modal', function () {
            $(this).removeData('bs.modal');
            // $('.modal .modal-content').empty();
        });
        $('#infoForm').bootstrapValidator({
            /*验证状态图标设置*/
            feedbackIcons:{
                valid:'glyphicon glyphicon-ok',             //验证成功状态
                invalid:'glyphicon glyphicon-remove',       //验证失败状态
                validating:'glyphicon glyphicon-refresh'    //正在验证状态
            },
            /*配置要验证的属性*/
            fields: {
                username: {
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                }
            }
        });
        $('#loginBtn').click(function () {
            var bootstrapValidator = $("#infoForm").data('bootstrapValidator');
            bootstrapValidator.validate();
            if(bootstrapValidator.isValid()){
                $.ajax({
                    url:'login?type=doLogin',
                    data:$("#infoForm").serialize(),
                    method:'post',
                    dataType:'json',
                    success:function (res) {
                        if(res.code>0){
                            $('#loginBtn').parents('.modal').modal('hide');
                            $('#loginMenu').empty().append('<a href="login?type=toIndex" target="_blank">'+res.user.realname+'</a>');
                            userid=res.user.id;
                            powerid=res.user.powerid;
                        }else{
                            alert(res.msg);
                        }
                    },
                    error:function (e) {
                        alert('与服务器连接失败，请稍后再试...');
                        console.log(e);
                    }
                });
            }
        });
        $('#infoForm').submit(function (e) {
            e.preventDefault();
        });
    });
    function borBook(id) {
        if(!userid){
            alert('请先登录！');
            return;
        }
        if(powerid==1){
            alert('管理员不能借阅图书！');
            return;
        }
        if(confirm('确定借阅本书吗？')){
            $.ajax({
                url:'index',
                data:{
                    type:'borrow',
                    bid:id
                },
                method:'post',
                dataType:'json',
                success:function (res) {
                    alert(res.msg);
                    if(res.code>0){
                        location.reload();
                    }
                },
                error:function (e) {
                    alert('与服务器连接失败，请稍后再试...');
                    console.log(e);
                }
            });
        }
    }
</script>
</html>
