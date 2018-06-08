public class Pinjiefactory {
    public static Ipinjie getpinjieMethod(){
        String s = CONFIG.Path;
        String[] s1 = s.split("\\\\");
       String[] s2 =  s1[s1.length-1].split("\\$");
       String s3 = s2[0];

       if(s3.toLowerCase().equals("boss")){
        return (Ipinjie)SpringGetbean.getbean("J1");
       }else {
           return null;
       }
    }
}
