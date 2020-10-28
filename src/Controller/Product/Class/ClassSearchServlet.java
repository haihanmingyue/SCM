package Controller.Product.Class;

import Dao.Product.CategoryDao;
import Module.Product.Category;
import PageUtil.PageTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/stock/classSearch")
public class ClassSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

          String searchKey = request.getParameter("searchKey");
          System.out.println("传过来的key："+searchKey);
          String[] keys = searchKey.split(" ");
          String str,str2;
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
          PageTable<Category> pr = new PageTable<>();
          pr.pClassCreat(1,17,str,str2);
          pr.getP().setDataList(CategoryDao.keyClass(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),str,str2));
          List<Integer> classNoList = CategoryDao.allClassNo();
          request.setAttribute("total",pr.getP().getTotalRecord());
          request.setAttribute("totalPage",pr.getP().getTotalPage());
          request.setAttribute("list",pr.getP().getDataList());
          request.setAttribute("classNoList",classNoList);
          if((str+str2).equals("")){
              request.getSession().setAttribute("key",null);
          }else {
              request.getSession().setAttribute("key",str+" "+str2);
          }
          List<String> keyList = new ArrayList<>();
          keyList.add(str);keyList.add(str2);
          request.getSession().setAttribute("keyList",keyList);

          request.getRequestDispatcher("../product/productClass.jsp").forward(request,response);
    }
}
