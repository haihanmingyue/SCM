package Controller.Stock.StockServlet.ruku;

import Dao.Po.PomainDao;
import Module.Po.Pomain;
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
@WebServlet("/stock/rukuselect")
public class RuKuIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){

            PageTable<Pomain> pr = new PageTable<>();
            pr.create(1,17,1);
            pr.getP().setDataList(PomainDao.selectByType(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
            System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            request.setAttribute("total",pr.getP().getTotalRecord());
            request.setAttribute("totalPage",pr.getP().getTotalPage());
            request.setAttribute("list",pr.getP().getDataList());
            request.getSession().setAttribute("key",null);

            request.getRequestDispatcher("../stocker/stockreg.jsp").forward(request,response);

        }
    }
}
