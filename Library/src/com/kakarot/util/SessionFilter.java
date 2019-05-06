package com.kakarot.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*转换request和response*/
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        /*获取过滤器配置信息*/
        String encoding=config.getInitParameter("encoding");
        String contentType=config.getInitParameter("contentType");
        /*处理中文乱码*/
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType(contentType);
        /*获取项目根路径*/
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath()
                + "/";
        /*根路径存入作用域*/
        request.setAttribute("basePath", basePath);
        /*放行请求*/
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        this.config=null;
    }
}
