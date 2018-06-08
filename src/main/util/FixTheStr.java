import org.springframework.util.StringUtils;

public class FixTheStr {

    public static String fix(String s){


        if(StringUtils.isEmpty(s)){

            return"";
        }

        byte[] bts =s.getBytes();
        int btsLength= bts.length;
        byte[] newBytes = new byte[btsLength];
        for(int i =0;i<btsLength;i++) {

            byte b =bts[i];
            if((b >=0&& b <=31) || b >=127){
//将不可打印字符至空
                b =32;
            }

            newBytes[i]=b;
        }

        return new String(newBytes);
    }
}
