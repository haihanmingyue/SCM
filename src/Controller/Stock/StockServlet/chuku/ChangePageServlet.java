package Controller.Stock.StockServlet.chuku;

import Dao.So.SomainDao;
import Module.So.Somain;
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
 * 出库查询翻页
 * */
@WebServlet("/stock/chukuChangePage")
public class ChangePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){

            int page = Integer.parseInt(request.getParameter("page"));
            int flag = Integer.parseInt(request.getParameter("flag"));
            PageTable<Somain> pr = new PageTable<>();
            if(flag == 1){ //全部
                pr.ChuKuCreate(page,17,1);
                pr.getP().setDataList(SomainDao.selectByType(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                if(pr.getP().getDataList().size()==0){
                    pr.create(page-1,17,1);
                    pr.getP().setDataList(SomainDao.selectByType(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                }
                System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            }else if(flag == 2){//货到付款
                pr.ChuKuCreate(page,17,2);
                pr.getP().setDataList(SomainDao.selectByTypeOO(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                if(pr.getP().getDataList().size()==0){
                    pr.create(page-1,17,2);
                    pr.getP().setDataList(SomainDao.selectByTypeOO(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                }
                System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            }else if(flag == 3){ //款到发货
                pr.ChuKuCreate(page,17,3);
                pr.getP().setDataList(SomainDao.selectByTypeTT(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                if(pr.getP().getDataList().size()==0){
                    pr.create(page-1,17,3);
                    pr.getP().setDataList(SomainDao.selectByTypeTT(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                }
                System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            }else { //预付款到发货
                pr.ChuKuCreate(page,17,4);
                pr.getP().setDataList(SomainDao.selectByTypeTF(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                if(pr.getP().getDataList().size()==0){
                    pr.create(page-1,17,4);
                    pr.getP().setDataList(SomainDao.selectByTypeTF(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize()));
                }
                System.out.println("显示在页面的的数据有："+pr.getP().getDataList().size()+" 条");
            }

            List<Somain> list = pr.getP().getDataList();
            String jsonOut = JSON.toJSONString(list);
            if(list.size()>0){ //数据不为空
                String js = jsonOut.replaceAll("}]","");
                js = js+","+"\"totalNum\""+":"+pr.getP().getTotalRecord()+","+"\"allPage\""+":"+pr.getP().getTotalPage()+"}]";
                response.getWriter().write(js);
            }else { //数据为空时 respone以下的json格式数据
                String js = jsonOut.replaceAll("]", "{");
                js = js +"\"a\":\"无信息\","+"\"b\":\"无信息\","+"\"c\":\"无信息\","+"\"d\":\"无信息\","+"\"e\":\"无信息\","+"\"f\":\"无信息\","+"\"g\":\"无信息\","+
                        "\"h\":\"无信息\","+"\"i\":\"无信息\","+"\"j\":\"无信息\","+"\"totalNum\""+":"+0+","+"\"allPage\""+":"+0+ "}]";
                response.getWriter().write(js);

            }
        }
    }
}
