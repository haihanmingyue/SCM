package Controller.BaoBiao.chukubb;

import Dao.Stock.StockrecordDao;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet("/stock/calnum")
public class CalculationNumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("yearMonth");
        String proCode = request.getParameter("proCode");

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+2;
        String mon;
        if(month<10){
            mon = "0"+month;
        }else {
            mon = month+"";
        }
        String date2 = year+"-"+mon;

        if(date.equals("")){
            System.out.println("空"+true);
        }else {
            proCode = proCode.replaceAll("\"","").replaceAll("\\[","").replaceAll("]","");

            String[] proCodes = proCode.split(",");

            List<List<Integer>> lists = StockrecordDao.NumChange(proCodes,date,date2);
            List<Integer> resultList = new ArrayList<>();
            for(List<Integer> list : lists){
                int sum = 0;
                for(Integer i : list){
                    sum += i;
                }
                resultList.add(sum);
            }
            String result = JSON.toJSONString(resultList);
            response.getWriter().println(result);
            System.out.println(result);
        }

    }
}
