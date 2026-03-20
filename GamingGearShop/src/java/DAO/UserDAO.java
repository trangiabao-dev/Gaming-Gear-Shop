package DAO;

import Model.UserDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import utils.JPAUtils;
import utils.PasswordUtil;

public class UserDAO extends JPAGenericDAO<UserDTO> {

    public UserDAO() {
        super(UserDTO.class);
    }

    public UserDTO checkLogin(String userID, String password) {
        // Chỉ tìm theo userID trước
        String jpql = "SELECT u FROM UserDTO u WHERE u.userID = ?1 AND u.status = true";
        List<UserDTO> list = super.query(jpql, userID);

        if (list != null && !list.isEmpty()) {
            UserDTO user = list.get(0);
            // Dùng BCrypt so sánh password
            if (PasswordUtil.checkPassword(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    // Hàm kiểm tra trùng lặp ID tái sử dụng findById của cha
    public boolean checkDuplicate(String userID) {
        UserDTO user = super.findById(userID);
        return user != null; // Trả về true nếu đã tồn tại
    }

    public List<UserDTO> getAllUsers() {
        String jpql = "SELECT u FROM UserDTO u ORDER BY u.roleID ASC";
        return super.query(jpql);
    }

    // Tìm user theo email
    public UserDTO findByEmail(String email) {
        String jpql = "SELECT u FROM UserDTO u WHERE u.email = ?1 AND u.status = true";
        List<UserDTO> list = super.query(jpql, email);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

// Lưu OTP vào DB
    public boolean saveOTP(String email, String otp) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = JPAUtils.getEntityManager();
            UserDTO user = findByEmail(email);
            if (user == null) {
                return false;
            }
            trans = em.getTransaction();
            trans.begin();
            user.setOtp(otp);
            em.merge(user);
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

// Cập nhật mật khẩu mới
    public boolean resetPassword(String email, String newPassword) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = JPAUtils.getEntityManager();
            UserDTO user = findByEmail(email);
            if (user == null) {
                return false;
            }
            trans = em.getTransaction();
            trans.begin();
            user.setPassword(newPassword);
            user.setOtp(null);
            em.merge(user);
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

 
}
