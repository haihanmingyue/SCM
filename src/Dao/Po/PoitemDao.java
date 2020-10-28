package Dao.Po;

import DBUtil.LinkUtil;
import Module.Po.Poitem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoitemDao {
    public static List<Poitem> selectByPOID(long poid){
        List<Poitem> list = new ArrayList<>();
        LinkUtil.openConnection();
        ResultSet rs;
        String sql = "select poitem.POID,product.`Name`,poitem.UnitName,poitem.Num,poitem.UnitPrice," +
                "poitem.ItemPrice,poitem.ProductCode from poitem,product where product.ProductCode = " +
                "poitem.ProductCode and POID = "+poid;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Poitem p  = new Poitem();
                p.setPOID(rs.getLong(1));p.setProductName(rs.getString(2));
                p.setUnitName(rs.getString(3));p.setNum(rs.getInt(4));
                p.setUnitPrice(rs.getFloat(5));p.setItemPrice(rs.getFloat(6));
                p.setProductCode(rs.getString(7));
                list.add(p);
            }
            LinkUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    };
}
