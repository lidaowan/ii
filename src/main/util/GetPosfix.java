public class GetPosfix {

    public static String get(String allname){
        if(allname==null || "".equals(allname)){
            System.out.println("文件名为空");
            return null;
        }
        String[] s1 = allname.split("\\.");
        return s1[s1.length-1];
    }
}
