package Controller.Product.Manager;

import Dao.Product.CategoryDao;
import Dao.Product.ProductDao;
import Module.Product.Category;
import Module.Product.Product;
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
 * 商品管理
 * */
@WebServlet("/stock/productPage")
public class ChangePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {
            int page = Integer.parseInt(request.getParameter("page"));
            int flag = Integer.parseInt(request.getParameter("flag"));
            Object o = request.getSession().getAttribute("key");
            PageTable<Product> pr = new PageTable<>();
            if (o == null) {

                pr.productManagerCreat(page, 12, null, null,null);

                pr.getP().setDataList(ProductDao.selectAllProduct(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                if (pr.getP().getDataList().size() == 0) {
                    pr.productManagerCreat(page - 1, 12, null,null, null);

                    pr.getP().setDataList(ProductDao.selectAllProduct(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                }
            } else {
                Object o1 = request.getSession().getAttribute("keyList");
                List<String> keyList = (List<String>) o1;
                pr.productManagerCreat(page , 12, keyList.get(0), keyList.get(1),keyList.get(2));
                pr.getP().setDataList(ProductDao.selectCDK(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),keyList.get(0), keyList.get(1),keyList.get(2)));
                if(pr.getP().getDataList().size()==0){
                    pr.productManagerCreat(page - 1, 12, keyList.get(0), keyList.get(1),keyList.get(2));
                    pr.getP().setDataList(ProductDao.selectCDK(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),keyList.get(0), keyList.get(1),keyList.get(2)));
                }
            }

            List<Category> classNoList = CategoryDao.selectAll();
            request.setAttribute("classno", classNoList);

                if (flag == 1) {
                    if (request.getAttribute("currentPage") != null) {
                        request.removeAttribute("currentPage");
                    }

                    List<Product> list = pr.getP().getDataList();
                    String jsonOut = JSON.toJSONString(list);
                    response.getWriter().write(jsonOut);

                } else {

                    request.setAttribute("currentPage", pr.getP().getCurrentPage() + 1);

                    request.setAttribute("total", pr.getP().getTotalRecord());
                    request.setAttribute("totalPage", pr.getP().getTotalPage());
                    request.setAttribute("list", pr.getP().getDataList());

                    request.getRequestDispatcher("../product/product.jsp").forward(request, response);
                }
            }
        }

}
