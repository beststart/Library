package com.kakarot.servlet;

import com.kakarot.pojo.Author;
import com.kakarot.service.AuthorService;
import com.kakarot.service.impl.AuthorServiceImpl;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/author")
public class AuthorServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        PrintWriter out=response.getWriter();
        AuthorService authorService=new AuthorServiceImpl();
        Integer id=Integer.parseInt(StringUtils.isNullOrEmpty(request.getParameter("id"))?"0":request.getParameter("id"));
        Integer status=null;
        String sta=request.getParameter("status");
        if(!StringUtils.isNullOrEmpty(sta)){
            status=Integer.parseInt(sta);
        }
        String name=request.getParameter("name");
        String remake=request.getParameter("remake");
        if("toList".equals(type)){
            request.getRequestDispatcher("/WEB-INF/pages/author/author-list.jsp").forward(request,response);
        }
        if("getPage".equals(type)){
            int offset=Integer.parseInt(request.getParameter("offset"));
            int limit=Integer.parseInt(request.getParameter("limit"));
            Author author=new Author();
            author.setName(name);
            author.setStatus(status);
            out.print(authorService.getPage(offset,limit,author));
        }
        if("toEdit".equals(type)){
            if(id!=0){
                request.setAttribute("author",authorService.getInfoById(id));
            }
            request.getRequestDispatcher("/WEB-INF/pages/author/author-edit.jsp").forward(request,response);
        }
        if("doEdit".equals(type)){
            Author author=new Author();
            author.setId(id);
            author.setName(name);
            author.setRemake(remake);
            out.print(authorService.update(author));
        }
        if("updateStatus".equals(type)){
            out.print(authorService.updateStatus(id,status));
        }
        if("delete".equals(type)){
            out.print(authorService.delete(id));
        }
    }
}
