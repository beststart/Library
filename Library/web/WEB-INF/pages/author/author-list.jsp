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
    <link rel="stylesheet" href="static/plugins/bootstrap-table/bootstrap-table.css">
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
                    <label class="control-label" for="sname">作者名</label>
                    <input type="text" class="form-control" id="sname">
                </div>
                <div class="form-group">
                    <label class="control-label" for="sstatus">状态</label>
                    <select id="sstatus" class="form-control">
                        <option value="">[请选择]</option>
                        <option value="1">可用</option>
                        <option value="0">禁用</option>
                    </select>
                </div>
                <div class="form-group" style="text-align:left;">
                    <button type="button" style="margin-left:10px" id="btn_query" class="btn btn-primary">查询</button>
                    <button type="button" style="margin-left:10px" id="btn_clear" class="btn btn-primary">清空</button>
                </div>
            </form>
        </div>
    </div>
    <div class="table-responsive">
        <div id="toolbar" class="btn-group">
            <a href="author?type=toEdit" data-toggle="modal" data-target="#modal" class="btn btn-default">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </a>
        </div>
        <table id="infoTable"></table>
    </div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" id="modal" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        </div>
    </div>
</div>
<script src="static/plugins/jquery/jquery-3.4.0.js"></script>
<script src="static/plugins/bootstrap/js/bootstrap.js"></script>
<script src="static/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="static/plugins/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script>
    $(function () {
        $('#infoTable').bootstrapTable({
            url: 'author?type=getPage',
            method: 'get',
            striped: true,                  //各行换色
            cache: false,                   //是否缓存，默认true
            sidePagination: 'server',       //服务端分页
            pageNumber: 1,                  //默认加载第一页
            pagination: true,               //是否显示分页
            pageSize: 5,                    //每页显示条数
            pageList: [5, 10, 20, 30, 50],     //可供选择的每页行数
            showRefresh: true,              //是否显示刷新按钮
            clickToSelect: true,            //是否启用点击选中行
            // height: 500,                 //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                 //每一行的唯一标识，一般为主键列
            showToggle: true,               //是否显示详细视图和列表视图的切换按钮
            cardView: false,                //是否显示详细视图
            toolbar: '#toolbar',            //工具按钮用哪个容器
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: '编号'
            }, {
                field: 'name',
                title: '作者名'
            }, {
                field: 'remake',
                title: '备注',
                formatter: remakeFun
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
            var name = $('#sname').val();
            var status = $('#sstatus').val();
            $('#infoTable').bootstrapTable('refresh', {
                query: {
                    name: name,
                    status: status
                }
            });
        });
        $('#btn_clear').click(function () {
            $('input,select').val('');
        });
        $('#modal').on('hidden.bs.modal', function () {
            $(this).removeData('bs.modal');
            $('#modal .modal-content').empty();
        });
    });

    function statusFun(value, row) {
        if (value == 1) {
            return '<button class="btn btn-success" type="button" onclick="updateStatus(' + row.id + ',0);">可用</button>';
        }
        return '<button class="btn btn-danger" type="button" onclick="updateStatus(' + row.id + ',1);">禁用</button>';
    }

    function optFun(value, row) {
        return '<a class="btn btn-primary" href="author?type=toEdit&id=' + row.id + '" data-toggle="modal" data-target="#modal">修改</a> <a class="btn btn-primary" onclick="delInfo(' + row.id + ');" type="button" >删除</a>';
    }

    function remakeFun(value) {
        if(value){
            if(value.length>10){
                var str=value.substr(0,10)+'...';
                return '<span title="'+value+'">'+str+'</span>';
            }
        }
        return '暂无备注';
    }

    function updateStatus(id, status) {
        if (confirm('确定更改状态吗？')) {
            $.ajax({
                url: 'author',
                data: {
                    type: 'updateStatus',
                    id: id,
                    status: status
                },
                method: 'post',
                dataType: 'json',
                success: function (res) {
                    alert(res.msg);
                    if (res.code > 0) {
                        $('#infoTable').bootstrapTable('refresh');
                    }
                },
                error: function (e) {
                    alert('与服务器连接失败，请稍后再试...');
                    console.log(e);
                }
            });
        }
    }

    function delInfo(id) {
        if (confirm('确定删除本数据吗？')) {
            $.ajax({
                url: 'author',
                data: {
                    type: 'delete',
                    id: id
                },
                method: 'post',
                dataType: 'json',
                success: function (res) {
                    alert(res.msg);
                    if (res.code > 0) {
                        $('#infoTable').bootstrapTable('refresh');
                    }
                },
                error: function (e) {
                    alert('与服务器连接失败，请稍后再试...');
                    console.log(e);
                }
            });
        }
    }
</script>
</body>
</html>
