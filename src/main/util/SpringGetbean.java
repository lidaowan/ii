import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringGetbean {
    static ApplicationContext applicationContext;
    static {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");


    }

    public static Object getbean(String beanname){
        if(beanname!=null  && !"".equals(beanname)){
      return   applicationContext.getBean(beanname);}
      return null;

    }

}
