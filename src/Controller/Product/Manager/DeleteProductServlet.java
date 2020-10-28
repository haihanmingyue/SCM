package Controller.Product.Manager;

import DBUtil.LinkUtil;
import Dao.Product.CategoryDao;
import Dao.Product.ProductDao;
import Dao.Stock.StockrecordDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/stock/productdelete")
public class DeleteProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){
            String proid =  request.getParameter("proid");

            if(ProductDao.PONumAndSONum(proid)>0){
                response.getWriter().write("false");
            }else {

                int i = StockrecordDao.RecordNum(proid);
                System.out.println("123");
                System.out.println(i);
                if(i>0){
                    LinkUtil.openConnection();
                    int j = StockrecordDao.deleteRecord(proid);
                    int i2 = ProductDao.deleteProduct(proid);
                    if(j == i && i2 ==1){
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
                }else {
                    LinkUtil.openConnection();
                    if(ProductDao.deleteProduct(proid) == 1){
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
                }
            }
            System.out.println("关闭数据库连接");
            LinkUtil.close();
        }
    }
}
