package Controller.Stock.StockServlet.chuku;

import Dao.So.SomainDao;
import Module.So.Somain;
import PageUtil.PageTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stock/chukuindex")
public class ChukuIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){

            PageTable<Somain> pr = new PageTable<>();
            pr.ChuKuCreate(1,17,1);
            pr.getP().setDataList(SomainDao.selectByType(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
            System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            request.setAttribute("total",pr.getP().getTotalRecord());
            request.setAttribute("totalPage",pr.getP().getTotalPage());
            request.setAttribute("list",pr.getP().getDataList());
            request.getRequestDispatcher("../stocker/stockout.jsp").forward(request,response);

        }
    }
}
