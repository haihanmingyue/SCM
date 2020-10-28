package Dao.Stock;

import DBUtil.LinkUtil;
import Module.Po.Poitem;
import Module.So.Soitem;
import Module.Stock.Stockrecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockrecordDao {
    public static int insertStockRecord(String productCode,String ID,int changeNumber,int type,String Date,String userAccount){

        String sql = "insert into stockrecord values(null,?,?,?,?,?,?)";
        //4 已入库
        int  rs = 0;
        try {
           LinkUtil.stm(sql);
            LinkUtil.getStm().setString(1,productCode);
            LinkUtil.getStm().setString(2,ID);
            LinkUtil.getStm().setInt(3,changeNumber);
            LinkUtil.getStm().setInt(4,type);
            LinkUtil.getStm().setString(5,Date);
            LinkUtil.getStm().setString(6,userAccount);
            rs =  LinkUtil.getStm().executeUpdate();
            LinkUtil.getStm().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public static int[] insertRuKuRecord(List<Poitem> list, int Type, String Date, String account){

        String sql = "insert into stockrecord values(null,?,?,?,?,?,?)";
        //4 已入库
        int[] rs = new int[0];
        try {
          for(Poitem p : list){
              LinkUtil.stm(sql);
              LinkUtil.getStm().setString(1,p.getProductCode());
              LinkUtil.getStm().setString(2, String.valueOf(p.getPOID()));
              LinkUtil.getStm().setInt(3,p.getNum());
              LinkUtil.getStm().setInt(4,Type);
              LinkUtil.getStm().setString(5,Date);
              LinkUtil.getStm().setString(6,account);
              LinkUtil.getStm().addBatch();
          }
            rs =  LinkUtil.getStm().executeBatch();
            LinkUtil.getStm().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int[] insertChuKuRecord(List<Soitem> list, int Type, String Date, String account){

        String sql = "insert into stockrecord values(null,?,?,?,?,?,?)";
        //4 已入库
        int[] rs = new int[0];
        try {
            for(Soitem s : list){
                LinkUtil.stm(sql);
                LinkUtil.getStm().setString(1,s.getProductCode());
                LinkUtil.getStm().setString(2, String.valueOf(s.getSOID()));
                LinkUtil.getStm().setInt(3,s.getNum());
                LinkUtil.getStm().setInt(4,Type);
                LinkUtil.getStm().setString(5,Date);
                LinkUtil.getStm().setString(6,account);
                LinkUtil.getStm().addBatch();
            }
            rs =  LinkUtil.getStm().executeBatch();
            LinkUtil.getStm().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int RecordNum(String productCode){
        LinkUtil.openConnection();
        String sql = "select count(1) from stockrecord where ProductCode = '"+productCode+"' ";
        ResultSet rs ;
        int i = 0;
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


    public static int deleteRecord(String productCode){
        String sql = "delete from stockrecord where ProductCode = ?";
        int i = 0;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setString(1,productCode);
            i = LinkUtil.getStm().executeUpdate();
            LinkUtil.getStm().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


    public static int[] deleteRecord(List<String> productCode){

        String sql = "delete from stockrecord where ProductCode = ?";
        int[] i = new int[productCode.size()];
        try {
            LinkUtil.stm(sql);
           for(String s : productCode){
               LinkUtil.getStm().setString(1,s);
               LinkUtil.getStm().addBatch();
           }
           i = LinkUtil.getStm().executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


    public static List<Stockrecord> Record(int currPage, int pageSize, String productCode){
        List<Stockrecord> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select * from stockrecord where ProductCode = '"+productCode+"' limit ?,?";
        ResultSet rs ;

        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Stockrecord s = new Stockrecord();
                s.setStockID(rs.getInt("StockID"));
                s.setProductCode(rs.getString("ProductCode"));
                s.setOrderCode(rs.getInt("OrderCode"));
                s.setStockNum(rs.getInt("StockNum"));
                s.setStockType(rs.getInt("StockType"));
                s.setStockTime(rs.getString("StockTime"));
                s.setCreateUser(rs.getString("CreateUser"));
                list.add(s);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int RecordDKT(String productCode,String date,String key,String type){
        LinkUtil.openConnection();
        String sql = "select count(1) from stockrecord where ProductCode = '"+productCode+"' and StockType like '%"+type+"%' and StockTime like '%"+date+"%' and CONCAT(IFNULL(OrderCode,\"\"),CreateUser) LIKE '%"+key+"%'";
        ResultSet rs ;
        int i = 0;
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

    public static List<Stockrecord> RecordDKT(int currPage, int pageSize, String productCode,String date,String key,String type){
        List<Stockrecord> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select * from stockrecord where ProductCode = '"+productCode+"' and StockType like '%"+type+"%' and StockTime like '%"+date+"%' and CONCAT(IFNULL(OrderCode,\"\"),CreateUser) LIKE '%"+key+"%' limit ?,?";
        ResultSet rs ;

        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Stockrecord s = new Stockrecord();
                s.setStockID(rs.getInt("StockID"));
                s.setProductCode(rs.getString("ProductCode"));
                s.setOrderCode(rs.getInt("OrderCode"));
                s.setStockNum(rs.getInt("StockNum"));
                s.setStockType(rs.getInt("StockType"));
                s.setStockTime(rs.getString("StockTime"));
                s.setCreateUser(rs.getString("CreateUser"));
                list.add(s);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int RKRecord(String date){
        LinkUtil.openConnection();
        String sql = "select count(1) from stockrecord WHERE IFNULL(OrderCode,0) != 0 and StockType = 1 and StockTime like '%"+date+"%' ";
        ResultSet rs ;
        int i = 0;
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


    public static List<Stockrecord> allRk(int currentPage,int pageSize,String date){
        List<Stockrecord> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select distinct StockID,stockrecord.ProductCode,OrderCode,StockNum,StockType,StockTime,CreateUser,product.Name,poitem.ItemPrice " +
                "from poitem,stockrecord,product WHERE poitem.POID = stockrecord.OrderCode and stockrecord.ProductCode = product.ProductCode and IFNULL(OrderCode,0) != 0 and  StockType = 1 and StockTime like '%"+date+"%' limit ?,?";
        ResultSet rs ;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currentPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Stockrecord s = new Stockrecord();
                s.setStockID(rs.getInt("StockID"));
                s.setProductCode(rs.getString("stockrecord.ProductCode"));
                s.setOrderCode(rs.getInt("OrderCode"));
                s.setStockNum(rs.getInt("StockNum"));
                s.setStockType(rs.getInt("StockType"));
                s.setStockTime(rs.getString("StockTime"));
                s.setCreateUser(rs.getString("CreateUser"));
                s.setProName(rs.getString("product.Name"));
                s.setTotalMoney(rs.getFloat("poitem.ItemPrice"));
                System.out.println(rs.getFloat("poitem.ItemPrice"));
                list.add(s);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public static int CKRecord(String date){
        LinkUtil.openConnection();
        String sql = "select count(1) from stockrecord WHERE IFNULL(OrderCode,0) != 0 and StockType = 2 and StockTime like '%"+date+"%' ";
        ResultSet rs ;
        int i = 0;
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
    public static List<Stockrecord> allCK(int currentPage,int pageSize,String date){
        List<Stockrecord> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select distinct StockID,stockrecord.ProductCode,OrderCode,StockNum,StockType,StockTime,CreateUser,product.Name,soitem.ItemPrice " +
                "from soitem,stockrecord,product WHERE soitem.SOID = stockrecord.OrderCode and stockrecord.ProductCode = product.ProductCode and IFNULL(OrderCode,0) != 0 and  StockType = 2 and StockTime like '%"+date+"%' limit ?,?";
        ResultSet rs ;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currentPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Stockrecord s = new Stockrecord();
                s.setStockID(rs.getInt("StockID"));
                s.setProductCode(rs.getString("stockrecord.ProductCode"));
                s.setOrderCode(rs.getInt("OrderCode"));
                s.setStockNum(rs.getInt("StockNum"));
                s.setStockType(rs.getInt("StockType"));
                s.setStockTime(rs.getString("StockTime"));
                s.setCreateUser(rs.getString("CreateUser"));
                s.setProName(rs.getString("product.Name"));
                s.setTotalMoney(rs.getFloat("soitem.ItemPrice"));
                list.add(s);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<List<Integer>> NumChange(String[] proCodeList,String date1,String checkDate){
        List<Integer> list ;
        List<List<Integer>> list2 = new ArrayList<>();
        LinkUtil.openConnection();
        ResultSet rs  ;
        for(int i =0 ;i<proCodeList.length;i++){
            list = new ArrayList<>();
            String sql = "SELECT ProductCode,OrderCode,StockNum,StockType,StockTime FROM stockrecord WHERE (StockType = 1 or StockType = 2) and ProductCode = '"+proCodeList[i]+"' and StockTime BETWEEN '"+date1+"' AND '"+checkDate+"'";
            try {
                LinkUtil.stm(sql);
                rs = LinkUtil.getStm().executeQuery();
                while (rs.next()){

                    if(rs.getInt(4)==1){
                        list.add(0 - rs.getInt(3));
                    }else {
                        list.add(rs.getInt(3));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            list2.add(list);

        }
        LinkUtil.close();
        return list2;
    }

    public static void main(String[] args) {
//        String[] proCode = new String[]{"10001","10002"};
//        List<List<Integer>> lists =   NumChange(proCode,"2020-01","2020-09");
//        System.out.println(lists.size());
//        for(List<Integer> list : lists){
//           for(Integer i : list){
//               System.out.print(i+",");
//           }
//           System.out.println();
//        }


    }
}
