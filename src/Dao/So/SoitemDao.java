package Dao.So;

import DBUtil.LinkUtil;
import Module.Po.Poitem;
import Module.So.Soitem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SoitemDao {
    public static List<Soitem> selectBySOID(long poid){
        List<Soitem> list = new ArrayList<>();
        LinkUtil.openConnection();
        ResultSet rs;
        String sql = "select soitem.SOID,product.`Name`,soitem.UnitName,soitem.Num,soitem.UnitPrice," +
                "soitem.ItemPrice,soitem.ProductCode from soitem,product where product.ProductCode = " +
                "soitem.ProductCode and SOID = "+poid;
        try {
            LinkUtil.stm(sql);
            rs = LinkUtil.getStm().executeQuery();
            while (rs.next()){
                Soitem p  = new Soitem();
                p.setSOID(rs.getLong(1));p.setProductName(rs.getString(2));
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
