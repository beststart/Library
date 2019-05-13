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
            height: 390px;
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
                                <li class="active"><a href="#">出版社</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">作者</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#" data-toggle="modal" data-target="#modal">登录</a></li>
                        <li><a href="${basePath}login?type=toLogin" target="_blank">后台</a></li>
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
                        <p class="intro" title="${hot.intro}">
                            <c:choose>
                                <c:when test="${fn:length(hot.intro)>30}">
                                    ${fn:substring(hot.intro, 0,30 )}...
                                </c:when>
                                <c:otherwise>${hot.intro}</c:otherwise>
                            </c:choose>
                        </p>
                        <p>
                            <a href="#" class="btn btn-default" role="button">详情</a>
                            <a href="#" class="btn btn-default" role="button">借阅</a>
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
<script>
    $(function () {
        $('.modal').on('hidden.bs.modal', function () {
            $(this).removeData('bs.modal');
            $('.modal .modal-content').empty();
        });
    });
</script>
</html>
