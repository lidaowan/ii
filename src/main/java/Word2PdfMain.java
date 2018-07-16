import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;

import java.io.File;
import java.util.List;

public class Word2PdfMain {

    static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。
    static final int wdFormatPDF = 17;// word转PDF 格式



    public static void main(String[] args) {
        ComThread.InitSTA();
        String path = "E:\\简历处理\\预处理\\";
        String path2 = "E:\\简历处理\\待处理\\Boss$2018-5-18\\";
        ActiveXComponent  app = new ActiveXComponent("Word.Application");
        app.setProperty("Visible", false);

        getAllfilename ga = new getAllfilename();
       List list = ga.getfilenames(path);

       for (int i=0;i<list.size();i++){
           word2pdf(path+list.get(i),path2+To(list.get(i).toString()),app);
       }
        ComThread.Release();
    }
public static String To(String yuan){
        if(yuan.endsWith("doc")){
          yuan =  yuan.replace(".doc",".pdf");
          return yuan;
        }else if(yuan.endsWith("docx")){
            yuan = yuan.replace(".docx",".pdf");
            return yuan;
    }else {
            return null;
        }

}



    public static boolean word2pdf(String source, String target,ActiveXComponent app) {
        System.out.println("Word转PDF开始启动...");
        // ComThread.InitSTA();
        long start = System.currentTimeMillis();

        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", false);
            Dispatch docs = app.getProperty("Documents").toDispatch();
            System.out.println("打开文档：" + source);
            Dispatch doc = Dispatch.call(docs, "Open", source, false, true).toDispatch();
            System.out.println("转换文档到PDF：" + target);
            File tofile = new File(target);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(doc, "SaveAs", target, wdFormatPDF);
            Dispatch.call(doc, "Close", false);
            long end = System.currentTimeMillis();
            System.out.println("转换完成，用时：" + (end - start) + "ms");
            return true;
        } catch (Exception e) {
            System.out.println("Word转PDF出错：" + e.getMessage());
            return false;
        } finally {

            if (app != null) {
                app.invoke("Quit", wdDoNotSaveChanges);
                //  ComThread.Release();
            }

        }
    }
}
