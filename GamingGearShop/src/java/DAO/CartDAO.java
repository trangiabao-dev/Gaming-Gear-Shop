package DAO;

import Model.CartDTO;
import Model.ProductDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import utils.JPAUtils;

public class CartDAO extends JPAGenericDAO<CartDTO> {

    public CartDAO() {
        super(CartDTO.class);
    }

    public List<CartDTO> getCartByUser(String userID) {
        String jpql = "SELECT c FROM CartDTO c WHERE c.userID = ?1";
        List<CartDTO> list = super.query(jpql, userID);

        if (list == null) {
            return null;
        }

        ProductDAO pDAO = new ProductDAO();
        for (CartDTO item : list) {
            ProductDTO p = pDAO.getProductByID(item.getProductID());
            if (p != null) {
                item.setProductName(p.getProductName());
                item.setPrice(p.getPrice());
            }
        }
        return list;
    }

    // Thêm hoặc cộng dồn số lượng nếu sản phẩm đã có
    public boolean addToCart(String userID, String productID, int quantity) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = JPAUtils.getEntityManager();
            trans = em.getTransaction();

            String jpql = "SELECT c FROM CartDTO c WHERE c.userID = ?1 AND c.productID = ?2";
            List<CartDTO> list = em.createQuery(jpql, CartDTO.class)
                    .setParameter(1, userID)
                    .setParameter(2, productID)
                    .getResultList();

            trans.begin();
            if (list != null && !list.isEmpty()) {
                // Đã có → cộng dồn số lượng
                CartDTO existing = list.get(0);
                existing.setQuantity(existing.getQuantity() + quantity);
                em.merge(existing);
            } else {
                // Chưa có → thêm mới
                em.persist(new CartDTO(userID, productID, quantity));
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

    // Xóa 1 sản phẩm khỏi giỏ
    public boolean removeFromCart(String userID, String productID) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = JPAUtils.getEntityManager();

            String jpql = "SELECT c FROM CartDTO c WHERE c.userID = ?1 AND c.productID = ?2";
            List<CartDTO> list = em.createQuery(jpql, CartDTO.class)
                    .setParameter(1, userID)
                    .setParameter(2, productID)
                    .getResultList();

            if (list != null && !list.isEmpty()) {
                trans = em.getTransaction();
                trans.begin();
                em.remove(em.merge(list.get(0)));
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

    // Xóa toàn bộ giỏ sau khi checkout
    public boolean clearCart(String userID) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = JPAUtils.getEntityManager();
            trans = em.getTransaction();
            trans.begin();
            em.createQuery("DELETE FROM CartDTO c WHERE c.userID = ?1")
                    .setParameter(1, userID)
                    .executeUpdate();
            trans.commit();
            return true;

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

    // Đếm số loại sản phẩm trong giỏ (hiển thị badge navbar)
    public int countCartItems(String userID) {
        EntityManager em = null;
        try {
            em = JPAUtils.getEntityManager();
            Long count = (Long) em.createQuery(
                    "SELECT COUNT(c) FROM CartDTO c WHERE c.userID = ?1")
                    .setParameter(1, userID)
                    .getSingleResult();
            return count.intValue();

        } catch (Exception e) {
            return 0;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
