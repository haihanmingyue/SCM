package Dao.So;

import DBUtil.LinkUtil;
import Module.Po.Pomain;
import Module.So.Somain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SomainDao {

    //更新 入库时间 和 入库登记者
    public static int chuku(String account,int SOID,String Date){
        String sql = "update somain set StockTime = '"+Date+"',Status = 2,StockUser = '"+account+"' where SOID = "+SOID;
        //4 已入库
        int rs = 0;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeUpdate();
            LinkUtil.getStm().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public static Somain selectBySOID(long SOID){
        Somain somain = null;
        LinkUtil.openConnection();
        String sql ="select SOID,somain.CustomerCode,somain.Account,somain.CreateTime,TipFee,ProductTotal,POTotal,PayType," +
                "PrePayFee,Status,Remark,StockTime,StockUser,PayTime,PayUser,PrePayTime,PrePayUser," +
                "EndTime,EndUser,customer.`Name` FROM somain,customer where somain.CustomerCode = customer.CustomerCode and SOID = "+SOID;
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                somain = new Somain(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getLong(5),rs.getFloat(6),rs.getFloat(7),rs.getInt(8),rs.getFloat(9),
                        rs.getInt(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
                        rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),
                        rs.getString(20));
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return somain;
    }
    //-------------------------------------搜索所有符合条件的-------------------------------------
    public static int selectByType(){
        int i = 0;
        LinkUtil.openConnection();
        String sql ="select count(1) FROM somain,customer where somain.CustomerCode = customer.CustomerCode and" +
                "((PayType = 1 and Status = 1) or (PayType = 2 and Status = 3) or (PayType = 3 and Status = 5))";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                i = rs.getInt(1);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }


    public static List<Somain> selectByType(int currPage,int pageSize){
        List<Somain> list = new ArrayList<>();
        Somain somain;
        LinkUtil.openConnection();
        String sql ="select SOID,somain.CustomerCode,somain.Account,somain.CreateTime,TipFee,ProductTotal,POTotal,PayType," +
                "PrePayFee,Status,Remark,StockTime,StockUser,PayTime,PayUser,PrePayTime,PrePayUser," +
                "EndTime,EndUser,customer.`Name` FROM somain,customer where somain.CustomerCode = customer.CustomerCode and" +
                "((PayType = 1 and Status = 1) or (PayType = 2 and Status = 3) or (PayType = 3 and Status = 5)) limit ?,?";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                somain = new Somain(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getLong(5),rs.getFloat(6),rs.getFloat(7),rs.getInt(8),rs.getFloat(9),
                        rs.getInt(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
                        rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),
                        rs.getString(20));
                list.add(somain);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    //-------------------------------搜索货到付款的-----------------------------

    public static int selectByTypeOO(){
        int i = 0;
        LinkUtil.openConnection();
        String sql ="select count(1) FROM somain,customer where somain.CustomerCode = customer.CustomerCode and" +
                "((PayType = 1 and Status = 1))";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                i = rs.getInt(1);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }


    public static List<Somain> selectByTypeOO(int currPage,int pageSize){
        List<Somain> list = new ArrayList<>();
        Somain somain;
        LinkUtil.openConnection();
        String sql ="select SOID,somain.CustomerCode,somain.Account,somain.CreateTime,TipFee,ProductTotal,POTotal,PayType," +
                "PrePayFee,Status,Remark,StockTime,StockUser,PayTime,PayUser,PrePayTime,PrePayUser," +
                "EndTime,EndUser,customer.`Name` FROM somain,customer where somain.CustomerCode = customer.CustomerCode and" +
                "((PayType = 1 and Status = 1)) limit ?,?";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                somain = new Somain(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getLong(5),rs.getFloat(6),rs.getFloat(7),rs.getInt(8),rs.getFloat(9),
                        rs.getInt(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
                        rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),
                        rs.getString(20));
                list.add(somain);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    //------------------------------------款到发货----------------------------
    public static int selectByTypeTT(){
        int i = 0;
        LinkUtil.openConnection();
        String sql ="select count(1) FROM somain,customer where somain.CustomerCode = customer.CustomerCode and" +
                "((PayType = 2 and Status = 3))";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                i = rs.getInt(1);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }


    public static List<Somain> selectByTypeTT(int currPage,int pageSize){
        List<Somain> list = new ArrayList<>();
        Somain somain;
        LinkUtil.openConnection();
        String sql ="select SOID,somain.CustomerCode,somain.Account,somain.CreateTime,TipFee,ProductTotal,POTotal,PayType," +
                "PrePayFee,Status,Remark,StockTime,StockUser,PayTime,PayUser,PrePayTime,PrePayUser," +
                "EndTime,EndUser,customer.`Name` FROM somain,customer where somain.CustomerCode = customer.CustomerCode and" +
                "((PayType = 2 and Status = 3)) limit ?,?";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                somain = new Somain(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getLong(5),rs.getFloat(6),rs.getFloat(7),rs.getInt(8),rs.getFloat(9),
                        rs.getInt(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
                        rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),
                        rs.getString(20));
                list.add(somain);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    //---------------------------------预付款到发货----------------------------

    public static int selectByTypeTF(){
        int i = 0;
        LinkUtil.openConnection();
        String sql ="select count(1) FROM somain,customer where somain.CustomerCode = customer.CustomerCode and" +
                "((PayType = 3 and Status = 5))";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                i = rs.getInt(1);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }


    public static List<Somain> selectByTypeTF(int currPage, int pageSize){
        List<Somain> list = new ArrayList<>();
        Somain somain;
        LinkUtil.openConnection();
        String sql ="select SOID,somain.CustomerCode,somain.Account,somain.CreateTime,TipFee,ProductTotal,POTotal,PayType," +
                "PrePayFee,Status,Remark,StockTime,StockUser,PayTime,PayUser,PrePayTime,PrePayUser," +
                "EndTime,EndUser,customer.`Name` FROM somain,customer where somain.CustomerCode = customer.CustomerCode and" +
                "((PayType = 3 and Status = 5)) limit ?,?";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                somain = new Somain(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getLong(5),rs.getFloat(6),rs.getFloat(7),rs.getInt(8),rs.getFloat(9),
                        rs.getInt(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
                        rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),
                        rs.getString(20));
                list.add(somain);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



}
