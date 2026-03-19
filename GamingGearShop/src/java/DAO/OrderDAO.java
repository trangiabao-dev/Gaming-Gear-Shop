package DAO;

import Model.Cart;
import Model.OrderDTO;
import Model.OrderDetailDTO;
import Model.ProductDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import utils.JPAUtils;

public class OrderDAO extends JPAGenericDAO<OrderDTO> {

    public OrderDAO() {
        super(OrderDTO.class);
    }

    public boolean checkOut(OrderDTO order, Cart cart) {
        EntityManager em = null;
        EntityTransaction trans = null;
        boolean check = false;

        try {
            em = JPAUtils.getEntityManager();
            trans = em.getTransaction();
            trans.begin();

            // Bước 1: Lưu Order
            em.persist(order);
            // Lệnh BẮT BUỘC ĐI LÀM: Đẩy xuống SQL ngay để lấy ID tự tăng
            em.flush();

            // Bước 2: Duyệt qua giỏ hàng
            for (ProductDTO item : cart.getCart().values()) {
                OrderDetailDTO detail = new OrderDetailDTO(0, item.getPrice(), item.getQuantity(), order.getOrderID(), item.getProductID());
                em.persist(detail);

                ProductDTO productInDB = em.find(ProductDTO.class, item.getProductID());
                if (productInDB != null) {
                    productInDB.setQuantity(productInDB.getQuantity() - item.getQuantity());
                    em.merge(productInDB);
                }
            }

            trans.commit();
            check = true;

        } catch (PersistenceException e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return check;
    }

    public boolean updateOrderStatus(int orderID, int newStatus) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = JPAUtils.getEntityManager();
            trans = em.getTransaction();
            OrderDTO order = em.find(OrderDTO.class, orderID);
            if (order != null) {
                trans.begin();
                order.setStatus(newStatus);
                em.merge(order);
                trans.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    // Lấy tất cả đơn hàng cho admin xem
    public List<OrderDTO> getAllOrders() {
        String jpql = "SELECT o FROM OrderDTO o ORDER BY o.orderID DESC";
        return super.query(jpql);
    }
}
