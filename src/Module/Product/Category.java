package Module.Product;

import com.alibaba.fastjson.annotation.JSONField;

public class Category {

    @JSONField(name = "a")
    private int categoryID;
    @JSONField(name = "b")
    private String name;
    @JSONField(name = "c")
    private String remark;

    public Category(){}

    public Category(int categoryID,String name,String remark){
        this.categoryID = categoryID;
        this.name = name;
        if(remark==null)
            remark="";
        this.remark = remark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
