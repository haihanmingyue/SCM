package Controller.Product.Class;

import DBUtil.LinkUtil;
import Dao.Product.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/stock/addclass")
public class AddClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {
            String classname = request.getParameter("name");
            String msg = request.getParameter("msg");
//            String page = request.getParameter("page");
            if(CategoryDao.allNameCount(classname)>0){
                response.getWriter().write("already");
            }else {
                int rs = CategoryDao.addClass(classname,msg);
                if(rs == 1 ){
                    try {
                        LinkUtil.commit();
                        response.getWriter().write("succeed");

//                        response.getWriter().write("<script>alert('添加成功!');location.href='../product/classCP?page="+page+"&flag=2';</script>");
                    } catch (SQLException e) {
                        LinkUtil.rollBack();
                        response.getWriter().write("false");
//                        response.getWriter().write("<script>alert('添加失败,可能已经存在此分类，请刷新页面!');location.href='../product/classCP?page="+page+"&flag=2';</script>");
                        e.printStackTrace();
                    }finally {
                        LinkUtil.close();
                    }
                }else {
                    response.getWriter().write("false");
//                    response.getWriter().write("<script>alert('添加失败,可能已经存在此分类，请刷新页面!');location.href='../product/classCP?page="+page+"&flag=2';</script>");
                }
            }

        }

    }
}
