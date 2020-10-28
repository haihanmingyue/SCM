package Controller.Product.Manager;

import Controller.Product.ManagerService.ManagerBatchDeleteService;
import DBUtil.LinkUtil;
import Dao.Product.ProductDao;
import Dao.Stock.StockrecordDao;
import Module.Stock.Stockrecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/stock/batchdelete")
public class BatchDeleteProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){
            String proCode = request.getParameter("productCode");
            proCode = proCode.replace("[","").replace("]","");
            proCode = proCode.replaceAll("\"","");
            String[] proCodes = proCode.split(",");
            System.out.println("开始判断是否能删除");
           int i = ManagerBatchDeleteService.BatchDelete(proCodes);
           if(i==0){
               response.getWriter().write("already");
           }else if(i==1){
               response.getWriter().write("succeed");
           }else {
               response.getWriter().write("other");
           }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
