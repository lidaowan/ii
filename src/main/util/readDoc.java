import org.apache.poi.hwpf.HWPFDocument;

import java.io.File;
import java.io.FileInputStream;

public class readDoc implements Read {
    public String read(String path) {

        File file = new File(path);
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            HWPFDocument doc = new HWPFDocument(fis);
            String doc1 = doc.getDocumentText();

            //  doc1 = isprint(doc1);
           // System.out.println(doc1);
//            StringBuilder doc2 = doc.getText();
//            System.out.println(doc2.toString());
//            Range rang = doc.getRange();
//            String doc3 = rang.text();
//            System.out.println(doc3);
            doc.close();
            fis.close();

            return doc1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(path);

        }
        return null;

    }
}
