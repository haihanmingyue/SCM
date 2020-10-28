package Dao.Us;

import DBUtil.LinkUtil;
import Module.User.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModelDao {
    public static boolean login(String username,String password){
        System.out.println("开始执行");
        boolean flag = false;
        LinkUtil.openConnection();
        ResultSet rs;
        String sql = "select count(1) from scmuser where Account = '"+username+"' and Password = '"+password+"' ";
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            if(rs.next()){
                if(rs.getInt(1)==1){
                    flag = true;
                }
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static UserModel message(String username){
        UserModel u = new UserModel();
        LinkUtil.openConnection();
        String sql = "SELECT scmuser.Account,scmuser.`Name`,usermodel.ModelCode,systemmodel.ModelUri,scmuser.Status "+
        "FROM scmuser,usermodel,systemmodel where scmuser.Account = usermodel.Account "+
        "and usermodel.ModelCode = systemmodel.ModelCode and scmuser.Account = '"+username+"' ";
        ResultSet rs;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            if(rs.next()){
                u.setAccount(rs.getString(1));
                u.setName(rs.getString(2));
                u.setModelCode(rs.getInt(3));
                u.setModelUri(rs.getString(4));
                u.setStatus(rs.getInt(5));
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    public static void main(String[] args) {

        System.out.println(login("jiong521968","asd123"));

    }
}
