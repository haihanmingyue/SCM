package Controller.BaoBiao.rukubb;

import Dao.Stock.StockrecordDao;
import Module.Stock.Stockrecord;
import PageUtil.PageTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stock/rkindex")
public class RuKuBaoBiaoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {

            PageTable<Stockrecord> pr = new PageTable<>();
            pr.RKBBCreat(1,17,"");
            pr.getP().setDataList(StockrecordDao.allRk(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),""));
            System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            request.setAttribute("total",pr.getP().getTotalRecord());
            request.setAttribute("totalPage",pr.getP().getTotalPage());
            request.setAttribute("list",pr.getP().getDataList());

            request.getRequestDispatcher("../baobiao/rukubaobiao.jsp").forward(request, response);


        }
    }
}
