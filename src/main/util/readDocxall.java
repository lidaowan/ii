
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;

public class readDocxall implements Read {


    public String read(String path) {
        File file = new File(path);
        String str = "";
        FileInputStream fis;
        try {
             fis = new FileInputStream(file);
            XWPFDocument xdoc = new XWPFDocument(fis);

            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            String doc1 = extractor.getText();
         //   System.out.println(doc1);
            xdoc.close();
            fis.close();
            return doc1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
