

public class ReadFactoryImp implements ReadFactory {
    public Read getRead(String posfix) {

        if (posfix.endsWith("docx") ){
            return  (Read) SpringGetbean.getbean("readdocx");
        }
        else if(posfix.endsWith("doc") || posfix.endsWith("dot") ){
            return  (Read) SpringGetbean.getbean("readdoc");
        }
        else if(posfix.endsWith("pdf")){
            return  (Read) SpringGetbean.getbean("readpdf");
        }else if(posfix.endsWith("txt")){
            return  (Read) SpringGetbean.getbean("readtxt");
        }
        return null;
    }
}
