package Controller.Product.Class;

import Dao.Product.CategoryDao;
import Module.Product.Category;
import PageUtil.PageTable;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 商品分类
 * */
@WebServlet("/stock/classCP")
public class ChangePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {
            int page = Integer.parseInt(request.getParameter("page"));
            int flag = Integer.parseInt(request.getParameter("flag"));
            Object o = request.getSession().getAttribute("key");
            PageTable<Category> pr = new PageTable<>();
            if (o == null) {
                System.out.println("执行第一条");
                pr.pClassCreat(page, 17, null, null);
                pr.getP().setDataList(CategoryDao.allClass(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                if(pr.getP().getDataList().size()==0){
                    pr.pClassCreat(page-1,17 , null, null);
                    pr.getP().setDataList(CategoryDao.allClass(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                }
            } else {
                System.out.println("执行第二条");
                Object o1 = request.getSession().getAttribute("keyList");
                List<String> keyList = (List<String>) o1;
                System.out.println("打印list："+keyList.get(0) + keyList.get(1));
                pr.pClassCreat(page, 17, keyList.get(0), keyList.get(1));
                pr.getP().setDataList(CategoryDao.keyClass(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(), keyList.get(0), keyList.get(1)));
                if(pr.getP().getDataList().size()==0){
                    pr.pClassCreat(page-1, 17, keyList.get(0), keyList.get(1));
                    pr.getP().setDataList(CategoryDao.keyClass(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(), keyList.get(0), keyList.get(1)));
                }
            }
            if (flag == 1) {
                if(request.getAttribute("currentPage") != null){
                    request.removeAttribute("currentPage");
                }
                List<Category> list = pr.getP().getDataList();
                String jsonOut = JSON.toJSONString(list);
                response.getWriter().write(jsonOut);
            }else {
                List<Integer> classNoList = CategoryDao.allClassNo();
                request.setAttribute("currentPage",pr.getP().getCurrentPage()+1);
                request.setAttribute("total",pr.getP().getTotalRecord());
                request.setAttribute("totalPage",pr.getP().getTotalPage());
                request.setAttribute("list",pr.getP().getDataList());
                request.setAttribute("classNoList",classNoList);
                request.getRequestDispatcher("../product/productClass.jsp").forward(request,response);
            }
        }
    }
}
