package DAO;

import Model.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thinh
 */
public class UserDAO {
//    // 1. Khai báo hằng số SQL (Sửa lại cho đúng cú pháp)
//
//    private static final String LOGIN = "SELECT fullName, roleID, address, phone, status "
//            + "FROM tblUsers "
//            + "WHERE userID = ? AND password = ?";
//
//    // 2. Hàm checkLogin dùng hằng số ở trên
//    public UserDTO checkLogin(String userID, String password) throws SQLException, ClassNotFoundException {
//        UserDTO user = null;
//        Connection conn = null;
//        PreparedStatement pstm = null;
//        ResultSet rs = null;
//
//        try {
//            conn = dbutils.getConnection();
//            if (conn != null) {
//                // Thay vì viết chuỗi dài, mình dùng biến LOGIN đã khai báo ở trên
//                pstm = conn.prepareStatement(LOGIN);
//
//                pstm.setString(1, userID);
//                pstm.setString(2, password);
//
//                rs = pstm.executeQuery();
//
//                if (rs.next()) {
//                    String fullName = rs.getString("fullName");
//                    int roleID = rs.getInt("roleID");
//                    String address = rs.getString("address");
//                    String phone = rs.getString("phone");
//                    boolean status = rs.getBoolean("status");
//
//                    user = new UserDTO(userID, fullName, "", roleID, address, phone, status);
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (pstm != null) {
//                pstm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return user;
//    }
//
//// Hàm kiểm tra xem userID đã tồn tại trong Database chưa
//    public boolean checkDuplicate(String userID) throws SQLException {
//        boolean check = false;
//        Connection conn = null;
//        PreparedStatement pstm = null;
//        ResultSet rs = null;
//        try {
//            conn = dbutils.getConnection(); // Lưu ý chữ DBUtils viết hoa đúng tên Class của bạn
//            if (conn != null) {
//                // Câu lệnh chỉ lấy ra userID để kiểm tra
//                String sql = "SELECT userID FROM tblUsers WHERE userID=?";
//                pstm = conn.prepareStatement(sql);
//                pstm.setString(1, userID);
//
//                rs = pstm.executeQuery();
//
//                // Nếu rs.next() trả về true -> Có tìm thấy dòng dữ liệu -> Đã tồn tại -> check = true
//                if (rs.next()) {
//                    check = true;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (pstm != null) {
//                pstm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return check;
//    }
//// Thêm khách hàng
//
//    public boolean insert(UserDTO user) throws SQLException {
//        boolean check = false;
//        Connection conn = null;
//        PreparedStatement pstm = null;
//        try {
//            conn = dbutils.getConnection();
//            if (conn != null) {
//                // SỬA CÂU SQL: Thêm cột status và gán cứng là 1 (True) để tài khoản Active ngay
//                String sql = "INSERT INTO tblUsers(userID, fullName, password, roleID, status, address, phone) "
//                        + "VALUES(?,?,?,?,1,?,?)";
//                // Lưu ý: 1 là True. address và phone tạm thời để trống ('')
//
//                pstm = conn.prepareStatement(sql);
//                pstm.setString(1, user.getUserID());
//                pstm.setString(2, user.getFullName());
//                pstm.setString(3, user.getPassword());
//                pstm.setInt(4, user.getRoleID()); // Lúc này roleID sẽ là "2"
//                pstm.setString(5, user.getAddress()); // Nạp địa chỉ vào dấu ? thứ 5
//                pstm.setString(6, user.getPhone());
//
//                check = pstm.executeUpdate() > 0;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (pstm != null) {
//                pstm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return check;
//    }
}
