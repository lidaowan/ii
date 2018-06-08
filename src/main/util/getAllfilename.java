

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class getAllfilename {
   private List<String> list = new ArrayList<String>();
    public List<String> getfilenames(String path){

        File file = new File(path);
   String[] s = file.list();
   if(s==null || s.length==0){
       System.out.println("路径不存在文件");
       return  null;
   }
   for(int i=0;i<s.length;i++){
       list.add(s[i]);
   }
        return list;
    }
}
