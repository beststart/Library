package com.kakarot.servlet;

import com.kakarot.pojo.Book;
import com.kakarot.service.BookService;
import com.kakarot.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        PrintWriter out=response.getWriter();
        BookService bookService=new BookServiceImpl();
        if("toList".equals(type)){
            request.getRequestDispatcher("/WEB-INF/pages/book/book-list.jsp").forward(request,response);
        }
        if("getPage".equals(type)){
            int offset=Integer.parseInt(request.getParameter("offset"));
            int limit=Integer.parseInt(request.getParameter("limit"));
            out.print(bookService.getPage(offset,limit,new Book()));
        }
    }
}
