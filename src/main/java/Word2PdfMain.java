import java.util.List;

public class Word2PdfMain {



    public static void main(String[] args) {
        String path = "E:\\简历处理\\处理失败\\手机号读取失败\\";
        String path2 = "E:\\简历处理\\处理失败\\手机号读取失败\\";
        getAllfilename ga = new getAllfilename();
       List list = ga.getfilenames(path);

       for (int i=0;i<list.size();i++){
           Word2PdfUtil.word2pdf(path+list.get(i),path2+To(list.get(i).toString()));
       }
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
}
