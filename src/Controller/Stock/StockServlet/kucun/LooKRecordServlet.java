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
import java.util.List;

@WebServlet("/stock/rukurecord")
public class LooKRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){

            String productCode = request.getParameter("productCode");
            System.out.println(productCode);

            PageTable<Stockrecord> pr = new PageTable<>();
            pr.RecordCreat(1, 8, productCode,null,null,null);
            pr.getP().setDataList(StockrecordDao.Record(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(), productCode));


            System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
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
