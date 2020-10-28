package DBUtil;


import Dao.Product.ProductDao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class LinkUtil {
//    JDBC连接Mysql6 com.mysql.cj.jdbc.Driver， 需要指定时区serverTimezone:
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private final static String SQL_URL = "jdbc:mysql://127.0.0.1:3306/vvb?serverTimezone=UTC&characterEncoding=utf8";
    private final static String ROOT_NAME = "root";
    private final static String PASSWORD = "as521968";
    private static Connection con ;
    private static PreparedStatement stm;
    private static ResultSet rs;
//    Statement对象 存在 sql注入问题 不安全  如传入1==1；
//            如 delete from student where 1==1  就会删除全部数据

    public static Connection openConnection()  {

        //使用连接池
        Context envContext;
        try {
            InitialContext ic = new InitialContext();
            envContext = (Context) ic.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/mysql");
            con = ds.getConnection();
            con.setAutoCommit(false);
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }

        return con;

    }

    public static Connection getCon2() {  //测试用
        try{
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(SQL_URL,ROOT_NAME,PASSWORD);
            con.setAutoCommit(false);
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
       LinkUtil.openConnection();
    }

    public static void close()  {
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //stm 关闭时 会自动关闭 rs
        if(stm!=null){
            try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static PreparedStatement stm(String sql) throws SQLException {

        stm = con.prepareStatement(sql);
        return stm;
    }
    public static void commit() throws SQLException {
       con.commit();
    }
    public static void rollBack() {
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PreparedStatement getStm() {
        return stm;
    }

    public static ResultSet getRs() {
        return rs;
    }
}
