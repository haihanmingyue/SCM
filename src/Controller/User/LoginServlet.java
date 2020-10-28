package Controller.User;

import Dao.Us.UserModelDao;
import Module.User.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(username);
            System.out.println(password);
            System.out.println("xxxx: "+UserModelDao.login(username,password));
            if(UserModelDao.login(username,password)){
                UserModel u = UserModelDao.message(username);
                if(u.getStatus()==1){
                    response.getWriter().write("<script>alert('你的账号已被锁定!');location.href='"+request.getContextPath()+"/index/login.jsp';</script>");
                }else {
                    response.getWriter().write("<script>alert('登录成功!');</script>");
                    response.getWriter().write("<a>欢迎使用SCM</a>");
                    response.getWriter().write("<script>window.open('/SCM/index/title.jsp','topFrame');</script>");
                    request.getSession().setAttribute("user",u);
                }
            }else {
                response.getWriter().write("<script>alert('用户名或密码错误!');location.href='"+request.getContextPath()+"/index/login.jsp';</script>");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
