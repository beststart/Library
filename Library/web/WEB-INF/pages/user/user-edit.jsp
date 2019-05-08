<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="static/plugins/bootstrap-validator/css/bootstrapValidator.css">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">用户信息</h4>
</div>
<div class="modal-body">
    <form class="form-horizontal" id="infoForm">
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label"><span class="text-danger">*</span>用户名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="username" <c:if test="${not empty user}">disabled</c:if> id="username" placeholder="请输入用户名" value="${user.username}">
            </div>
        </div>
        <c:if test="${empty user}">
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label"><span class="text-danger">*</span>密码</label>
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
        </c:if>
        <div class="form-group">
            <label for="realname" class="col-sm-2 control-label"><span class="text-danger">*</span>真实姓名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="realname" value="${user.realname}" id="realname" placeholder="请输入真实姓名">
            </div>
        </div>
        <div class="form-group">
            <label for="age" class="col-sm-2 control-label">年龄</label>
            <div class="col-sm-10">
                <input type="number" class="form-control" name="age" value="${user.age}" id="age" placeholder="请输入年龄">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-2 control-label">联系方式</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="phone" value="${user.phone}" id="phone" placeholder="联系方式">
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
                       },
                       stringLength:{
                           min:6,
                           max:30,
                           message:'用户名长度在6到30之间'
                       },
                       threshold:5,//5个字符才开始发送ajax请求验证。
                       remote:{
                           url:'user',
                           data:function () {
                                var username=$('#username').val();
                                return {
                                    type:'checkUserName',
                                    username:username
                                };
                           },
                           type:'post',
                           message:'用户名已存在'
                       },
                       regexp:{
                           regexp:/^[a-zA-Z0-9_]+$/,
                           message:'用户名只能输入字母数字和下划线'
                       }
                   }
               },
               password:{
                   validators:{
                       notEmpty:{
                           message:'密码不能为空'
                       },
                       stringLength:{
                           min:6,
                           max:30,
                           message:'密码长度在6-30之间'
                       }
                   }
               },
               pwd:{
                   validators:{
                       notEmpty:{
                           message:'请再次输入密码'
                       },
                       identical:{//验证相同
                           field:'password',
                           message:'两次密码不一致'
                       }
                   }
               },
               realname:{
                   validators:{
                       notEmpty:{
                           message:'请输入真实姓名'
                       }
                   }
               },
               age:{
                   validators:{
                       between:{
                           min:0,
                           max:120,
                           message:'年龄必须在0-120之间'
                       }
                   }
               },
               phone:{
                   validators:{
                       regexp:{
                           regexp:/^1[0-9]{10}$/,
                           message:'请输入正确手机号码'
                       }
                   }
               }

           }
       });
       $('#saveBtn').click(function () {
           var bootstrapValidator = $("#infoForm").data('bootstrapValidator');
           bootstrapValidator.validate();
           if(bootstrapValidator.isValid()){
                $.ajax({
                    url:'user?type=doEdit',
                    data:$("#infoForm").serialize(),
                    method:'post',
                    dataType:'json',
                    success:function (res) {
                        alert(res.msg);
                        if(res.code>0){
                            $('#saveBtn').parents('.modal').modal('hide');
                            $('#infoTable').bootstrapTable('refresh');
                        }
                    },
                    error:function (e) {
                        alert('与服务器连接失败，请稍后再试...');
                        console.log(e);
                    }
                });
           }
       });
    });
</script>
