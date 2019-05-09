package com.kakarot.servlet;

import com.kakarot.pojo.Press;
import com.kakarot.service.PressService;
import com.kakarot.service.impl.PressServiceImpl;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/press")
public class PressServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        PrintWriter out=response.getWriter();
        PressService pressService=new PressServiceImpl();
        Integer id=Integer.parseInt(StringUtils.isNullOrEmpty(request.getParameter("id"))?"0":request.getParameter("id"));
        Integer status=null;
        String sta=request.getParameter("status");
        if(!StringUtils.isNullOrEmpty(sta)){
            status=Integer.parseInt(sta);
        }
        String name=request.getParameter("name");
        String remake=request.getParameter("remake");
        String address=request.getParameter("address");
        String contact=request.getParameter("contact");
        String phone=request.getParameter("phone");
        if("toList".equals(type)){
            request.getRequestDispatcher("/WEB-INF/pages/press/press-list.jsp").forward(request,response);
        }
        if("getPage".equals(type)){
            int offset=Integer.parseInt(request.getParameter("offset"));
            int limit=Integer.parseInt(request.getParameter("limit"));
            Press press=new Press();
            press.setName(name);
            press.setStatus(status);
            out.print(pressService.getPage(offset,limit,press));
        }
        if("toEdit".equals(type)){
            if(id!=0){
                request.setAttribute("press",pressService.getInfoById(id));
            }
            request.getRequestDispatcher("/WEB-INF/pages/press/press-edit.jsp").forward(request,response);
        }
        if("doEdit".equals(type)){
            Press press=new Press();
            press.setId(id);
            press.setName(name);
            press.setRemake(remake);
            press.setAddress(address);
            press.setContact(contact);
            press.setPhone(phone);
            out.print(pressService.update(press));
        }
        if("updateStatus".equals(type)){
            out.print(pressService.updateStatus(id,status));
        }
        if("delete".equals(type)){
            out.print(pressService.delete(id));
        }
    }
}
