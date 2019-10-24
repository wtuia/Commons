package email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileInputStream;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

class SendMail {

    private static Properties properties;
    private static String TRANS_PORT;
    private static String HOST;
    private static String CHARSET;
    private static String ENCODING;
    private static String TYPE;


    private SendMail(){};

    private static void lodProperties() {
        if (properties == null) {
            String filePath = "src/main/javax/email/mail.properties";
            try (FileInputStream fis = new FileInputStream(filePath)){
                properties = new Properties();
                properties.load(fis);
                TRANS_PORT = properties.getProperty("session.transport");
                HOST = properties.getProperty("transport.host");
                TYPE = properties.getProperty("content.text.type");
                CHARSET = properties.getProperty("mail.file.charset");
                ENCODING = properties.getProperty("mail.file.encoding");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static void send(MailBean mail) {
        beanRequireNonNull(mail);
        lodProperties();
        // 建立会话
        Session session = Session.getInstance(properties);
        // 建立信息
        Message message = new MimeMessage(session);
        try {
            // 发件人地址
            message.setFrom(new InternetAddress(mail.getAddress()));
            // 创建发送，抄送，密送，用户
            create(message, mail.getTo(), Message.RecipientType.TO);
            create(message, mail.getCc(), Message.RecipientType.CC);
            create(message, mail.getBcc(), Message.RecipientType.BCC);
            // 设置正文内容
            BodyPart bodyPart = setMailContentBody(message, mail);
            // 邮件正文框(包括附件)
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            // 设置附件
            addFile(mail.getFiles(), multipart);
            message.setContent(multipart);
            // 服务器验证 连接
            Transport transport = session.getTransport(TRANS_PORT);
            transport.connect(HOST, mail.getUser(), (mail.getPassword()));
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static void beanRequireNonNull(MailBean mailBean) {
        Objects.requireNonNull(mailBean.getTo(),"收件人不能为空");
        Objects.requireNonNull(mailBean.getPassword(), "密码不能为空");
    }

    // 创建发送用户
    private static void create(Message message, String[] send, Message.RecipientType type)
            throws MessagingException {
        if (send != null) {
            String sends = getMails(send);
            Address[] address = InternetAddress.parse(sends);
            message.setRecipients(type, address);
        }
    }

    // 读取用户
    private static String getMails(String[] mails) {
        StringBuilder sb = new StringBuilder();
        int len = mails.length;
        for (String str : mails) {
            sb.append(str).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private static BodyPart setMailContentBody(Message message, MailBean mailBean) {
        // 邮件组成
        BodyPart bodyPart = new MimeBodyPart();
        try {
            // 发送日期
            message.setSentDate(Date.from(Instant.now()));
            // 发送主题
            message.setSubject(mailBean.getSubject());
            // 发送内容，邮件正文内容
            message.setText(mailBean.getContent());
            bodyPart.setContent(mailBean.getContent(), TYPE);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return bodyPart;
    }

    // 添加附件
    private static void addFile(String[] fileList, Multipart multipart) {
        if (fileList == null || fileList.length <= 0) {
            return;
        }
        try {
            for (String fileName : fileList) {
                BodyPart mimeBodyPart = new MimeBodyPart();
                FileDataSource fileDataSource = new FileDataSource(fileName);
                mimeBodyPart.setDataHandler(new DataHandler(fileDataSource));
                mimeBodyPart.setFileName(
                        MimeUtility.encodeText(fileDataSource.getName(), CHARSET, ENCODING));
                multipart.addBodyPart(mimeBodyPart);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
