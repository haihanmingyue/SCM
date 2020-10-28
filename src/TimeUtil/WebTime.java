package TimeUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WebTime {

    /**
     * 获取指定网站的日期时间
     *
     * @param webUrl
     * @return
     */
    private static String getWebsiteDatetime(String webUrl){
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
            return sdf.format(date);
        } catch (IOException e) {
            System.out.println("哎呀，网络好像出了问题");
        }
        return null;
    }

    public static String getNowTime(){
        String webUrl2 = "http://www.baidu.com";
        String time  = getWebsiteDatetime(webUrl2);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        if(time==null){
            System.out.println("使用系统本地时间");
            return sdf.format(d);
        }
        return getWebsiteDatetime(webUrl2);
    }

    public static String getComNowTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        System.out.println("使用系统本地时间");
        return sdf.format(d);
    }
    public static void main(String[] args) {
        String webUrl2 = "http://www.baidu.com";//百度
        String webUrl3 = "http://www.taobao.com";//淘宝
        String webUrl4 = "http://www.ntsc.ac.cn";//中国科学院国家授时中心
        String webUrl5 = "http://www.360.cn";//360
        System.out.println(getWebsiteDatetime(webUrl2) + " [百度]");
        System.out.println(getWebsiteDatetime(webUrl3) + " [淘宝]");
        System.out.println(getWebsiteDatetime(webUrl4) + " [中国科学院国家授时中心]");
        System.out.println(getWebsiteDatetime(webUrl5) + " [360安全卫士]");
    }

}
