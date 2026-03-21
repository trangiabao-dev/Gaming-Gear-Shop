package DAO;

import Model.FeedbackDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import utils.JPAUtils;

public class FeedbackDAO extends JPAGenericDAO<FeedbackDTO> {

    public FeedbackDAO() {
        super(FeedbackDTO.class);
    }

    public List<FeedbackDTO> getFeedbackByProductID(String productID) {
        if (!isValidString(productID)) return null;
        String jpql = "SELECT f FROM FeedbackDTO f WHERE f.productID = ?1 ORDER BY f.feedID DESC";
        return super.query(jpql, productID);
    }

    public boolean hasPurchased(String userID, String productID) {
        EntityManager em = null;
        try {
            em = JPAUtils.getEntityManager();
            String jpql = "SELECT COUNT(d) FROM OrderDetailDTO d JOIN OrderDTO o ON d.orderID = o.orderID "
                        + "WHERE o.userID = ?1 AND d.productID = ?2 AND o.status != 0";
            Long count = (Long) em.createQuery(jpql)
                    .setParameter(1, userID)
                    .setParameter(2, productID)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    public boolean addFeedback(String userID, String productID, String content, int rating) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = JPAUtils.getEntityManager();
            trans = em.getTransaction();

            // Lấy ngày hiện tại
            String today = new java.text.SimpleDateFormat("yyyy-MM-dd")
                    .format(new java.util.Date());

            FeedbackDTO fb = new FeedbackDTO();
            fb.setUserID(userID);
            fb.setProductID(productID);
            fb.setContent(content);
            fb.setRating(rating);
            fb.setDate(today);

            trans.begin();
            em.persist(fb);
            trans.commit();
            return true;
        } catch (Exception e) {
            if (trans != null && trans.isActive()) trans.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    // Admin xóa feedback
    public boolean deleteFeedback(int feedID) {
        return super.remove(feedID);
    }

    // Lấy tất cả feedback cho Admin
    public List<FeedbackDTO> getAllFeedbacks() {
        String jpql = "SELECT f FROM FeedbackDTO f ORDER BY f.feedID DESC";
        return super.query(jpql);
    }
}