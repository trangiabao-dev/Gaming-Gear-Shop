package utils;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class OTPUtil {

    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static boolean sendOTP(String toEmail, String otp) {
        String fromEmail = getConfig("mail.from");
        String password = getConfig("mail.password");

        if (fromEmail == null || password == null) {
            System.err.println("Không tìm thấy cấu hình mail!");
            return false;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            msg.setSubject("Ma OTP dat lai mat khau - Gaming Gear Shop");
            msg.setText(
                    "Xin chao!\n\n"
                    + "Ma OTP cua ban la: " + otp + "\n\n"
                    + "Ma nay co hieu luc trong 5 phut.\n"
                    + "Gaming Gear Shop"
            );
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getConfig(String key) {
        try {
            InitialContext ctx = new InitialContext();
            return (String) ctx.lookup("java:comp/env/" + key);
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
