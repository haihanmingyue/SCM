package Controller.BaoBiao.kucunbb;

import Dao.Product.ProductDao;
import Dao.Stock.StockrecordDao;
import Module.Product.Product;
import Module.Stock.Stockrecord;
import PageUtil.PageTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stock/kcindex")
public class KuCunBaoBiaoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {

            PageTable<Product> pr = new PageTable<>();
            pr.KCBBCreate(1,17,"");
            pr.getP().setDataList(ProductDao.selectAllProduct(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
            System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            request.setAttribute("total",pr.getP().getTotalRecord());
            request.setAttribute("totalPage",pr.getP().getTotalPage());
            request.setAttribute("list",pr.getP().getDataList());

            request.getRequestDispatcher("../baobiao/kucunbaobiao.jsp").forward(request, response);


        }
    }
}
