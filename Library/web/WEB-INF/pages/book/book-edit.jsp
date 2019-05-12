<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="static/plugins/bootstrap-validator/css/bootstrapValidator.css">
<link rel="stylesheet" href="static/plugins/webuploader/webuploader.css">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">图书信息</h4>
</div>
<div class="modal-body">
    <form class="form-horizontal" id="infoForm">
        <input type="hidden" name="id" value="${book.id}">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label"><span class="text-danger">*</span>书名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="name" id="name" placeholder="请输入图书名称" value="${book.name}">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label"><span class="text-danger">*</span>图书价格</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="price" id="price" placeholder="请输入图书价格" value="${book.price}">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">作者</label>
            <div class="col-sm-10">
                <select name="authorid" id="authorid" class="form-control">
                    <c:forEach var="a" items="${aList}">
                        <option value="${a.id}">${a.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">出版社</label>
            <div class="col-sm-10">
                <select name="pressid" id="pressid" class="form-control">
                    <c:forEach var="p" items="${pList}">
                        <option value="${p.id}">${p.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label"><span class="text-danger">*</span>封面</label>
            <div class="col-sm-10">
                <div id="filePicker">选择图片</div>
                <div id="imgDiv" style="height: 100px;width: 100px;border: 1px solid gray">
                    <button type="button" id="closeBtn" class="close" style="color: red;opacity: .9;position: absolute;left: 100px;display: none;">&times;</button>
                    <c:if test="${not empty book}">
                        <img src="static/upload/${book.img}" alt="${book.img}" style="width: 100px;height: 100px;">
                    </c:if>
                </div>
                <input type="hidden" class="form-control" name="img" id="img" value="${book.img}" >
            </div>

        </div>
        <div class="form-group">
            <label for="remake" class="col-sm-2 control-label">简介</label>
            <div class="col-sm-10">
                <textarea class="form-control" name="intro" id="intro" rows="3">${book.intro}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="remake" class="col-sm-2 control-label">备注</label>
            <div class="col-sm-10">
                <textarea class="form-control" name="remake" id="remake" rows="3">${book.remake}</textarea>
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
<script src="static/plugins/webuploader/webuploader.js"></script>
<script>
    $(function () {
        $('#filePicker input[type="file"]').parent().css({'height':'40px','width':'86px'});
       $('#infoForm').bootstrapValidator({
           excluded:[":disabled"],//设置只有disbled不验证，默认hidden也是不验证的
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
                           message:'请输入书名'
                       }
                   }
               },
               price:{
                   validators:{
                       notEmpty:{
                           message:'请输入图书价格'
                       },
                       regexp:{
                           regexp:/((^[1-9]\d*)|^0)(\.\d{0,2}){0,1}$/,
                           message:'请输入正确的价格'
                       }
                   }
               },
               img:{
                   validators:{
                       notEmpty:{
                           message:'请选择图书封面'
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
                    url:'book?type=doEdit',
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
        // 初始化Web Uploader
        var uploader = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: true,
            // 文件接收服务端。
            server: 'upload',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker',
            //允许重复上传
            duplicate :true,
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        uploader.on('fileQueued',function (file) {
            uploader.makeThumb( file, function( error, src ) {
                if ( error ) {
                    alert('预览出错');
                } else {
                    var $img=$('<img src="'+src+'" style="height: 100px;width: 100px;" />');
                    $('#imgDiv').find('img').remove();
                    $('#imgDiv').append($img);
                    $('#closeBtn').show();
                }
            });
        });
        uploader.on('uploadSuccess',function (file,res) {
            $('#img').val(res.fileName);
        });
        $('#closeBtn').click(function () {
           if(confirm('确定删除该图片吗？')){
               uploader.reset();
               $('#imgDiv').find('img').remove();
               $('#closeBtn').hide();
               $('#img').val('');
           }
        });
    });
</script>
