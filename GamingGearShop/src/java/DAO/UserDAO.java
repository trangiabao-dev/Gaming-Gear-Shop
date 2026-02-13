/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.dbutils;

/**
 *
 * @author thinh
 */
public class UserDAO {
    // 1. Khai báo hằng số SQL (Sửa lại cho đúng cú pháp)

    private static final String LOGIN = "SELECT fullName, roleID, address, phone, status "
            + "FROM tblUsers "
            + "WHERE userID = ? AND password = ?";

    // 2. Hàm checkLogin dùng hằng số ở trên
    public UserDTO checkLogin(String userID, String password) throws SQLException, ClassNotFoundException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = dbutils.getConnection();
            if (conn != null) {
                // Thay vì viết chuỗi dài, mình dùng biến LOGIN đã khai báo ở trên
                ptm = conn.prepareStatement(LOGIN);

                ptm.setString(1, userID);
                ptm.setString(2, password);

                rs = ptm.executeQuery();

                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    int roleID = rs.getInt("roleID");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    boolean status = rs.getBoolean("status");

                    user = new UserDTO(userID, fullName, "", roleID, address, phone, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

// Hàm kiểm tra xem userID đã tồn tại trong Database chưa

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = dbutils.getConnection(); // Lưu ý chữ DBUtils viết hoa đúng tên Class của bạn
            if (conn != null) {
                // Câu lệnh chỉ lấy ra userID để kiểm tra
                String sql = "SELECT userID FROM tblUsers WHERE userID=?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, userID);

                rs = ptm.executeQuery();

                // Nếu rs.next() trả về true -> Có tìm thấy dòng dữ liệu -> Đã tồn tại -> check = true
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
// Thêm khách hàng

    public boolean insert(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = dbutils.getConnection();
            if (conn != null) {
                // SỬA CÂU SQL: Thêm cột status và gán cứng là 1 (True) để tài khoản Active ngay
                String sql = "INSERT INTO tblUsers(userID, fullName, password, roleID, status, address, phone) "
                        + "VALUES(?,?,?,?,1,'','')";
                // Lưu ý: 1 là True. address và phone tạm thời để trống ('')

                ptm = conn.prepareStatement(sql);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getPassword());
                ptm.setInt(4, user.getRoleID()); // Lúc này roleID sẽ là "2"

                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
