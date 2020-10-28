package Controller.Stock.StockServlet.kucun;

import Dao.Product.ProductDao;
import Module.Product.Product;
import PageUtil.PageTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取可以入库的采购单
 * */
@WebServlet("/stock/kucunindex")
public class KuCunIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){

            PageTable<Product> pr = new PageTable<>();
            pr.kuCunCreat(1,17,"",0,null);
            pr.getP().setDataList(ProductDao.searchKeySNum(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),"",0));
            System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            request.setAttribute("total",pr.getP().getTotalRecord());
            request.setAttribute("totalPage",pr.getP().getTotalPage());
            request.setAttribute("list",pr.getP().getDataList());
            request.getSession().setAttribute("key",null);

            request.getRequestDispatcher("../stocker/productstock.jsp").forward(request,response);

        }
    }
}
