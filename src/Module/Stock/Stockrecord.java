package Module.Stock;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 库存记录
 * */
public class Stockrecord {
    @JSONField(name = "a")
    private int stockID;//序列号
    @JSONField(name = "b")
    private String productCode;//产品编号
    @JSONField(name = "c")
    private int orderCode;//相关单据编号
    @JSONField(name = "d")
    private int stockNum;//库存变化数量
    @JSONField(name = "e")
    private String stockTypeName;//变化类型名字

    private int stockType;//库存变化类型
    @JSONField(name = "f")
    private String stockTime;//库存变化时间
    @JSONField(name = "g")
    private String createUser;//经手人
    @JSONField(name = "h")
    private String proName;//产品名
    @JSONField(name = "i")
    private float totalMoney;//产品总金额

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getStockTypeName() {

        if(getStockType()==1){
            return "采购入库";
        }
        else if(getStockType()==2){
            return "销售出库";
        }else if(getStockType()==3){
            return "盘点入库";
        }else {
            return "盘点出库";
        }

    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }



    public int getStockType() {
        return stockType;
    }

    public void setStockType(int stockType) {
        this.stockType = stockType;
    }

    public String getStockTime() {
        return stockTime;
    }

    public void setStockTime(String stockTime) {
        this.stockTime = stockTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
