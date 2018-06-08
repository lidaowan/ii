import java.io.*;

public class test2 {
    public static void main(String[] args) {
        while (true) {
            String encoding = "gbk";
            File file = new File("E:\\qq.txt");
            Long filelength = file.length();
            byte[] filecontent = new byte[filelength.intValue()];
            try {
                FileInputStream in = new FileInputStream(file);
                in.read(filecontent);
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("文件被占用");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("文件被占用");
            }
            try {
                System.out.println(new String(filecontent, encoding));
                //   return new String(filecontent, encoding);
            } catch (UnsupportedEncodingException e) {
                System.err.println("The OS does not support " + encoding);
                e.printStackTrace();
                //   return null;
            }
        }
    }
}