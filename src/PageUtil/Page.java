package PageUtil;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

    private static final long serialVersionUID = -8741766802354222579L;

    //每页显示多少条记录
    private int pageSize;
    //当前第几页数据
    private int currentPage;
    //一共有多少条记录
    private int totalRecord;
    //一共多少页记录
    private int totalPage;
    //要显示的数据，使用泛型
    private List<T> dataList;

    public Page() {
        super();
    }

    public Page(int currentPage,int  pageSize) {
        super();
        this.currentPage = currentPage-1;
        this.pageSize = pageSize;

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}