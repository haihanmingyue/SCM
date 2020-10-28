package Controller.Stock.StockerServices;

import Dao.Po.PoitemDao;
import Module.Po.Poitem;
import Module.User.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/stock/rukuservice")
public class RuKuServiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       //接收传送来的poid
        String POID = request.getParameter("poid");
        System.out.println("传送过来的poid："+POID);
        //先搜索这个单子下有多少个产品
        List<Poitem> list = PoitemDao.selectByPOID(Long.parseLong(POID));
        for(Poitem p : list){
            System.out.println("单号："+p.getPOID()+" 产品编号："+p.getProductCode()+"数量 "+p.getNum());
        }

        UserModel u = (UserModel) request.getSession().getAttribute("user");
        String account = u.getAccount();
        int flag = 0;

        try {
            flag = StockService.ru(account,Integer.parseInt(POID.trim()),list,1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (flag==1){
//          response.getWriter().write("<script>alert('入库成功!');location.href='../stock/changePage?flag="+flagc+"&page="+currentPage+"';</script>");
            response.getWriter().write("succeed");
      }else {
//          response.getWriter().write("<script>alert('入库失败!');location.href='../stock/changePage?flag="+flagc+"&page="+currentPage+"'';</script>");
            response.getWriter().write("false");
      }

    }
}
