package Controller.Po;

import Dao.Po.PoitemDao;
import Dao.Po.PomainDao;
import Dao.So.SoitemDao;
import Module.Po.Poitem;
import Module.Po.Pomain;
import Module.So.Soitem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/poamin/poamindetail")
public class Pomain_detailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){
            String poid = request.getParameter("poid");
            Pomain p = PomainDao.selectByPOID(Long.parseLong(poid.trim()));
            List<Poitem> list = PoitemDao.selectByPOID(Long.parseLong(poid.trim()));
            request.setAttribute("poitemList",list);
            request.setAttribute("pomain",p);
            request.getRequestDispatcher("../gztm/pomain_detail.jsp").forward(request,response);
        }


    }
}
