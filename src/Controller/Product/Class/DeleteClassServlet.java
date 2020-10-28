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

@WebServlet("/stock/deleteclass")
public class DeleteClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){
            String categoryid =  request.getParameter("categoryid");
            if(ProductDao.selectProduct(Integer.parseInt(categoryid))>0){
                response.getWriter().write("false");
            }else {
                if(CategoryDao.deleteClass(Integer.parseInt(categoryid)) == 1){
                    try {
                        LinkUtil.commit();
                        response.getWriter().write("succeed");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        LinkUtil.rollBack();
                        response.getWriter().write("other");
                    }
                }else {
                    response.getWriter().write("other");
                }
                LinkUtil.close();
            }
        }
    }
}
