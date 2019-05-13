package com.kakarot.servlet;

import com.kakarot.service.ViewService;
import com.kakarot.service.impl.ViewServiceImpl;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class ViewServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewService viewService=new ViewServiceImpl();
        String type=request.getParameter("type");
        if(StringUtils.isNullOrEmpty(type)){
            request.setAttribute("hotList",viewService.getInfoByHot());
            request.getRequestDispatcher("/WEB-INF/pages/views/hot.jsp").forward(request,response);
        }
    }
}
