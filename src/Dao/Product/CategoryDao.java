package Dao.Product;

import DBUtil.LinkUtil;
import Module.Product.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CategoryDao {
    public static int allCount(){  //搜索全部个数
        int i  = 0;
        LinkUtil.openConnection();
        String sql = "select count(1) from category";
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
    public static List<Integer> allClassNo(){  //搜索全部分类序号
        List<Integer> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select CategoryID from category";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                list.add(rs.getInt(1));
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<Category> allClass(int currentPage,int pageSize){  //分页搜索全部
        List<Category> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select * from category limit ?,?";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currentPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Category c = new Category(rs.getInt(1),rs.getString(2),rs.getString(3));
                list.add(c);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int allKeyCount(String categoryID,String Name){  //搜索全部个数
        int i  = 0;
        LinkUtil.openConnection();
        String sql = "select count(1) from category where CategoryID like '%"+categoryID+"%' and (Name like '%"+Name+"%' or CategoryID like '%"+Name+"%') ";
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

    public static List<Category> keyClass(int currentPage,int pageSize,String categoryID,String Name){  //条件搜索
        List<Category> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select * from category where CategoryID like '%"+categoryID+"%' and (Name like '%"+Name+"%' or CategoryID like '%"+Name+"%') limit ?,?";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setInt(1,currentPage);
            LinkUtil.getStm().setInt(2,pageSize);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Category c = new Category(rs.getInt(1),rs.getString(2),rs.getString(3));
                list.add(c);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    //删除分类
    public static int deleteClass(int categoryid){
        LinkUtil.openConnection();
        String sql = "delete from category where CategoryID = "+categoryid;
        int rs = 0;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    //添加分类
    public static int addClass(String name,String msg){
        LinkUtil.openConnection();
        String sql = "insert into category values(null,?,?)";
        int rs = 0;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setString(1,name);
            LinkUtil.getStm().setString(2,msg);
            rs = LinkUtil.getStm().executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //跟新
    public static int update(int id,String name,String msg){
        LinkUtil.openConnection();
        String sql = "update category set Name = ?,Remark = ? where CategoryID = "+id;
        int rs = 0;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setString(1,name);
            LinkUtil.getStm().setString(2,msg);
            rs = LinkUtil.getStm().executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    //添加分类
    public static int updatemsg(int id,String msg){
        LinkUtil.openConnection();
        String sql = "update category set Remark = "+"? where CategoryID = "+id;
        int rs = 0;
        try {
            LinkUtil.stm(sql);
            LinkUtil.getStm().setString(1,msg);
            rs = LinkUtil.getStm().executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //通过分类名查询个数
    public static int allNameCount(String name){
        int i  = 0;
        LinkUtil.openConnection();
        String sql = "select count(1) from category where Name = '"+name+"' ";
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



    public static String className(int id){
        String name = null;
        LinkUtil.openConnection();
        String sql = "select Name from category where CategoryID = "+id;
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            if (rs.next()){
                name = rs.getString(1);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }


    public static List<Category> selectAll(){
        List<Category> list = new ArrayList<>();
        LinkUtil.openConnection();
        String sql = "select CategoryID,Name from category";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Category c  = new Category();
                c.setCategoryID(rs.getInt(1));
                c.setName(rs.getString(2));
                list.add(c);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        int i   =  updatemsg(1001,"起飞");
        LinkUtil.close();
        System.out.println(i);
    }
}
