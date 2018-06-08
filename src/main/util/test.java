


import java.io.*;
import java.security.Security;
import java.text.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

    public class test {

        public static void main(String[] args) {
            //设置SSL连接、邮件环境
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            Properties props = System.getProperties();
            props.setProperty("mail.pop3.host", "pop.163.com");
            props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.pop3.socketFactory.fallback", "false");
            props.setProperty("mail.pop3.port", "995");
            props.setProperty("mail.pop3.socketFactory.port", "995");
            props.setProperty("mail.pop3.auth", "true");
            props.setProperty("mail.mime.splitlongparameters","false");
//        props.put("mail.pop3.host", "smtp.qq.com");
//        props.put("mail.pop3.socketFactory.class", SSL_FACTORY);
//        props.put("mail.pop3.socketFactory.fallback", "false");
//        props.put("mail.pop3.port", "995");
//        props.put("mail.pop3.socketFactory.port", "995");
//        props.put("mail.pop3.auth", "true");
            //建立邮件会话
            Session session = Session.getDefaultInstance(props, null);
            //设置连接邮件仓库的环境
            URLName url = new URLName("pop3", "pop.163.com", 995, null, "13671081083@163.com", "456456000qazwsx");
            Store store = null;
            Folder inbox = null;
            try {
                //得到邮件仓库并连接
                store = session.getStore(url);
                store.connect();
                //得到收件箱并抓取邮件
                inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_ONLY);
                FetchProfile profile = new FetchProfile();
                profile.add(FetchProfile.Item.ENVELOPE);
                Message[] messages = inbox.getMessages();
                inbox.fetch(messages, profile);
                //打印收件箱邮件部分信息
                int length = messages.length;
                System.out.println("收件箱的邮件数：" + length);
                for (int i = 0;i<length;i++){



                    String from = MimeUtility.decodeText(messages[i].getFrom()[0].toString());
                    InternetAddress ia = new InternetAddress(from);
                    System.out.println("发件人：" + ia.getPersonal() + '(' + ia.getAddress() + ')');
                    System.out.println("主题：" + messages[i].getSubject());
                    System.out.println("邮件大小：" + messages[i].getSize());
                    System.out.println("邮件发送时间:" + new SimpleDateFormat("yyyy-MM-dd").format(messages[i].getSentDate()));
                    System.out.println("是否包含附件:" +isContainAttach((Part) messages[i]));

                    String qname = "主i题"+messages[i].getSubject()+"大i小"+messages[i].getSize()+"日i期"+new SimpleDateFormat("yyyy-MM-dd").format(messages[i].getSentDate());

                    if(isContainAttach((Part) messages[i])){
                        saveAttachMent((Part) messages[i],qname);
                    }
                    System.out.println("-------------------------------------------\n");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inbox.close(false);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                try {
                    store.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }




        public static boolean isContainAttach(Part part) throws Exception {


            boolean attachflag = false;
            String contentType = part.getContentType();
            if (part.isMimeType("multipart/*")) {
                Multipart mp = (Multipart) part.getContent();
                for (int i = 0; i < mp.getCount(); i++) {
                    BodyPart mpart = mp.getBodyPart(i);
                    String disposition = mpart.getDisposition();
                    if ((disposition != null)
                            && ((disposition.equals(Part.ATTACHMENT)) || (disposition
                            .equals(Part.INLINE))))
                        attachflag = true;
                    else if (mpart.isMimeType("multipart/*")) {
                        attachflag = isContainAttach((Part) mpart);
                    } else {
                        String contype = mpart.getContentType();
                        if (contype.toLowerCase().indexOf("application") != -1)
                            attachflag = true;
                        if (contype.toLowerCase().indexOf("name") != -1)
                            attachflag = true;
                    }
                }
            } else if (part.isMimeType("message/rfc822")) {
                attachflag = isContainAttach((Part) part.getContent());
            }
            return attachflag;


        }




        //保存简历附件

        public static void saveAttachMent(Part part,String qname) throws Exception {
            try{
                String fileName = "";
                if (part.isMimeType("multipart/*")) {
                    Multipart mp = (Multipart) part.getContent();
                    for (int i = 0; i < mp.getCount(); i++) {
                        BodyPart mpart = mp.getBodyPart(i);
                        String disposition = mpart.getDisposition();
                        if ((disposition != null)
                                && ((disposition.equals(Part.ATTACHMENT)))) {
                            fileName = mpart.getFileName();

                            // fileName = MimeUtility.decodeText(fileName);
                            fileName = MimeUtility.decodeText(fileName);
                            String[] tuoname = fileName.split("\\.");
                            qname = (qname +"随机数"+ (int)(Math.floor(Math.random() * 100 ))+ "."+tuoname[tuoname.length-1]).trim();


                            saveFile(qname, mpart.getInputStream());

                        }
                    }



                }

//        String fileName = "";
//        if (part.isMimeType("multipart/*")) {
//            Multipart mp = (Multipart) part.getContent();
//            for (int i = 0; i < mp.getCount(); i++) {
//                BodyPart mpart = mp.getBodyPart(i);
//                String disposition = mpart.getDisposition();
//                if ((disposition != null)
//                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition
//                        .equals(Part.INLINE)))) {
//                    fileName = mpart.getFileName();
//                    if (fileName.toLowerCase().indexOf("gb2312") != -1) {
//                        fileName = MimeUtility.decodeText(fileName);
//                    }
//                    saveFile(fileName, mpart.getInputStream());
//                } else if (mpart.isMimeType("multipart/*")) {
//                    saveAttachMent(mpart);
//                } else {
//                    fileName = mpart.getFileName();
//                    if ((fileName != null)
//                            && ((fileName.toLowerCase().indexOf("GB2312") != -1)||(fileName.toLowerCase().indexOf("gb18030") != -1))) {
//                        fileName = MimeUtility.decodeText(fileName);
//                        saveFile(fileName, mpart.getInputStream());
//                    }
//                }
//            }
//        } else if (part.isMimeType("message/rfc822")) {
//            saveAttachMent((Part) part.getContent());
//        }
            }catch (Exception exception){}
        }
//真正的保存附件

        public static void saveFile(String fileName, InputStream in) throws Exception {
            try {
                String osName = System.getProperty("os.name");
                String storedir = "";
                String separator = "";
                if (osName == null)
                    osName = "";
                if (osName.toLowerCase().indexOf("win") != -1) {
                    separator = "\\";
                    if (storedir == null || storedir.equals(""))
                        storedir = "E:\\简历处理\\待处理\\Boss$2018-5-18";
                } else {
                    separator = "/";
                    storedir = "/tmp";
                }
                fileName = MimeUtility.decodeText(fileName);
                File storefile = new File(storedir + separator + fileName);

                System.out.println("storefile's path: " + storefile.toString());
                // for(int i=0;storefile.exists();i++){
                // storefile = new File(storedir+separator+fileName+i);
                // }
                BufferedOutputStream bos = null;
                BufferedInputStream bis = null;
                try {
                    bos = new BufferedOutputStream(new FileOutputStream(storefile));
                    bis = new BufferedInputStream(in);
                    int c;
                    while ((c = bis.read()) != -1) {
                        bos.write(c);
                        bos.flush();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    throw new Exception("文件保存失败!");
                } finally {
                    bos.close();
                    bis.close();
                }
//        Workbook book = Workbook.getWorkbook(new File(storedir + separator + fileName));
//        Sheet[] sheets = book.getSheets();
//
//        Sheet sheet = sheets[0];
//        System.out.println("Sheet"+0);
//        int col = sheet.getColumns();
//        int row = sheet.getRows();
//        for(int r=3;r<row;r++){
//            //for(int c=1;c<col;c++){
////        得到第c列第r行的单元格
//            Cell cell = sheet.getCell(1, r);
//            String result = cell.getContents();
//            System.out.print("     "+result);

                // }
                // System.out.println();
            }catch (Exception exception){}
        }
        //    book.close();
    }




