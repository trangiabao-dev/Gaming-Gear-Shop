package DAO;

import Model.Cart;
import Model.OrderDTO;
import Model.ProductDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.dbutils;

public class OrderDAO {

    public boolean checkOut(OrderDTO order, Cart cart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = dbutils.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);

                // A. Lưu Hóa Đơn (tblOrders)
                String sqlOrder = "INSERT INTO tblOrders(orderDate, total, userID, status) VALUES(?,?,?,?)";
                pstm = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
                pstm.setDate(1, new Date(System.currentTimeMillis()));
                pstm.setDouble(2, order.getTotal());
                pstm.setString(3, order.getUserID());
                pstm.setInt(4, 1);
                pstm.executeUpdate();

                ResultSet rs = pstm.getGeneratedKeys();
                int orderID = 0;
                if (rs.next()) {
                    orderID = rs.getInt(1);
                }

                // B. Lưu Chi Tiết & Trừ Kho (SỬA Ở ĐÂY ĐỂ LƯU 4 CỘT MỚI)
                if (orderID > 0) {
                    // Thêm 4 dấu hỏi ? cho productName, fullName, phone, address
                    String sqlDetail = "INSERT INTO tblOrderDetails(price, quantity, orderID, productID, productName, fullName, phone, address) VALUES(?,?,?,?,?,?,?,?)";
                    String sqlUpdateProduct = "UPDATE tblProducts SET quantity = quantity - ? WHERE productID = ?";

                    PreparedStatement pstmDetail = conn.prepareStatement(sqlDetail);
                    PreparedStatement pstmUpdate = conn.prepareStatement(sqlUpdateProduct);

                    for (ProductDTO item : cart.getCart().values()) {
                        // 1. Lưu chi tiết đơn hàng
                        pstmDetail.setDouble(1, item.getPrice());
                        pstmDetail.setInt(2, item.getQuantity());
                        pstmDetail.setInt(3, orderID);
                        pstmDetail.setString(4, item.getProductID());

                        // Lấy thông tin từ đối tượng 'order' đã có fullName, phone, address từ Controller truyền sang
                        pstmDetail.setString(5, item.getProductName()); // Tên sản phẩm từ giỏ hàng
                        pstmDetail.setString(6, order.getFullName());    // Tên khách hàng từ OrderDTO
                        pstmDetail.setString(7, order.getPhone());       // Số điện thoại từ OrderDTO
                        pstmDetail.setString(8, order.getAddress());     // Địa chỉ từ OrderDTO

                        pstmDetail.executeUpdate();

                        // 2. Trừ số lượng tồn kho
                        pstmUpdate.setInt(1, item.getQuantity());
                        pstmUpdate.setString(2, item.getProductID());
                        pstmUpdate.executeUpdate();
                    }

                    pstmDetail.close();
                    pstmUpdate.close();

                    conn.commit();
                    conn.setAutoCommit(true);
                    check = true;
                }
            }
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
