import org.apache.log4j.Logger;

public class Test {
    private Logger logger = Logger.getLogger(Test.class);

    public Test(){
        String str = "123a";
        try {
            int i = Integer.parseInt(str);
            logger.debug("succeed:"+str);
        }catch (Exception e){
            logger.debug("error:"+str);
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Test();
    }

}
