package Controller.Product.Manager;

import Dao.Product.CategoryDao;
import Dao.Product.ProductDao;
import Module.Product.Category;
import Module.Product.Product;
import PageUtil.PageTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/stock/productSearch")
public class ProductSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

          String searchKey = request.getParameter("searchKey");
          System.out.println("传过来的key："+searchKey);
          String[] keys = searchKey.split(" ");
          String str,str2,str3;
          if(keys[0].equals("null")){
             str = "";
          }else {
             str = keys[0];
          }
           if(keys[1].equals("null")){
               str2 = "";
           }else {
               str2 = keys[1];
           }
           if(keys[2].equals("null")){
               str3 = "";
           }else {
               str3 = keys[2];
           }
          PageTable<Product> pr = new PageTable<>();
          pr.productManagerCreat(1,12,str,str2,str3);
          pr.getP().setDataList(ProductDao.selectCDK(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),str,str2,str3));
          List<Category> classNoList = CategoryDao.selectAll();
          request.setAttribute("total",pr.getP().getTotalRecord());
          request.setAttribute("totalPage",pr.getP().getTotalPage());
          request.setAttribute("list",pr.getP().getDataList());
          request.setAttribute("classno", classNoList);
          if((str+str2+str3).equals("")){
              request.getSession().setAttribute("key",null);
          }else {
              request.getSession().setAttribute("key",str+" "+str2+" "+str3);
          }
          List<String> keyList = new ArrayList<>();
          keyList.add(str);keyList.add(str2);keyList.add(str3);
          request.getSession().setAttribute("keyList",keyList);

          request.getRequestDispatcher("../product/product.jsp").forward(request,response);
    }
}
