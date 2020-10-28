package Module.So;

import com.alibaba.fastjson.annotation.JSONField;

public class Somain {

    @JSONField(name="a")
    private long SOID; //采购单编号
    @JSONField(name="b")
    private String createTime; //创建时间
    @JSONField(name="c")
    private String account; //创建用户

    private String customerCode;//客户编号
    @JSONField(name="d")
    private String customerName;//客户名称

    @JSONField(name="e")
    private float tipFee;//附加费用
    @JSONField(name="f")
    private float productTotal; //产品总价
    @JSONField(name="g")
    private float pOTotal; //采购单总价格

    private int payType; //付款方式
    @JSONField(name="h")
    private String payName;//付款方式名称

    @JSONField(name="i")
    private float prePayFee; //最低预付款金额

    private int status ; //处理状态
    @JSONField(name="j")
    private String staName;

    private String remark;
    private String stockTime; //出库登记时间
    private String stockUser; //出库登记用户
    private String payTime;//付款登记时间
    private String payUser;//付款登记用户
    private String prePayUser;
    private String prePayTime;//预付款登记时间
    private String endTime;
    private String endUser;

    public Somain(){}
    public Somain(long SOID,String customerCode,String account,String createTime,float tipFee,float productTotal,
                  float pOTotal,int payType,float prePayFee, int status,String remark,String stockTime,String stockUser,String payTime,
                  String payUser,String prePayTime,String prePayUser,String endTime,String endUser,String customerName){
        this.SOID = SOID;this.customerCode = customerCode;this.account = account;this.createTime = createTime;this.tipFee =tipFee;
        this.productTotal = productTotal;this.pOTotal = pOTotal;this.payType = payType;this.prePayFee = prePayFee;this.status = status;
        this.remark =remark;this.stockTime = stockTime;this.stockUser = stockUser;this.payTime = payTime;this.payUser = payUser;this.prePayTime = prePayTime;
        this.prePayUser = prePayUser;this.endTime = endTime;this.endUser =endUser;this.customerName = customerName;
    }
    public String getStaName() {
        if(getStatus()==1){
            this.staName= "新增";
        }else if(getStatus()==2){
            this.staName = "已收货";
        }
        else if(getStatus()==3){
            this.staName = "已付款";
        }else if(getStatus()==4){
            this.staName = "已了结";
        }
        else if(getStatus()==5) {
            this.staName = "已预付";
        }
        return staName;
    }
    public String getPayName() {

        if(getPayType()==1){
            this.payName= "货到付款";
        }else if(getPayType()==2){
            this.payName = "款到发货";
        }else {
            this.payName = "预付款到发货";
        }
        return payName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPrePayUser() {
        return prePayUser;
    }

    public void setPrePayUser(String prePayUser) {
        this.prePayUser = prePayUser;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndUser() {
        return endUser;
    }

    public void setEndUser(String endUser) {
        this.endUser = endUser;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }

    public String getStockTime() {
        return stockTime;
    }

    public void setStockTime(String stockTime) {
        this.stockTime = stockTime;
    }

    public String getStockUser() {
        return stockUser;
    }

    public void setStockUser(String stockUser) {
        this.stockUser = stockUser;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getPrePayTime() {
        return prePayTime;
    }

    public void setPrePayTime(String prePayTime) {
        this.prePayTime = prePayTime;
    }



    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }



    public float getTipFee() {
        return tipFee;
    }

    public void setTipFee(float tipFee) {
        this.tipFee = tipFee;
    }

    public float getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(float productTotal) {
        this.productTotal = productTotal;
    }

    public long getSOID() {        return SOID;
    }

    public void setSOID(long SOID) {
        this.SOID = SOID;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getpOTotal() {
        return pOTotal;
    }

    public void setpOTotal(float pOTotal) {
        this.pOTotal = pOTotal;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public float getPrePayFee() {
        return prePayFee;
    }

    public void setPrePayFee(float prePayFee) {
        this.prePayFee = prePayFee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
