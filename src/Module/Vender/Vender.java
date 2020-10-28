package Module.Vender;

/**
 * 供应商
 * */
public class Vender {

    private String VenderCode;//供应商编号
    private String Name; //供应商名称
    private String Password;//密码
    private String Contactor;// 联系人
    private String Address; //地址
    private String Postcode;// 邮编
    private String Tel;//电话
    private String Fax;//传真
    private String CreateDate;//创建日期

    public String getVenderCode() {
        return VenderCode;
    }

    public void setVenderCode(String venderCode) {
        VenderCode = venderCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getContactor() {
        return Contactor;
    }

    public void setContactor(String contactor) {
        Contactor = contactor;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }
}
