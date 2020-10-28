package Controller.Product.Manager;

import DBUtil.LinkUtil;
import Dao.Product.ProductDao;
import Module.Product.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/stock/proupdate")
public class UpdateClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {
//           "proid":proid,"proName":proName,"price":price,"unitName":unitName,"remark":remark,"category":category
            String proid = request.getParameter("proid");
            System.out.println("proid:"+proid);
            String category = request.getParameter("category").trim();
            System.out.println("category:"+category);
            String proName = request.getParameter("proName").trim();
            System.out.println("proName:"+proName);
            String unitName = request.getParameter("unitName");
            System.out.println("unitName:"+unitName);
            String price = request.getParameter("price");
            System.out.println("price:"+price);
            String remark = request.getParameter("remark");
            System.out.println("ome");

            Product p = ProductDao.ByProId(proid);
            if (p != null) {  //判断Product是否存在
                System.out.println("判断");
                if(ProductDao.PONumAndSONum(proid)>0){ //如果该产品还存在 在售数量/在购数量
                    System.out.println("是否存在还在销售或是采购");
                    System.out.println(p.getCategoryID()+":"+p.getUnitName()+":"+p.getName());
                    if(p.getCategoryID() != Integer.parseInt(category) || !p.getUnitName().equals(unitName) || !p.getName().equals(proName)){  //比较 categoryid  或者 名字 或者 看、数量单位 是否有被修改，没有则其他信息可以修改，否则无法修改
                       System.out.println("比较");
                        response.getWriter().write("already");
                    }else {
                        if(ProductDao.updateProduct(proid,proName,Float.parseFloat(price),unitName,remark,Integer.parseInt(category))==1){
                            try {
                                LinkUtil.commit();
                                LinkUtil.close();
                                response.getWriter().write("succeed");
                            } catch (SQLException e) {
                                LinkUtil.rollBack();
                                response.getWriter().write("false");
                                e.printStackTrace();
                            }
                        }else {
                            response.getWriter().write("false");
                        }
                    }
                }else {
                    if (ProductDao.updateProduct(proid, proName, Float.parseFloat(price), unitName, remark, Integer.parseInt(category)) == 1) {
                        try {
                            LinkUtil.commit();
                            LinkUtil.close();
                            response.getWriter().write("succeed");
                        } catch (SQLException e) {
                            LinkUtil.rollBack();
                            response.getWriter().write("false");
                            e.printStackTrace();
                        }
                    } else {
                        response.getWriter().write("false");
                    }
                }
            }else { //不存在return no
                response.getWriter().write("no");
            }
        }
    }
}
