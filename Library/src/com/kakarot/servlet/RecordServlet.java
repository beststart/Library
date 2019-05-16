package com.kakarot.servlet;

import com.kakarot.pojo.Record;
import com.kakarot.pojo.UserInfo;
import com.kakarot.service.RecordService;
import com.kakarot.service.impl.RecordServiceImpl;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/record")
public class RecordServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        RecordService recordService=new RecordServiceImpl();
        Record record=new Record();
        Integer userid=null;
        String uid=request.getParameter("userid");
        if(!StringUtils.isNullOrEmpty(uid)){
            userid=Integer.parseInt(uid);
        }
        Integer bookid=null;
        String bid=request.getParameter("bookid");
        if(!StringUtils.isNullOrEmpty(bid)){
            bookid=Integer.parseInt(bid);
        }
        Integer id=null;
        String rid=request.getParameter("id");
        if(!StringUtils.isNullOrEmpty(rid)){
            id=Integer.parseInt(rid);
        }
        String borrowTime=request.getParameter("borrowTime");
        String returnTime=request.getParameter("returnTime");
        record.setBookid(bookid);
        record.setUserid(userid);
        record.setBorrowTime(borrowTime);
        record.setReturnTime(returnTime);
        record.setId(id);
        Object obj=request.getSession().getAttribute("lu");
        if(obj!=null){
            UserInfo userInfo= (UserInfo) obj;
            if(userInfo.getPowerid()!=1){
                record.setUserid(userInfo.getId());
            }
        }
        if("toList".equals(type)){
            request.getRequestDispatcher("/WEB-INF/pages/record/record-list.jsp").forward(request,response);
        }
        if("getPage".equals(type)){
            int offset=Integer.parseInt(request.getParameter("offset"));
            int pageSize=Integer.parseInt(request.getParameter("limit"));
            response.getWriter().print(recordService.getPage(offset,pageSize,record));
        }
        if("rBook".equals(type)){
            response.getWriter().print(recordService.returnBook(record));
        }
    }
}
