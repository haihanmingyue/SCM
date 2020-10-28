package Module.So;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 采购单明细
 * */
public class Soitem {
    private long SOID; //采购单编号
    @JSONField(name="a")
    private String productCode; //产品编号
    @JSONField(name="b")
    private String productName;//产品名称
    @JSONField(name="c")
    private int num; //产品数量
    @JSONField(name="d")
    private String unitName;//数量单位
    @JSONField(name="e")
    private float unitPrice;//产品单价
    @JSONField(name="f")
    private float itemPrice;//产品总价

    public long getSOID() {
        return SOID;
    }

    public void setSOID(long SOID) {
        this.SOID = SOID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }
}
