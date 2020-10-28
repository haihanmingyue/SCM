package Controller.Product.Class;

import DBUtil.LinkUtil;
import Dao.Product.CategoryDao;
import Dao.Product.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/stock/updateclass")
public class UpdateClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){
            String classname = request.getParameter("name");
            String msg = request.getParameter("msg");
            System.out.println(classname);

            String cid = request.getParameter("cid");
            if(ProductDao.selectProduct(Integer.parseInt(cid))>0 && !CategoryDao.className(Integer.parseInt(cid)).equals(classname)) {
                response.getWriter().write("already");
            }else if(ProductDao.selectProduct(Integer.parseInt(cid))>0 && CategoryDao.className(Integer.parseInt(cid)).equals(classname)){
                if(CategoryDao.updatemsg(Integer.parseInt(cid),msg) == 1){
                    try {
                        LinkUtil.commit();
                        response.getWriter().write("succeed");
                        System.out.println("succeed");
                    } catch (SQLException e) {
                        LinkUtil.rollBack();
                        response.getWriter().write("false");
                        e.printStackTrace();
                    }finally {
                        LinkUtil.close();
                    }
                }else {
                    response.getWriter().write("false");
                }
            }
            else {
                int rs = CategoryDao.update(Integer.parseInt(cid),classname,msg);
                if(rs == 1 ){
                    try {
                        LinkUtil.commit();
                        response.getWriter().write("succeed");
                    } catch (SQLException e) {
                        LinkUtil.rollBack();
                        response.getWriter().write("false");
                        e.printStackTrace();
                    }finally {
                        LinkUtil.close();
                    }
                }else {
                    response.getWriter().write("false");
                }
            }
        }
    }
}
