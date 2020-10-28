package Filter;

import Module.User.UserModel;
import PageUtil.Page;
import PageUtil.PageTable;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Filter1_LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Object o = request.getSession().getAttribute("user");
//        System.out.println(o==null);
        String path = request.getServletPath();
//        System.out.println(path);
        if( o!=null || path.indexOf("index.htm")>0 || path.indexOf("login")>0 || path.indexOf("title.jsp")>0 || path.indexOf("folders.js")>0 || path.indexOf("login.jsp")>0){
            chain.doFilter(req, resp);
        }else {
            response.getWriter().println("<a>您还未登录，请先登录</a>");
            response.setHeader("refresh", "2;url="+request.getContextPath()+"/index/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
