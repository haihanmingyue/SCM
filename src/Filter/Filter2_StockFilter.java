package Filter;

import Module.User.UserModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Filter2_StockFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        UserModel u = (UserModel) request.getSession().getAttribute("user");
        System.out.println(u.getAccount());
        System.out.println(u.getName());
        System.out.println(u.getModelUri());
        boolean flag = u.getModelUri().equals("/stock/*");
        boolean flag2 = u.getModelUri().endsWith("/all");
        System.out.println(flag);

            if((flag || flag2)){
                chain.doFilter(req, resp);
            }else {
                response.getWriter().println("<a>权限不符</a>");
            }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
