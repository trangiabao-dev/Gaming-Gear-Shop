package DAO;

import Model.UserDTO;
import java.util.List;

public class UserDAO extends JPAGenericDAO<UserDTO> {

    public UserDAO() {
        super(UserDTO.class);
    }

    public UserDTO checkLogin(String userID, String password) {
        String jpql = "SELECT u FROM UserDTO u WHERE u.userID = ?1 AND u.password = ?2 AND u.status = true";
        
        List<UserDTO> list = super.query(jpql, userID, password);
        
        // Nếu danh sách trả về có dữ liệu, lấy user đầu tiên
        if (list != null && !list.isEmpty()) {
            return list.get(0);
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
}