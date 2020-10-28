package Controller.BaoBiao.kucunbb;

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
 * 翻页
 * */
@WebServlet("/stock/kcbbPage")
public class ChangePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){

            int page = Integer.parseInt(request.getParameter("page"));

            PageTable<Product> pr = new PageTable<>();
            pr.KCBBCreate(page,17,"");
            pr.getP().setDataList(ProductDao.selectAllProduct(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
            System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            request.setAttribute("total",pr.getP().getTotalRecord());
            request.setAttribute("totalPage",pr.getP().getTotalPage());

            List<Product> list = pr.getP().getDataList();
            String jsonOut = JSON.toJSONString(list);
            if(list.size()>0){ //数据不为空
                String js = jsonOut.replaceAll("}]","");
                js = js+","+"\"totalNum\""+":"+pr.getP().getTotalRecord()+","+"\"allPage\""+":"+pr.getP().getTotalPage()+"}]";
                response.getWriter().write(js);
            }else { //数据为空时 respone以下的json格式数据
                String js = jsonOut.replaceAll("]", "{");
                js = js + "\"f\":\"无信息\"," + "\"a\":\"无信息\"," + "\"c\":\"无信息\"," + "\"h\":\"无信息\"," + "\"totalNum\"" + ":" + 0 + "," + "\"allPage\"" + ":" + 0 + "}]";
                response.getWriter().write(js);
            }

        }
    }
}
