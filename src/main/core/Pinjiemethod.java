import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pinjiemethod implements Ipinjie {

    public void tiqu(List<String> list) {
        Connection connection = C3p0pool.getConnection();
        List<PeopleBean> peopleBeans = new ArrayList<PeopleBean>();
        List list1 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {

            PeopleBean peopleBean = new PeopleBean();

            Read read = new ReadFactoryImp().getRead(list.get(i));

            String neirong = read.read(CONFIG.Path + "\\" + list.get(i));
            // System.out.println(neirong);
            if (neirong == null || "".equals(neirong)) {
                MoveFile.moveFile(CONFIG.Path, list.get(i), CONFIG.mubiaoJXSB);
                continue;
            }
            String phone = getPhone(neirong);
            if (phone == null || "".equals(phone)) {
                MoveFile.moveFile(CONFIG.Path, list.get(i), CONFIG.mubiaoPhone);
                continue;
            }
            String email = getEmail(neirong);
            if (email == null || "".equals(email)) {
                MoveFile.moveFile(CONFIG.Path, list.get(i), CONFIG.mubiaoEmail);
                // continue;
            }

            String s = list.get(i);
            // 主i题艾天星-应聘java工程师-期望薪资10k-15k-工作8年【Boss直聘】大i小67412日i期2018-05-15随机数2.0

            fengzhuangpeople(peopleBean, s, phone, email);
            list1.add(peopleBean);


            MoveFile.moveFileChenGong(CONFIG.Path, list.get(i), CONFIG.mubiaoChenGong);
        }
        Daoimp dimp = new Daoimp();
        dimp.intoshujuk(connection, list1);

    }

    private String getEmail(String neirong) {

        String reg = "[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(neirong);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public void fengzhuangpeople(PeopleBean peopleBean, String s, String phone, String email) {
        String reg = "主i题([\\s\\S]{1,20})-应聘";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            peopleBean.setName(matcher.group(1));
        }
        reg = "-应聘([\\s\\S]{1,20})-期望薪资";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(s);
        if (matcher.find()) {
            peopleBean.setHangye(matcher.group(1));
        }
        reg = "-期望薪资([\\s\\S]{1,20})-工作";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(s);
        if (matcher.find()) {
            peopleBean.setHopemoney(matcher.group(1));
        }
        reg = "-工作([\\s\\S]{1,20})年";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(s);
        if (matcher.find()) {
            peopleBean.setGongzuoage(Integer.parseInt(matcher.group(1)));
        }
        reg = "大i小([\\s\\S]{1,20})日i期";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(s);
        if (matcher.find()) {
            peopleBean.setJianlidaxiao(matcher.group(1));
        }
        reg = "日i期([\\s\\S]{1,20})随机数";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(s);
        if (matcher.find()) {
            peopleBean.setYouxiangjieshoushijian(matcher.group(1));
        }
        peopleBean.setPhone(phone);
        peopleBean.setEmail(email);

        peopleBean.setCunfangweizhi(CONFIG.Path + "\\" + s);

        // peopleBean.setJianli(neirong);

    }

    public String getPosfix(String s) {
        String s1[] = s.split("\\.");
        return s1[s1.length - 1];

    }

    public void neirongchazhao(PeopleBean peopleBean, String neirong) {

    }

    public String getPhone(String s) {
        if (s != null && !"".equals(s)) {
            String reg = "(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                return matcher.group();
            } else {
                reg = "(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])-\\d{4}-\\d{4}";
                pattern = Pattern.compile(reg);
                matcher = pattern.matcher(s);
                if (matcher.find()) {
                    return matcher.group();
                } else {
                    reg = "(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89]) \\d{4} \\d{4}";
                    pattern = Pattern.compile(reg);
                    matcher = pattern.matcher(s);
                    if (matcher.find()) {
                        return matcher.group();
                    } else {
                        CONFIG.getPhoneFaile++;
                        System.out.println("获取手机号失败次数：" + CONFIG.getPhoneFaile);
                        return null;
                    }
                }
            }
        } else return null;
    }
}
