package DAO;

import Model.CartDTO;
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

    public boolean checkOutFromDB(OrderDTO order, List<CartDTO> cartItems) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = JPAUtils.getEntityManager();
            trans = em.getTransaction();
            trans.begin();

            em.persist(order);
            em.flush();

            for (CartDTO item : cartItems) {
                OrderDetailDTO detail = new OrderDetailDTO(
                        0, item.getPrice(), item.getQuantity(),
                        order.getOrderID(), item.getProductID()
                );
                em.persist(detail);

                // Trừ số lượng tồn kho
                ProductDTO product = em.find(ProductDTO.class, item.getProductID());
                if (product != null) {
                    product.setQuantity(product.getQuantity() - item.getQuantity());
                    em.merge(product);
                }
            }

            trans.commit();
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    // Lấy danh sách đơn hàng theo userID

    public List<OrderDTO> getOrdersByUser(String userID) {
        String jpql = "SELECT o FROM OrderDTO o WHERE o.userID = ?1 ORDER BY o.orderID DESC";
        return super.query(jpql, userID);
    }

    // Lấy chi tiết từng đơn (danh sách sản phẩm trong đơn)
    public List<OrderDetailDTO> getDetailsByOrderID(int orderID) {
        EntityManager em = null;
        try {
            em = JPAUtils.getEntityManager();
            String jpql = "SELECT o FROM OrderDetailDTO o WHERE o.orderID = ?1";
            return em.createQuery(jpql, OrderDetailDTO.class)
                    .setParameter(1, orderID)
                    .getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    // Hủy đơn hàng (chỉ cho hủy khi status = 1 - đang xử lý)
    public boolean cancelOrder(int orderID, String userID) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = JPAUtils.getEntityManager();
            trans = em.getTransaction();

            OrderDTO order = em.find(OrderDTO.class, orderID);
            // Kiểm tra: đơn phải của đúng user VÀ chưa giao
            if (order != null && order.getUserID().equals(userID) && order.getStatus() == 1) {
                trans.begin();
                order.setStatus(0); // 0 = Đã hủy
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
