package Dao.Product;

import DBUtil.LinkUtil;
import Module.Po.Poitem;
import Module.Product.Product;
import Module.So.Soitem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDao {
    public static int[] ruku(List<Poitem> list){
//        LinkUtil.openConnection();
        String sql = "update product set num = num + ?, PONum = PONum - ? where ProductCode = ?";
        //4 已入库
        int[] rs = new int[list.size()];
           try {
               for (Poitem p : list) {
                   LinkUtil.stm(sql);
                   LinkUtil.getStm().setInt(1, p.getNum());
                   LinkUtil.getStm().setInt(2, p.getNum());
                   LinkUtil.getStm().setString(3, p.getProductCode());
                   LinkUtil.getStm().addBatch();
               }
               rs = LinkUtil.getStm().executeBatch();
               LinkUtil.getStm().close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return rs;
    }


    public static int[] chuku(List<Soitem> list){
//        LinkUtil.openConnection();
        String sql = "update product set num = num - ?, SONum = SONum - ? where ProductCode = ?";
        //4 已入库
        int[] rs = new int[list.size()];
        try {
            for (Soitem p : list) {
                LinkUtil.stm(sql);
                LinkUtil.getStm().setInt(1, p.getNum());
                LinkUtil.getStm().setInt(2, p.getNum());
                LinkUtil.getStm().setString(3, p.getProductCode());
                LinkUtil.getStm().addBatch();
            }
            rs = LinkUtil.getStm().executeBatch();
            LinkUtil.getStm().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int PONumAndSONum(String productCode){
        int i = 0;
        LinkUtil.openConnection();
        String sql = "select PONum,SONum from product where ProductCode = "+productCode;
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            if (rs.next()){
                i = rs.getInt(1)+rs.getInt(2);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


    public static int[] PONumAndSONum(String[] productCode){
        LinkUtil.getCon2();
        String sql = "select PONum,SONum from product where ProductCode = ?";
        int[] rs = new int[productCode.length];
        ResultSet r;
        try {
            LinkUtil.stm(sql);
            for(int i=0;i<rs.length;i++){
                LinkUtil.getStm().setString(1,productCode[i]);
                r = LinkUtil.getStm().executeQuery();
                if(r.next()){
                    rs[i] = (r.getInt(1)+r.getInt(2));
                }
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static int updateProduct(String productCode, String proName, float price, String unitName, String remark, int category ){
        LinkUtil.openConnection();
        String sql = "update product set Name = '"+proName+"',UnitName = '"+unitName+"',Price = "+price+",Remark = '"+remark+"',CategoryID = "+category+" where ProductCode = '"+productCode+"' ";
        int rs = 0;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }



    public static int selectCategoryDateKey(String categoryid,String date,String key){
        int i = 0;
        LinkUtil.openConnection();
        String sql = "select count(1) from product,category where product.CategoryID like '%"+categoryid+"%' and CreateDate like " +
                "'%"+date+"%' and (concat(product.ProductCode,product.Name) like '%"+key+"%' or category.Name = '"+key+"') " +
                "and product.CategoryID = category.CategoryID;";
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

    public static int deleteProduct(String productCode){

        String sql = "delete from product where ProductCode = '"+productCode+"'";
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

    public static int[] deleteProduct(String[] productCodeList){

        String sql = "delete from product where ProductCode = ?";
        int[] rs = new int[productCodeList.length];
        try {
            LinkUtil.stm(sql);
            for(String str : productCodeList){
                LinkUtil.getStm().setString(1,str);
                LinkUtil.getStm().addBatch();
            }
            rs = LinkUtil.getStm().executeBatch();
            LinkUtil.getStm().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static List<Product> selectCDK(int currPage,int pageSize,String categoryid,String date,String key){
        List<Product> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select * from product,category where product.CategoryID like '%"+categoryid+"%' and CreateDate like '%"+date+"%' and (concat(product.ProductCode,product.Name) like '%"+key+"%' or category.Name = '"+key+"') and product.CategoryID = category.CategoryID limit ?,?";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();

            while (rs.next()){
                Product p = new Product(rs.getString(1),rs.getString("category.Name"),rs.getString(3),rs.getString(4),
                        rs.getFloat(5),rs.getString(6),rs.getString(7));
                p.setCategoryID(rs.getInt("product.CategoryID"));
                list.add(p);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static int selectProduct(int categoryid){
        int i = 0;
        LinkUtil.openConnection();
        String sql = "select count(1) from product where CategoryID = "+categoryid;
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



    //全部产品 库存查询使用
    public static List<Product> AllProductKuCun(int currentPage,int pageSize){
        List<Product> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select ProductCode,Name,num,PONum,SONum from product limit ?,?";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currentPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setProductCode(rs.getString(1));
                p.setName(rs.getString(2));
                p.setNum(rs.getInt(3));
                p.setpONum(rs.getInt(4));
                p.setsONum(rs.getInt(5));
                list.add(p);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //全部产品 产品管理使用
    public static List<Product> selectAllProduct(int currentPage,int pageSize){
        List<Product> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select product.ProductCode,category.Name,product.Name,product.UnitName,product.Price" +
                ",product.CreateDate,product.Remark,product.CategoryID,product.num from product,category where product.CategoryID = category.CategoryID limit ?,?";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currentPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Product p = new Product(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getFloat(5),rs.getString(6),rs.getString(7));
                p.setCategoryID(rs.getInt(8));
                p.setNum(rs.getInt(9));
                list.add(p);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //全部产品个数
    public static int all(){
        int i = 0;
        LinkUtil.openConnection();
        String sql = "select count(1) from product";
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



    public static int searchByProId(String proid){
        int i = 0;
        LinkUtil.openConnection();
        String sql = "select count(1) from product where ProductCode = '"+proid+"' ";
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

    public static Product ByProId(String proid) {
        LinkUtil.openConnection();
        String sql = "select * from product where ProductCode = '"+proid+"'";
        ResultSet rs;
        Product p = null;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            if (rs.next()) {
                p = new Product();
                p.setName(rs.getString("Name"));
                p.setCategoryID(rs.getInt("CategoryID"));
                p.setProductCode(rs.getString("ProductCode"));
                p.setUnitName(rs.getString("UnitName"));
                p.setCreateDate(rs.getString("CreateDate"));
                p.setPrice(rs.getFloat("Price"));
                p.setRemark(rs.getString("Remark"));
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public static int searchByProName(String proname){
        int i = 0;
        LinkUtil.openConnection();
        String sql = "select count(1) from product where Name = '"+proname+"' ";
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
    public static int kuCunSearchByKCN(String key,int startNum,int endNum){
        int i = 0;
        LinkUtil.openConnection();
        String sql = "select count(1) from product where concat(ProductCode,Name) like '%"+key+"%' and num BETWEEN "+startNum+" AND "+endNum+"";
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

    //全部产品 库存查询使用
    public static List<Product> searchKeySENum(int currentPage,int pageSize,String key,int startNum,int endNum){
        List<Product> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select ProductCode,Name,num,PONum,SONum from product where concat(ProductCode,Name) " +
                "like '%"+key+"%' and num BETWEEN "+startNum+" AND "+endNum+" limit ?,?";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currentPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setProductCode(rs.getString(1));
                p.setName(rs.getString(2));
                p.setNum(rs.getInt(3));
                p.setpONum(rs.getInt(4));
                p.setsONum(rs.getInt(5));
                list.add(p);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static int kuCunSearchByKCN(String key,int startNum){
        int i = 0;
        LinkUtil.openConnection();
        String sql = "select count(1) from product where concat(ProductCode,Name) like '%"+key+"%' and num >= "+startNum;
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


    //全部产品 库存查询使用
    public static List<Product> searchKeySNum(int currentPage, int pageSize, String key, int startNum){
        List<Product> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select ProductCode,Name,num,PONum,SONum from product where concat(ProductCode,Name) " +
                "like '%"+key+"%' and num >="+startNum+" limit ?,?";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currentPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setProductCode(rs.getString(1));
                p.setName(rs.getString(2));
                p.setNum(rs.getInt(3));
                p.setpONum(rs.getInt(4));
                p.setsONum(rs.getInt(5));
                list.add(p);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static int insertPro(String proid,int category,String name,String unitName,float price,String date,String remark){
        LinkUtil.openConnection();
        String sql = "insert into product values(?,?,?,?,?,?,?,0,0,0)";
        int rs = 0;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setString(1,proid);
            LinkUtil.getStm().setInt(2,category);
            LinkUtil.getStm().setString(3,name);
            LinkUtil.getStm().setString(4,unitName);
            LinkUtil.getStm().setFloat(5,price);
            LinkUtil.getStm().setString(6,date);
            LinkUtil.getStm().setString(7,remark);
            rs = LinkUtil.getStm().executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }






}
