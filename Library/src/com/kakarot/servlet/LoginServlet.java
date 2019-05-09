package com.kakarot.servlet;

import com.alibaba.fastjson.JSON;
import com.kakarot.pojo.UserInfo;
import com.kakarot.service.UserService;
import com.kakarot.service.impl.UserServiceImpl;
import com.kakarot.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService=new UserServiceImpl();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        Map<String,Object> map=new HashMap<>();
        String type=request.getParameter("type");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if("toLogin".equals(type)){
            session.removeAttribute("lu");
            request.getRequestDispatcher("/WEB-INF/pages/login/login.jsp").forward(request,response);
        }
        if("doLogin".equals(type)){
            UserInfo userInfo=userService.doLogin(username,password);
            if(userInfo!=null){
                session.setAttribute("lu",userInfo);
                map.put("code",Constant.SUCCESS);
                map.put("msg","登录成功！");
                out.print(JSON.toJSON(map));
            }else{
                map.put("code",Constant.ERROR);
                map.put("msg","用户名或密码不正确！");
                out.print(JSON.toJSON(map));
            }
        }
        if("toIndex".equals(type)){
            request.getRequestDispatcher("/WEB-INF/pages/index/index.jsp").forward(request,response);
        }
    }
}
