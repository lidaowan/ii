import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class main {


    public static void main(String[] args) throws  Exception{


        getAllfilename gta = new getAllfilename();
      List list =  gta.getfilenames(CONFIG.Path);
      Ipinjie pj =Pinjiefactory.getpinjieMethod();
      pj.tiqu(list);

      // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
      //  applicationContext.getBean("J1");

    }

    public static void readAndWriterTest4() throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\111.docx");
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument xdoc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            String doc1 = extractor.getText();
            System.out.println(doc1);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
