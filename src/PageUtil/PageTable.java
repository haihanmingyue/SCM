package PageUtil;

import Dao.Po.PomainDao;
import Dao.Product.CategoryDao;
import Dao.Product.ProductDao;
import Dao.So.SomainDao;
import Dao.Stock.StockrecordDao;

public class PageTable<T> {

   private Page<T> p;

    public PageTable(){
        super();
   }


    public void create(int currentPage, int pageSize,int flag){
        p = new Page<>(currentPage, pageSize);
        int numbers;
        if(flag == 1){
            numbers = PomainDao.selectByType();//查找个
        }else if(flag == 2){
            numbers = PomainDao.selectByTypeOO();//查找个
        }else if(flag == 3){
            numbers = PomainDao.selectByTypeTT();//查找个
        }else {
            numbers = PomainDao.selectByTypeTF();//查找个
        }
        if (numbers % p.getPageSize() == 0) {  //计算页数
            p.setTotalPage(numbers / p.getPageSize());
        } else {
            p.setTotalPage(numbers / p.getPageSize() + 1);
        }
        p.setTotalRecord(numbers);

    }

    public void ChuKuCreate(int currentPage, int pageSize,int flag){
        p = new Page<>(currentPage, pageSize);
        int numbers;
        if(flag == 1){
            numbers = SomainDao.selectByType();//查找个
        }else if(flag == 2){
            numbers = SomainDao.selectByTypeOO();//查找个
        }else if(flag == 3){
            numbers = SomainDao.selectByTypeTT();//查找个
        }else {
            numbers = SomainDao.selectByTypeTF();//查找个
        }
        if (numbers % p.getPageSize() == 0) {  //计算页数
            p.setTotalPage(numbers / p.getPageSize());
        } else {
            p.setTotalPage(numbers / p.getPageSize() + 1);
        }
        p.setTotalRecord(numbers);
    }


    public void pClassCreat(int currentPage, int pageSize,String ID,String Name){
        p = new Page<>(currentPage, pageSize);
        int numbers ;
        if(ID == null && Name==null){ //判断搜索框有无数据
            numbers = CategoryDao.allCount();//查找个数
            if (numbers % p.getPageSize() == 0) {  //计算页数
                p.setTotalPage(numbers / p.getPageSize());
            } else {
                p.setTotalPage(numbers / p.getPageSize() + 1);
            }
            p.setTotalRecord(numbers);
        }else {
            numbers = CategoryDao.allKeyCount(ID,Name);//查找个数
            if (numbers % p.getPageSize() == 0) {  //计算页数
                p.setTotalPage(numbers / p.getPageSize());
            } else {
                p.setTotalPage(numbers / p.getPageSize() + 1);
            }
            p.setTotalRecord(numbers);
        }

    }

    public void productManagerCreat(int currentPage, int pageSize,String category,String date,String key){
        p = new Page<>(currentPage, pageSize);
        int numbers ;
        if(category == null && date==null && key == null){ //判断搜索框有无数据
            numbers = ProductDao.all();//查找个数
            System.out.println("条数："+numbers);
            if (numbers % p.getPageSize() == 0) {  //计算页数
                p.setTotalPage(numbers / p.getPageSize());
            } else {
                p.setTotalPage(numbers / p.getPageSize() + 1);
            }
            p.setTotalRecord(numbers);
        }else {
            numbers = ProductDao.selectCategoryDateKey(category,date,key);//查找个数
            if (numbers % p.getPageSize() == 0) {  //计算页数
                p.setTotalPage(numbers / p.getPageSize());
            } else {
                p.setTotalPage(numbers / p.getPageSize() + 1);
            }
            p.setTotalRecord(numbers);
        }

    }

    public void kuCunCreat(int currentPage, int pageSize,String key,int start,String end){
        p = new Page<>(currentPage, pageSize);
        int numbers ;
        if (end == null){ //判断搜索框有无数据
            numbers = ProductDao.kuCunSearchByKCN(key,start);//查找个数
            System.out.println(numbers);
            if (numbers % p.getPageSize() == 0) {  //计算页数
                p.setTotalPage(numbers / p.getPageSize());
            } else {
                p.setTotalPage(numbers / p.getPageSize() + 1);
            }
            p.setTotalRecord(numbers);
        }else {
            numbers = ProductDao.kuCunSearchByKCN(key,start,Integer.parseInt(end.trim()));//查找个数
            System.out.println(numbers);
            if (numbers % p.getPageSize() == 0) {  //计算页数
                p.setTotalPage(numbers / p.getPageSize());
            } else {
                p.setTotalPage(numbers / p.getPageSize() + 1);
            }
            p.setTotalRecord(numbers);

        }

    }

    public void RecordCreat(int currentPage,int pageSize,String ProductCode,String key,String date,String type){
        p = new Page<>(currentPage, pageSize);
        int numbers;
        if (key == null && date == null && type == null){ //判断搜索框有无数据
            numbers = StockrecordDao.RecordNum(ProductCode);//查找个数

            if (numbers % p.getPageSize() == 0) {  //计算页数
                p.setTotalPage(numbers / p.getPageSize());
            } else {
                p.setTotalPage(numbers / p.getPageSize() + 1);
            }
            p.setTotalRecord(numbers);
        }else {

            numbers = StockrecordDao.RecordDKT(ProductCode,date,key,type);//查找个数

            if (numbers % p.getPageSize() == 0) {  //计算页数
                p.setTotalPage(numbers / p.getPageSize());
            } else {
                p.setTotalPage(numbers / p.getPageSize() + 1);
            }
            p.setTotalRecord(numbers);
        }

    }

    public void RKBBCreat(int currentPage,int pageSize,String date){
        p = new Page<>(currentPage, pageSize);
        int numbers;
            numbers = StockrecordDao.RKRecord(date);//查找个数
            if (numbers % p.getPageSize() == 0) {  //计算页数
                p.setTotalPage(numbers / p.getPageSize());
            } else {
                p.setTotalPage(numbers / p.getPageSize() + 1);
            }
            p.setTotalRecord(numbers);

    }

    public void CKBBCreat(int currentPage,int pageSize,String date){
        p = new Page<>(currentPage, pageSize);
        int numbers;
        numbers = StockrecordDao.CKRecord(date);//查找个数
        if (numbers % p.getPageSize() == 0) {  //计算页数
            p.setTotalPage(numbers / p.getPageSize());
        } else {
            p.setTotalPage(numbers / p.getPageSize() + 1);
        }
        p.setTotalRecord(numbers);

    }


    public void KCBBCreate(int currentPage,int pageSize,String date){
        p = new Page<>(currentPage, pageSize);
        int numbers;
        numbers = ProductDao.all();//查找个数
        if (numbers % p.getPageSize() == 0) {  //计算页数
            p.setTotalPage(numbers / p.getPageSize());
        } else {
            p.setTotalPage(numbers / p.getPageSize() + 1);
        }
        p.setTotalRecord(numbers);
    }

    public Page<T> getP() {
        return p;
    }


}
