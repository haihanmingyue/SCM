package Controller.So;

import Dao.Po.PoitemDao;
import Dao.Po.PomainDao;
import Dao.So.SoitemDao;
import Dao.So.SomainDao;
import Module.Po.Poitem;
import Module.Po.Pomain;
import Module.So.Soitem;
import Module.So.Somain;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/soamin/soamindetail")
public class Somain_detailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String poid = request.getParameter("poid");
        Somain p = SomainDao.selectBySOID(Long.parseLong(poid.trim()));
        List<Soitem> list = SoitemDao.selectBySOID(Long.parseLong(poid.trim()));
        request.setAttribute("soitemList",list);
        request.setAttribute("somain",p);

        request.getRequestDispatcher("../gztm/somain_detail.jsp").forward(request,response);
    }
}
