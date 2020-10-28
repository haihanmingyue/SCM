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


/**
 * 库存变更查询翻页
 * */
@WebServlet("/stock/recordpage")
public class RecordPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){

            int page = Integer.parseInt(request.getParameter("page"));
            String flag = request.getParameter("flag");
            String productCode = request.getParameter("productCode");
            String date = request.getParameter("date");
            String type = request.getParameter("type");
            String key = request.getParameter("key");

            System.out.println("page:"+page);
            PageTable<Stockrecord> pr = new PageTable<>();

            if((date+type+key).equals("")){
                pr.RecordCreat(page,8,productCode,null,null,null);
                pr.getP().setDataList(StockrecordDao.Record(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),productCode));
            }else {
                pr.RecordCreat(page,8,productCode,key,date,type);
                pr.getP().setDataList(StockrecordDao.RecordDKT(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),productCode,date,key,type));
            }

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
