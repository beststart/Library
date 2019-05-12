package com.kakarot.servlet;

import com.kakarot.pojo.Book;
import com.kakarot.service.AuthorService;
import com.kakarot.service.BookService;
import com.kakarot.service.PressService;
import com.kakarot.service.impl.AuthorServiceImpl;
import com.kakarot.service.impl.BookServiceImpl;
import com.kakarot.service.impl.PressServiceImpl;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ref.ReferenceQueue;
import java.time.format.ResolverStyle;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        PrintWriter out=response.getWriter();
        BookService bookService=new BookServiceImpl();
        AuthorService authorService=new AuthorServiceImpl();
        PressService pressService=new PressServiceImpl();
        Book book=new Book();
        Integer id=null;
        String sid=request.getParameter("id");
        if(!StringUtils.isNullOrEmpty(sid)){
            id=Integer.parseInt(sid);
        }
        Integer authorid=null;
        String aid=request.getParameter("authorid");
        if(!StringUtils.isNullOrEmpty(aid)){
            authorid=Integer.parseInt(aid);
        }
        Integer pressid=null;
        String pid=request.getParameter("pressid");
        if(!StringUtils.isNullOrEmpty(pid)){
            pressid=Integer.parseInt(pid);
        }
        Double price=0.0;
        String sprice=request.getParameter("price");
        if(!StringUtils.isNullOrEmpty(sprice)){
            price=Double.parseDouble(sprice);
        }
        Integer status=null;
        String ss=request.getParameter("status");
        if(!StringUtils.isNullOrEmpty(ss)){
            status=Integer.parseInt(ss);
        }
        Integer count=null;
        String sc=request.getParameter("count");
        if(!StringUtils.isNullOrEmpty(sc)){
            count=Integer.parseInt(sc);
        }
        String name=request.getParameter("name");
        String img=request.getParameter("img");
        String intro=request.getParameter("intro");
        String remake=request.getParameter("remake");
        book.setAuthorid(authorid);
        book.setId(id);
        book.setCount(count);
        book.setImg(img);
        book.setIntro(intro);
        book.setName(name);
        book.setPressid(pressid);
        book.setPrice(price);
        book.setStatus(status);
        book.setRemake(remake);

        if("toList".equals(type)){
            request.setAttribute("aList",authorService.getAll());
            request.setAttribute("pList",pressService.getAll());
            request.getRequestDispatcher("/WEB-INF/pages/book/book-list.jsp").forward(request,response);
        }
        if("getPage".equals(type)){
            int offset=Integer.parseInt(request.getParameter("offset"));
            int limit=Integer.parseInt(request.getParameter("limit"));
            out.print(bookService.getPage(offset,limit,book));
        }
        if("toEdit".equals(type)){
            if (id != null) {
                request.setAttribute("book",bookService.getInfoById(id));
            }
            request.setAttribute("aList",authorService.getAll());
            request.setAttribute("pList",pressService.getAll());
            request.getRequestDispatcher("/WEB-INF/pages/book/book-edit.jsp").forward(request,response);
        }
        if("doEdit".equals(type)){
            response.getWriter().print(bookService.update(book));
        }
        if("updateStatus".equals(type)){
            response.getWriter().print(bookService.updateStatus(id,status));
        }
        if("delete".equals(type)){
            response.getWriter().print(bookService.delete(id));
        }
    }
}
