<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="static/imgs/icon.jpg">
    <title>逆时光图书馆后台</title>
    <link href="static/plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="static/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="panel panel-default" style="margin-top: 5px">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form id="formSearch" class="form-inline">
                <div class="form-group">
                    <label class="control-label" for="uname">借书人</label>
                    <select id="uname" class="form-control">
                        <option value="">[请选择]</option>
                        <c:forEach var="u" items="${ul}">
                            <option value="${u.id}">${u.realname}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label class="control-label" for="bname">书名</label>
                    <select id="bname" class="form-control">
                        <option value="">[请选择]</option>
                        <c:forEach var="b" items="${bl}">
                            <option value="${b.id}">${b.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label class="control-label" for="btime">借书时间</label>
                    <input type="text" class="form-control" id="btime" placeholder="请选择借书时间范围">
                </div>
                <div class="form-group">
                    <label class="control-label" for="rtime">还书时间</label>
                    <input type="text" class="form-control" id="rtime" placeholder="请选择还书时间范围">
                </div>
                <div class="form-group" style="text-align:left;">
                    <button type="button" style="margin-left:10px" id="btn_query" class="btn btn-primary">查询</button>
                    <button type="button" style="margin-left:10px" id="btn_clear" class="btn btn-primary">清空</button>
                </div>
            </form>
        </div>
    </div>
            <div class="table-responsive">
                <table id="infoTable">
                </table>
            </div>
        </div>
</body>
<script src="static/plugins/jquery/jquery-3.4.0.js"></script>
<script src="static/plugins/bootstrap/js/bootstrap.js"></script>
<script src="static/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="static/plugins/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="static/plugins/laydate/laydate.js"></script>
<script>
    $(function () {
        laydate.render({
            elem: '#btime'
            ,range: true
        });
        laydate.render({
            elem: '#rtime'
            ,range: true
        });
        $('#infoTable').bootstrapTable({
            url: 'record?type=getPage',
            method: 'get',
            striped: true,                  //各行换色
            cache: false,                   //是否缓存，默认true
            sidePagination: 'server',       //服务端分页
            pageNumber: 1,                  //默认加载第一页
            pagination: true,               //是否显示分页
            pageSize: 5,                    //每页显示条数
            pageList: [5, 10, 20, 30, 50],     //可供选择的每页行数
            clickToSelect: true,            //是否启用点击选中行
            // height: 500,                 //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            // queryParams:function(param){
            //
            // },
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: '编号'
            }, {
                field: 'uname',
                title: '借书人'
            }, {
                field:'bname',
                title:'书名'
            },{
                field:'borrowTime',
                title:'借书时间'
            },{
                field:'returnTime',
                title:'还书时间'
            }, {
                field: 'status',
                title: '状态',
                formatter: statusFun
            }, {
                field: 'opt',
                title: '操作',
                formatter: optFun
            }]
        });
        $('#btn_query').click(function () {
            $('#infoTable').bootstrapTable('refresh', {
                query: {
                    offset:0,
                    userid: $('#uname').val(),
                    bookid: $('#bname').val(),
                    borrowTime:$('#btime').val(),
                    returnTime:$('#rtime').val()
                }
            });
        });
        $('#btn_clear').click(function () {
            $('input,select').val('');
        });
    });
    function statusFun(value, row) {
        if (row.returnTime) {
            return '<button class="btn btn-success" type="button">已还</button>';
        }
        return '<button class="btn btn-danger" type="button">未还</button>';
    }

    function optFun(value, row) {
        if(row.returnTime){
            return '暂无';
        }
        return '<a class="btn btn-primary" onclick="rBook(' + row.id + ','+row.bookid+');" type="button" >还书</a>';
    }
    function rBook(id,bookid) {
        if(confirm('确定归还本书吗？')){
            $.ajax({
                url:'record',
                data:{
                    type:'rBook',
                    id:id,
                    bookid:bookid
                },
                method:'post',
                dataType:'json',
                success:function (res) {
                    alert(res.msg);
                    $('#infoTable').bootstrapTable('refresh');
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
