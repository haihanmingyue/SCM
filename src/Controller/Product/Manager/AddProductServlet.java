package Controller.Product.Manager;

import DBUtil.LinkUtil;
import Dao.Product.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/stock/addproduct")
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String proid = request.getParameter("proid");
        String proName = request.getParameter("proName");

        if(ProductDao.searchByProId(proid)>0){  //proid是否已存在
            response.getWriter().write("alreadyID");
        }else {
            if(ProductDao.searchByProName(proName)>0) {
                response.getWriter().write("alreadyName");
            }else {
                String price = request.getParameter("price");
                String unitName = request.getParameter("unitName");
                String category = request.getParameter("category");
                String date = request.getParameter("date");
                String remark = request.getParameter("remark");
                if(ProductDao.insertPro(proid,Integer.parseInt(category),proName,unitName,Float.parseFloat(price),date,remark)==1){
                    try {
                        LinkUtil.commit();
                        LinkUtil.close();
                        response.getWriter().write("succeed");
                    } catch (SQLException e) {
                        response.getWriter().write("false");
                        e.printStackTrace();
                    }
                }else {
                    response.getWriter().write("false");
                }
            }
        }
    }
}
