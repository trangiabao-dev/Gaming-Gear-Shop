package DAO;

import Model.Cart;
import Model.OrderDTO;
import Model.OrderDetailDTO;
import Model.ProductDTO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import utils.JPAUtils;

public class OrderDAO extends JPAGenericDAO<OrderDTO> {

    public OrderDAO() {
        super(OrderDTO.class);
    }

    // 1. Hàm chính xử lý thanh toán giỏ hàng
    public boolean checkOut(OrderDTO order, Cart cart) {
        EntityManager em = null;
        EntityTransaction trans = null;
        boolean check = false;

        try {
            em = JPAUtils.getEntityManager();
            trans = em.getTransaction();
            trans.begin();

            // Bước 1: Lưu Order (JPA sẽ tự động lấy ID nếu cấu hình @GeneratedValue)
            em.persist(order);

            // Bước 2: Duyệt qua giỏ hàng để lưu OrderDetail và trừ tồn kho Product
            for (ProductDTO item : cart.getCart().values()) {
                
                // Lưu chi tiết đơn hàng
                OrderDetailDTO detail = new OrderDetailDTO(0, item.getPrice(), item.getQuantity(), order.getOrderID(), item.getProductID());
                em.persist(detail);

                // Lấy sản phẩm hiện tại từ DB và trừ số lượng
                ProductDTO productInDB = em.find(ProductDTO.class, item.getProductID());
                if (productInDB != null) {
                    productInDB.setQuantity(productInDB.getQuantity() - item.getQuantity());
                    em.merge(productInDB); // Cập nhật lại số lượng
                }
            }

            trans.commit(); // Chốt giao dịch thành công
            check = true;
            
        } catch (PersistenceException e) { 
            // 2. Xử lý lỗi: Bắt lỗi cụ thể của JPA thay vì Exception chung
            if (trans != null && trans.isActive()) {
                trans.rollback(); // Hủy toàn bộ nếu có lỗi
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return check;
    }
}