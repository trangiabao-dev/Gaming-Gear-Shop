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

/**
 *
 * @author thinh
 */
public class OrderDAO {
//
//    public boolean checkOut(OrderDTO order, Cart cart) throws SQLException {
//        boolean check = false;
//        Connection conn = null;
//        PreparedStatement pstm = null;
//
//        try {
//            conn = dbutils.getConnection();
//
//            if (conn != null) {
//                // 1. TẮT CHẾ ĐỘ TỰ ĐỘNG LƯU ĐỂ QUẢN LÝ GIAO DỊCH (TRANSACTION)
//                conn.setAutoCommit(false);
//
//                // ----------------------------------------------------
//                // BƯỚC A: Lưu Hóa Đơn (tblOrders)
//                // ----------------------------------------------------
//                String sqlOrder = "INSERT INTO tblOrders(orderDate, total, userID, status) VALUES(?,?,?,?)";
//                pstm = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
//                // Lấy ngày hiện tại
//                pstm.setDate(1, new Date(System.currentTimeMillis()));
//                pstm.setDouble(2, order.getTotal());
//                pstm.setString(3, order.getUserID());
//                pstm.setInt(4, 1); // Status 1: Mới đặt hàng
//                pstm.executeUpdate();
//
//                // Lấy OrderID vừa sinh ra
//                ResultSet rs = pstm.getGeneratedKeys();
//                int orderID = 0;
//                if (rs.next()) {
//                    orderID = rs.getInt(1);
//                }
//
//                // ----------------------------------------------------
//                // BƯỚC B: Lưu Chi Tiết & Trừ Kho (tblOrderDetails & tblProducts)
//                // ----------------------------------------------------
//                if (orderID > 0) {
//                    String sqlDetail = "INSERT INTO tblOrderDetails(price, quantity, orderID, productID) VALUES(?,?,?,?)";
//                    String sqlUpdateProduct = "UPDATE tblProducts SET quantity = quantity - ? WHERE productID = ?";
//
//                    PreparedStatement pstmDetail = conn.prepareStatement(sqlDetail);
//                    PreparedStatement pstmUpdate = conn.prepareStatement(sqlUpdateProduct);
//
//                    for (ProductDTO item : cart.getCart().values()) {
//                        // 1. Lưu chi tiết đơn hàng
//                        pstmDetail.setDouble(1, item.getPrice());
//                        pstmDetail.setInt(2, item.getQuantity());
//                        pstmDetail.setInt(3, orderID);
//                        pstmDetail.setString(4, item.getProductID());
//                        pstmDetail.executeUpdate();
//
//                        // 2. Trừ số lượng tồn kho (QUAN TRỌNG)
//                        pstmUpdate.setInt(1, item.getQuantity());
//                        pstmUpdate.setString(2, item.getProductID());
//                        pstmUpdate.executeUpdate();
//                    }
//
//                    // Đóng các pstm con
//                    pstmDetail.close();
//                    pstmUpdate.close();
//
//                    // 2. CHỐT ĐƠN (COMMIT) - Lưu tất cả xuống DB
//                    conn.commit();
//                    conn.setAutoCommit(true); // Bật lại chế độ mặc định
//                    check = true;
//                }
//            }
//        } catch (Exception e) {
//            // 3. CÓ LỖI THÌ HỦY HẾT (ROLLBACK)
//            if (conn != null) {
//                try {
//                    conn.rollback();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
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
