package com.kakarot.util;

import com.alibaba.fastjson.JSON;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/upload")
public class UploadImg extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //声明上传组件实例
        SmartUpload su=new SmartUpload();
        //初始化上传组件
        su.initialize(this.getServletConfig(),req,resp);
        try {
            //获取上传内容
            su.upload();
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
        //获取上传文件列表
        Files fs=su.getFiles();
        File f=fs.getFile(0);
        System.out.println("参数名："+f.getFieldName());
        System.out.println("文件名："+f.getFileName());
        System.out.println("文件后缀名："+f.getFileExt());
        String path = req.getSession().getServletContext().getRealPath("/")+"static/upload";
        System.out.println(path);
        java.io.File filePath=new java.io.File(path);
        //判断要保存的文件路径是否存在，不存在则创建
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        String fileName=String.valueOf((new Date()).getTime())+"."+f.getFileExt();
        String fullName=filePath+"/"+fileName;
        System.out.println(fullName);
        try {
            f.saveAs(fullName,f.SAVEAS_AUTO);
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("fileName",fileName);
        resp.getWriter().print(JSON.toJSON(map));
    }
}
