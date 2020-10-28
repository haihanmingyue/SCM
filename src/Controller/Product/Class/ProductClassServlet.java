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
import java.util.List;

@WebServlet("/stock/pClass")
public class ProductClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {
            PageTable<Category> pr = new PageTable<>();
            pr.pClassCreat(1, 17, null, null);
            pr.getP().setDataList(CategoryDao.allClass(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
            System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            List<Integer> classNoList = CategoryDao.allClassNo();
            request.setAttribute("total", pr.getP().getTotalRecord());
            request.setAttribute("totalPage",pr.getP().getTotalPage());
            request.setAttribute("list",pr.getP().getDataList());
            request.setAttribute("classNoList", classNoList);
            request.getSession().setAttribute("key",null);
            request.getRequestDispatcher("../product/productClass.jsp").forward(request, response);

        }
    }
}
