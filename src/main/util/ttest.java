

import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ttest {

    public static void main(String[] args) {
String s = "java工程师-期望薪资10k-15k-工作8年【Boss直聘】大i小67412日i期2018-05-15随机数2.0";
int t = s.indexOf("-应聘");
s = s.substring(t+1,s.length());
System.out.println(s);

    }




}
