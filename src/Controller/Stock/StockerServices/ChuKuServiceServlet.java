package Controller.Stock.StockerServices;

import Dao.Po.PoitemDao;
import Dao.So.SoitemDao;
import Module.Po.Poitem;
import Module.So.Soitem;
import Module.User.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/stock/chukuservice")
public class ChuKuServiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收传送来的poid
        String SOID = request.getParameter("soid");
        System.out.println("传送过来的poid："+SOID);
        //先搜索这个单子下有多少个产品
        List<Soitem> list = SoitemDao.selectBySOID(Long.parseLong(SOID));
        for(Soitem s : list){
            System.out.println("单号："+s.getSOID()+" 产品编号："+s.getProductCode()+"数量 "+s.getNum());
        }

        UserModel u = (UserModel) request.getSession().getAttribute("user");
        String account = u.getAccount();
        int flag = 0;

        try {
            flag = StockService.chu(account,Integer.parseInt(SOID.trim()),list,2);
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
