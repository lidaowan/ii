import java.io.File;

public class MoveFile {

    public  static void moveFile(String source,String name,String mubiao){

        String startPath = source+"\\"+name;
       // String endPath = CONFIG.mubiao+"\\"+source;
        try {
            File startFile = new File(startPath);
            File tmpFile = new File(mubiao);//获取文件夹路径
            if(!tmpFile.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
                tmpFile.mkdirs();
            }
          //  System.out.println(endPath + startFile.getName());
            if (startFile.renameTo(new File(mubiao + "\\"+name))) {
                System.out.println("File is moved successful!");
             //   log.info("文件移动成功！文件名：《{}》 目标路径：{}",fileName,endPath);
            } else {
                System.err.println("File is failed to move!");
              //  log.info("文件移动失败！文件名：《{}》 起始路径：{}",fileName,startPath);
            }
        } catch (Exception e) {
           // log.info("文件移动异常！文件名：《{}》 起始路径：{}",fileName,startPath);
e.printStackTrace();
        }
    }
}
