package email;

class MailMain {

    public static void main(String[] args) {
        String[] to = {"1424471149@qq.com"};
        String subect = "TES";
        String content = "TEST";
        String addr = "473666238@qq.com";
        String pass = "nmxjniemkzprbhgh";
        MailBean bean = new MailBean(addr, pass, subect);
        bean.setContent(content);
        bean.setTo(to);
        SendMail.send(bean);
    }
}
