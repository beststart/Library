<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="static/plugins/bootstrap-validator/css/bootstrapValidator.css">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">出版社信息</h4>
</div>
<div class="modal-body">
    <form class="form-horizontal" id="infoForm">
        <input type="hidden" name="id" value="${press.id}">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label"><span class="text-danger">*</span>出版社</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="name" id="name" placeholder="请输入出版社名称" value="${press.name}">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">联系人</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="contact" id="contact" placeholder="请输入联系人" value="${press.contact}">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">联系电话</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="phone" id="phone" placeholder="请输入联系电话" value="${press.phone}">
            </div>
        </div>
        <div class="form-group">
            <label for="remake" class="col-sm-2 control-label">地址</label>
            <div class="col-sm-10">
                <textarea class="form-control" name="address" id="address" rows="3">${press.address}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="remake" class="col-sm-2 control-label">备注</label>
            <div class="col-sm-10">
                <textarea class="form-control" name="remake" id="remake" rows="3">${press.remake}</textarea>
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
               name:{
                   validators:{
                       notEmpty:{
                           message:'用户名不能为空'
                       }
                   }
               },
               phone:{
                   validators:{
                       regexp:{
                           regexp:/^1[0-9]{10}$/,
                           message:'请输入正确的手机号码'
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
                    url:'press?type=doEdit',
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
