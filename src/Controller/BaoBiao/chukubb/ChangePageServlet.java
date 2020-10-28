package Controller.BaoBiao.chukubb;

import Dao.Stock.StockrecordDao;
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
@WebServlet("/stock/ckbbPage")
public class ChangePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){
            String date = request.getParameter("yearMonth");
            System.out.println(date);
            int page = Integer.parseInt(request.getParameter("page"));
            PageTable<Stockrecord> pr = new PageTable<>();
            pr.CKBBCreat(page,17,date);
            pr.getP().setDataList(StockrecordDao.allCK(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),date));
            System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            request.setAttribute("total",pr.getP().getTotalRecord());
            request.setAttribute("totalPage",pr.getP().getTotalPage());
            request.setAttribute("list",pr.getP().getDataList());

            System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            List<Stockrecord> list = pr.getP().getDataList();
            String jsonOut = JSON.toJSONString(list);
            if(list.size()>0){ //数据不为空
                String js = jsonOut.replaceAll("}]","");
                js = js+","+"\"totalNum\""+":"+pr.getP().getTotalRecord()+","+"\"allPage\""+":"+pr.getP().getTotalPage()+"}]";
                response.getWriter().write(js);
            }else { //数据为空时 respone以下的json格式数据
                String js = jsonOut.replaceAll("]", "{");
                js = js +"\"c\":\"无信息\","+"\"f\":\"无信息\","+"\"d\":\"无信息\","+"\"h\":\"无信息\","+"\"b\":\"无信息\","+"\"i\":\"无信息\","+"\"totalNum\""+":"+0+","+"\"allPage\""+":"+0+ "}]";
                response.getWriter().write(js);

            }

        }
    }
}
