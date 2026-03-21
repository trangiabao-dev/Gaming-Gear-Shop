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

public class OTPUtil {

    // Hardcode thẳng — không dùng context.xml nữa
    private static final String FROM_EMAIL = "gearshopgaming@gmail.com";
    private static final String PASSWORD   = "unfb dtqz eoja clhn";

    // Tạo OTP 6 số
    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Gửi OTP quên mật khẩu
    public static boolean sendOTP(String toEmail, String otp) {
        try {
            Session session = createSession();
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM_EMAIL));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("Ma OTP dat lai mat khau - Gaming Gear Shop");
            msg.setText(
                "Xin chao!\n\n" +
                "Ma OTP cua ban la: " + otp + "\n\n" +
                "Ma nay co hieu luc trong 5 phut.\n" +
                "Gaming Gear Shop"
            );
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Gửi email xác nhận đơn hàng
    public static boolean sendOrderConfirmation(String toEmail, String fullName, int orderID, double total) {
        try {
            Session session = createSession();
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM_EMAIL));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("Xac nhan don hang #" + orderID + " - Gaming Gear Shop");
            msg.setText(
                "Xin chao " + fullName + "!\n\n" +
                "Don hang #" + orderID + " cua ban da duoc dat thanh cong.\n" +
                "Tong tien: " + String.format("%,.0f", total) + " VND\n\n" +
                "Chung toi se xu ly va giao hang som nhat.\n" +
                "Cam on ban da mua sam tai Gaming Gear Shop!\n\n" +
                "Gaming Gear Shop"
            );
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Dùng chung cho cả 2 method
    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });
    }
}