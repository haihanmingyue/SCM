package Controller.Stock.StockServlet.kucun;

import Dao.Product.ProductDao;
import Module.Product.Product;
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
 * 库存查询翻页
 * */
@WebServlet("/stock/kucunpage")
public class ChangePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){

            int page = Integer.parseInt(request.getParameter("page"));
            Object o = request.getSession().getAttribute("key");
            int flag = Integer.parseInt(request.getParameter("flag"));

//
            System.out.println(o==null);
            PageTable<Product> pr = new PageTable<>();
            if(o == null){
                pr.kuCunCreat(page,17,"",0,null);
                pr.getP().setDataList(ProductDao.searchKeySNum(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),"",0));
                if(pr.getP().getDataList().size()==0){
                    pr.kuCunCreat(page-1,17,"",0,null);
                    pr.getP().setDataList(ProductDao.searchKeySNum(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),"",0));
                }
            }else {
                System.out.println("执行第二条");
                Object o1  =  request.getSession().getAttribute("keyList");
                List<String> list = (List<String>) o1;

                if(!list.get(2).equals("")){ //判断第二个数字框的数据是否为空
                    //不为空执行以下
                    pr.kuCunCreat(page,17,list.get(0),Integer.parseInt(list.get(1)),list.get(2));
                    pr.getP().setDataList(ProductDao.searchKeySENum(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),
                            list.get(0),Integer.parseInt(list.get(1)),Integer.parseInt(list.get(2))));
                    if(pr.getP().getDataList().size()==0){
                        pr.kuCunCreat(page,17,list.get(0),Integer.parseInt(list.get(1)),list.get(2));
                        pr.getP().setDataList(ProductDao.searchKeySENum(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),
                                list.get(0),Integer.parseInt(list.get(1)),Integer.parseInt(list.get(2))));
                    }
                }else { //空的话执行以下
                    pr.kuCunCreat(page,17,list.get(0),Integer.parseInt(list.get(1)),null);
                    pr.getP().setDataList(ProductDao.searchKeySNum(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),list.get(0),Integer.parseInt(list.get(1))));
                    if(pr.getP().getDataList().size()==0){
                        pr.kuCunCreat(page-1,17,list.get(0),Integer.parseInt(list.get(1)),null);
                        pr.getP().setDataList(ProductDao.searchKeySNum(pr.getP().getCurrentPage() * pr.getP().getPageSize(), pr.getP().getPageSize(),list.get(0),Integer.parseInt(list.get(1))));
                    }
                }

            }


            if (flag == 1) {
                if(request.getAttribute("currentPage") != null){
                    request.removeAttribute("currentPage");
                }
                List<Product> list = pr.getP().getDataList();
                String jsonOut = JSON.toJSONString(list);
                response.getWriter().write(jsonOut);
            }else {
                request.setAttribute("currentPage",pr.getP().getCurrentPage()+1);
                request.setAttribute("total",pr.getP().getTotalRecord());
                request.setAttribute("totalPage",pr.getP().getTotalPage());
                request.setAttribute("list",pr.getP().getDataList());
                request.getRequestDispatcher("../stocker/productstock.jsp").forward(request,response);
            }
        }
    }
}
