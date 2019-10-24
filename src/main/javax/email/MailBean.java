package email;

import java.util.Arrays;

/**
 *
 *
 * address - 地址,
 * to - 收件人,
 * cc - 抄送,
 * bcc - 密送,
 * subject - 主题,
 * content - 内容,
 * fileList - 附件
 *
 */
class MailBean {


    private String address;
    private String user;
    private String password;

    private String[] to;
    private String[] cc;
    private String[] bcc;

    private String subject;
    private String content;

    private String[] files;

    public MailBean(String address, String password, String subject) {
        this.address = address;
        this.password = password;
        this.subject = subject;
        // 默认用户名与邮箱名相同
        this.user = address;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }

    public String getAddress() {
        return address;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String[] getTo() {
        return to;
    }

    public String[] getCc() {
        return cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String[] getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return "MainBean{" +
                "address='" + address + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", to=" + Arrays.toString(to) +
                ", cc=" + Arrays.toString(cc) +
                ", bcc=" + Arrays.toString(bcc) +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}
