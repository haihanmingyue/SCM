package Module.Product;

import com.alibaba.fastjson.annotation.JSONField;

public class Product {
    @JSONField(name = "a")
    private String productCode;
    @JSONField(name = "b")
    private int categoryID;
    @JSONField(name = "c")
    private String name;
    @JSONField(name = "d")
    private String unitName;
    @JSONField(name = "e")
    private float price;
    @JSONField(name = "f")
    private String createDate;
    @JSONField(name = "g")
    private String remark;
    @JSONField(name = "h")
    private int num;
    @JSONField(name = "i")
    private int pONum;
    @JSONField(name = "j")
    private int sONum;
    @JSONField(name = "k")
    private String categoryName;//分类名称，来自产品分类实体

    public Product(){}

    public Product(String productCode,String CategoryName,String name,String unitName,float price,
                   String createDate,String remark){
        this.productCode = productCode;
        this.name = name;
        this.categoryName = CategoryName;
        this.categoryName = CategoryName;
        this.unitName = unitName;
        this.price = price;
        this.createDate = createDate;
        this.remark = remark;
        if(remark == null)
            this.remark = "";
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getpONum() {
        return pONum;
    }

    public void setpONum(int pONum) {
        this.pONum = pONum;
    }

    public int getsONum() {
        return sONum;
    }

    public void setsONum(int sONum) {
        this.sONum = sONum;
    }
}
