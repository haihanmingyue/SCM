package Controller.Stock.StockServlet.kucun;

import Dao.Product.ProductDao;
import Dao.Stock.StockrecordDao;
import Module.Product.Product;
import Module.Stock.Stockrecord;
import PageUtil.PageTable;
import com.alibaba.fastjson.JSON;

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
@WebServlet("/stock/recordsearch")
public class RecordSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){

            String productCode = request.getParameter("productCode");
            System.out.println(productCode);
            String date = request.getParameter("date");
            String type = request.getParameter("type");
            String key = request.getParameter("key");

            PageTable<Stockrecord> pr = new PageTable<>();
            pr.RecordCreat(1,8,productCode,key,date,type);
            pr.getP().setDataList(StockrecordDao.RecordDKT(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),productCode,date,key,type));

            List<Stockrecord> list = pr.getP().getDataList();
            String jsonOut = JSON.toJSONString(list);

            if(list.size()>0){ //数据不为空
                String js = jsonOut.replaceAll("}]","");
                js = js+","+"\"totalNum\""+":"+pr.getP().getTotalRecord()+","+"\"allPage\""+":"+pr.getP().getTotalPage()+"}]";

                response.getWriter().write(js);

            }else { //数据为空时 respone以下的json格式数据
                String js = jsonOut.replaceAll("]", "{");
                js = js +"\"f\":\"无信息\","+"\"c\":\"无信息\","+"\"g\":\"无信息\","+"\"d\":\"无信息\","+"\"e\":\"无信息\","+"\"totalNum\""+":"+0+","+"\"allPage\""+":"+0+ "}]";

                response.getWriter().write(js);

            }

        }
    }
}
