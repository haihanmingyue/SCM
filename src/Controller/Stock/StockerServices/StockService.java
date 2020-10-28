package Controller.Stock.StockerServices;

import DBUtil.LinkUtil;
import Dao.Po.PomainDao;
import Dao.Product.ProductDao;
import Dao.So.SomainDao;
import Dao.Stock.StockrecordDao;
import Module.Po.Poitem;
import Module.So.Soitem;
import TimeUtil.WebTime;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class StockService {

    public static int ru(String account,int POID, List<Poitem> list,int type) throws SQLException {


        int flag = 0;
        boolean temp = true;
        String Date = WebTime.getComNowTime();
        LinkUtil.openConnection();
        int rs1 = PomainDao.ruku(account,POID,Date);
        System.out.println("rs1:"+rs1);
        int[] rs2 = ProductDao.ruku(list);
        System.out.println("rs2:"+ Arrays.toString(rs2));
        int[] rs3 = StockrecordDao.insertRuKuRecord(list,type,Date,account);
        System.out.println("rs3:"+ Arrays.toString(rs3));

       if(rs1 != 1){ //先判断rs1
           temp = false;
       }
       for(int i : rs2){//接着判断rs2
           if(i != 1){
               temp = false;
               break;
           }
       }
     for(int j : rs3){//最后判断rs3
         if(j != 1){
             temp = false;
             break;
         }
     }
        if(temp){
            LinkUtil.commit();
            flag = 1;
        }else {
            LinkUtil.rollBack();
        }
        LinkUtil.close();
        return flag;
    }

    public static int chu(String account, int SOID, List<Soitem> list, int type) throws SQLException {

        int flag = 0;
        boolean temp = true;
        String Date = WebTime.getComNowTime();
        LinkUtil.openConnection();
        int rs1 = SomainDao.chuku(account,SOID,Date);
        System.out.println("rs1:"+rs1);
        int[] rs2 = ProductDao.chuku(list);
        System.out.println("rs2:"+ Arrays.toString(rs2));
        int[] rs3 = StockrecordDao.insertChuKuRecord(list,type,Date,account);
        System.out.println("rs3:"+ Arrays.toString(rs3));

        if(rs1 != 1){ //先判断rs1
            temp = false;
        }
        for(int i : rs2){//接着判断rs2
            if(i != 1){
                temp = false;
                break;
            }
        }
        for(int j : rs3){//最后判断rs3
            if(j != 1){
                temp = false;
                break;
            }
        }
        if(temp){
            LinkUtil.commit();
            flag = 1;
        }else {
            LinkUtil.rollBack();
        }
        LinkUtil.close();
        return flag;
    }




}
