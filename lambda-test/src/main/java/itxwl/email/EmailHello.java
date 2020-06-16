package itxwl.email;
import com.sun.mail.util.MailSSLSocketFactory;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * @Auther: 薛
 * @Date: 2020/4/15 16:14
 * @Description:
 */
public class EmailHello {

        public static void main(String[] args) throws Exception {
            Properties prop = new Properties();
            prop.setProperty("mail.host", "smtp.qq.com"); //// 设置QQ邮件服务器
            prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
            prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码
            // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);
            //使用JavaMail发送邮件的5个步骤
            //创建定义整个应用程序所需的环境信息的 Session 对象
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮件用户名、授权码
                    return new PasswordAuthentication("2509647976@qq.com", "nvqtlvnkfuqfdjhf");
                }
            });
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、使用邮箱的用户名和授权码连上邮件服务器
            ts.connect("smtp.qq.com", "2509647976@qq.com", "nvqtlvnkfuqfdjhf");
            //4、创建邮件
            //创建邮件对象
            MimeMessage message = new MimeMessage(session);
            //指明邮件的发件人
            message.setFrom(new InternetAddress("2509647976@qq.com"));
            //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("2509647976@qq.com"));
            //邮件的标题
            message.setSubject("云平台监听异常日志信息推送!");
            //message.setFileName("就是一个测试邮件,莫在意");
            //邮件的文本内容
            message.setContent(
                    "<b>异常账号:admin</b><br>\n" +
                    "<b>所属服务:tfCloud-dye</b><br>\n" +
                    "<b>异常类型:debug</b><br>\n" +
                    "<b>请求类型:GET</b><br>\n" +
                    "<b>日志异常名称:org.mybatis.spring.MyBatisSystemException</b><br>\n" +
                    "<b>接口URL名称:/tenant/list</b><br>", "text/html;charset=UTF-8");
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }
}
