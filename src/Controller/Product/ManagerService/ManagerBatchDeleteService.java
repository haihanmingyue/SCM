package Controller.Product.ManagerService;

import DBUtil.LinkUtil;
import Dao.Product.ProductDao;
import Dao.Stock.StockrecordDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerBatchDeleteService {
    public static int BatchDelete(String[] proCodes) {
        int resyle;
        int[] nums = ProductDao.PONumAndSONum(proCodes);//判断每个要被删除的产品是否有产品相关的销售单或采购单存在
        boolean temp = true;
        for (int j : nums) {
            if (j > 0) {
                temp = false;
                break;
            }
        }
        if (temp) {
            System.out.println("可以删除");
            List<Integer> list = new ArrayList<>();//存放有记录的，各是多少条记录，用来判断删除是否正确
            List<String> codeList = new ArrayList<>();
            for (String s : proCodes) {   //在库存记录有记录的  先删掉库存变更里的记录
                int i = StockrecordDao.RecordNum(s);
                if (i > 0) {
                    list.add(i); //有记录的 把对应记录个数存进list
                    codeList.add(s);//有记录的 把对应code存进list
                }
            }
            System.out.println("打开连接");
            LinkUtil.openConnection();
            boolean flag = true;
            if (codeList.size() > 0) {
                System.out.println("执行删除stockrecord");
                int[] stock = StockrecordDao.deleteRecord(codeList);
                System.out.println(stock.length);
                int[] pro = ProductDao.deleteProduct(proCodes);
                System.out.println(pro.length);
                for (int k = 0; k < list.size(); k++) {
                    if (stock[k] != list.get(k)) {
                        flag = false;
                        break;
                    }
                }

                for (int i : pro) {
                    if (i == 0) {
                        flag = false;
                        break;
                    }
                }
            } else {
                System.out.println("不执行stockrecord");
                int[] pro = ProductDao.deleteProduct(proCodes);
                System.out.println(pro.length);
                for (int i : pro) {
                    if (i == 0) {
                        flag = false;
                        break;
                    }
                }
            }

            System.out.println("flag:" + flag);
            if (flag) {
                try {
                    LinkUtil.commit();
                    resyle = 1;
                } catch (SQLException e) {
                    resyle = 2;
                    LinkUtil.rollBack();
                    e.printStackTrace();
                }
            } else {
                LinkUtil.rollBack();
                resyle = 2;
            }
        } else {
            resyle = 0;
        }
        System.out.println("关闭连接");
        LinkUtil.close();

        return resyle;
    }
}
