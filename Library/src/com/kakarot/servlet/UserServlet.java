package com.kakarot.servlet;

import com.kakarot.pojo.Power;
import com.kakarot.pojo.UserInfo;
import com.kakarot.service.PowerService;
import com.kakarot.service.UserService;
import com.kakarot.service.impl.PowerServiceImpl;
import com.kakarot.service.impl.UserServiceImpl;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserServiceImpl();
        PowerService powerService=new PowerServiceImpl();
        String type=request.getParameter("type");
        if("toList".equals(type)){
            List<Power> pList=powerService.getAll();
            request.setAttribute("pList",pList);
            request.getRequestDispatcher("/WEB-INF/pages/user/user-list.jsp").forward(request,response);
        }
        if("getPage".equals(type)){
            String realname=request.getParameter("realname");
            String phone=request.getParameter("phone");
            Integer powerid=null;
            String pid=request.getParameter("powerid");
            if(!StringUtils.isNullOrEmpty(pid)){
                powerid=Integer.parseInt(pid);
            }
            UserInfo userInfo=new UserInfo();
            userInfo.setRealname(realname);
            userInfo.setPhone(phone);
            userInfo.setPowerid(powerid);
            Integer offset=Integer.parseInt(request.getParameter("offset"));
            Integer limit=Integer.parseInt(request.getParameter("limit"));
            Object obj=userService.getPage(offset,limit,userInfo);
            response.getWriter().print(obj);
        }
        if("updateStatus".equals(type)){
            Integer id=Integer.parseInt(StringUtils.isNullOrEmpty(request.getParameter("id"))?"0":request.getParameter("id"));
            Integer status=Integer.parseInt(StringUtils.isNullOrEmpty(request.getParameter("status"))?"0":request.getParameter("status"));
            response.getWriter().print(userService.updateStatus(id,status));
        }
        if("delete".equals(type)){
            Integer id=Integer.parseInt(StringUtils.isNullOrEmpty(request.getParameter("id"))?"0":request.getParameter("id"));
            response.getWriter().print(userService.delete(id));
        }
        if("toEdit".equals(type)){
            Integer id=Integer.parseInt(StringUtils.isNullOrEmpty(request.getParameter("id"))?"0":request.getParameter("id"));
            if(id!=0){
                UserInfo userInfo=userService.getInfoById(id);
                request.setAttribute("user",userInfo);
            }
            request.getRequestDispatcher("/WEB-INF/pages/user/user-edit.jsp").forward(request,response);
        }
        if("checkUserName".equals(type)){
            response.getWriter().print(userService.checkUserName(request.getParameter("username")));
        }
        if("doEdit".equals(type)){
            Integer id=Integer.parseInt(StringUtils.isNullOrEmpty(request.getParameter("id"))?"0":request.getParameter("id"));
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            String realname=request.getParameter("realname");
            String phone=request.getParameter("phone");
            Integer age=null;
            String sage=request.getParameter("age");
            if(!StringUtils.isNullOrEmpty(sage)){
                age=Integer.parseInt(sage);
            }
            UserInfo userInfo=new UserInfo();
            userInfo.setId(id);
            userInfo.setUsername(username);
            userInfo.setPassword(password);
            userInfo.setRealname(realname);
            userInfo.setAge(age);
            userInfo.setPhone(phone);
            response.getWriter().print(userService.update(userInfo));
        }
        if("toPwd".equals(type)){
            Integer id=Integer.parseInt(StringUtils.isNullOrEmpty(request.getParameter("id"))?"0":request.getParameter("id"));
            if(id!=0){
                UserInfo userInfo=userService.getInfoById(id);
                request.setAttribute("user",userInfo);
            }
            request.getRequestDispatcher("/WEB-INF/pages/user/user-pwd.jsp").forward(request,response);
        }
        if("checkPwd".equals(type)){
            Integer id=Integer.parseInt(StringUtils.isNullOrEmpty(request.getParameter("id"))?"0":request.getParameter("id"));
            String password=request.getParameter("password");
            response.getWriter().print(userService.checkPwd(id,password));
        }
        if("changePwd".equals(type)){
            Integer id=Integer.parseInt(StringUtils.isNullOrEmpty(request.getParameter("id"))?"0":request.getParameter("id"));
            String password=request.getParameter("password");
            response.getWriter().print(userService.updatePwd(id,password));
        }
    }
}
