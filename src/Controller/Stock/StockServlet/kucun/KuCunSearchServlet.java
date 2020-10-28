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
import java.util.ArrayList;
import java.util.List;


/**
 * 入库页面 按key 查询
 * */
@WebServlet("/stock/kucunsearch")
public class KuCunSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){

            String key = request.getParameter("searchkey");
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            PageTable<Product> pr = new PageTable<>();//每页 20 个数据

            System.out.println("key:"+key);
            System.out.println("start:"+start);
            System.out.println("end:"+end);

            if(end.equals("")){
                pr.kuCunCreat(1,17,key,Integer.parseInt(start.trim()),null);
                pr.getP().setDataList(ProductDao.searchKeySNum(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),key,Integer.parseInt(start.trim())));
            }else {
                pr.kuCunCreat(1,17,key,Integer.parseInt(start.trim()),end);
                pr.getP().setDataList(ProductDao.searchKeySENum(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),key,Integer.parseInt(start.trim()),Integer.parseInt(end)));
            }

            List<String> keyList = new ArrayList<>();
            keyList.add(key);keyList.add(start);keyList.add(end);
            request.setAttribute("total",pr.getP().getTotalRecord());
            request.setAttribute("totalPage",pr.getP().getTotalPage());
            request.setAttribute("list",pr.getP().getDataList());
            if(( key.equals("") && start.equals("0") && end.equals("") )){
                request.getSession().setAttribute("key",null);
            }else {
                request.getSession().setAttribute("key",key+" "+start+" "+end);
            }
            request.getSession().setAttribute("keyList",keyList);

            request.getRequestDispatcher("../stocker/productstock.jsp").forward(request,response);

        }
    }
}
