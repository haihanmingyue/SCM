package Controller.Stock.StockServlet.chuku;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 入库页面 按key 查询
 * */
@WebServlet("/stock/chukulookup")
public class ChuKuSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){
//
//            String key = request.getParameter("selectkey");
//            PageTable<SoItemMain> pr = new PageTable<>();//每页 20 个数据
//            String[] keys = key.split(" ");
//            String str,str2,str3,str4;
//            if(keys[0].equals("null")){
//                str = "";
//            }else {
//                str = keys[0];
//            }
//            if(keys[1].equals("null")){
//                str2 = "";
//            }else {
//                str2 = keys[1];
//            }
//            if(keys[2].equals("null")){
//                str3 = "";
//            }else {
//                str3 = keys[2];
//            }
//            if(keys[3].equals("null")){
//                str4 = "";
//            }else {
//                str4 = keys[3];
//            }
//            pr.ChuKuCreate(1,17,str,str2,str3,str4);
//            pr.getP().setDataList(SoItemMainDao.selectKeyAll(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),str,str2,str3,str4));
//            List<String> keyList = new ArrayList<>();
//            keyList.add(str);keyList.add(str2);keyList.add(str3);keyList.add(str4);
//            request.setAttribute("total",pr.getP().getTotalRecord());
//            request.setAttribute("totalPage",pr.getP().getTotalPage());
//            request.setAttribute("list",pr.getP().getDataList());
//            if((str+str2+str3+str4).equals("")){
//                System.out.println("拼装"+str+str2+str3+str4);
//                request.getSession().setAttribute("key",null);
//            }else {
//                request.getSession().setAttribute("key",str+" "+ NumberToStr.type(str2)+" "+NumberToStr.status(str3)+" "+str4);
//            }
//            request.getSession().setAttribute("keyList",keyList);
//            request.getRequestDispatcher("../stocker/rukubaobiao.jsp").forward(request,response);
//
        }
    }
}
