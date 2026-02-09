/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
}
