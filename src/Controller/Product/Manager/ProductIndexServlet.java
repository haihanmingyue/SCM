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
import java.util.List;

@WebServlet("/stock/productIndex")
public class ProductIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {
            PageTable<Product> pr = new PageTable<>();
            pr.productManagerCreat(1, 12, null, null,null);
            pr.getP().setDataList(ProductDao.selectAllProduct(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));

            List<Category> classNoList = CategoryDao.selectAll();
            request.setAttribute("total", pr.getP().getTotalRecord());
            request.setAttribute("totalPage",pr.getP().getTotalPage());
            request.setAttribute("list",pr.getP().getDataList());
            request.setAttribute("classno", classNoList);
            request.getSession().setAttribute("key",null);
            request.getRequestDispatcher("../product/product.jsp").forward(request, response);
        }

    }
}
