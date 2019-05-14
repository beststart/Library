package com.kakarot.servlet;

import com.kakarot.pojo.Record;
import com.kakarot.pojo.UserInfo;
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
        String bid=request.getParameter("bid");
        Integer bookid=null;
        if(!StringUtils.isNullOrEmpty(bid)){
            bookid=Integer.parseInt(bid);
        }
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute("lu");
        Integer userid=null;
        if(userInfo!=null){
            userid=userInfo.getId();
        }
        Record record=new Record();
        record.setUserid(userid);
        record.setBookid(bookid);
        if(StringUtils.isNullOrEmpty(type)){
            request.setAttribute("hotList",viewService.getInfoByHot());
            request.getRequestDispatcher("/WEB-INF/pages/views/hot.jsp").forward(request,response);
        }
        if("borrow".equals(type)){
            response.getWriter().print(viewService.bBook(record));
        }
    }
}
