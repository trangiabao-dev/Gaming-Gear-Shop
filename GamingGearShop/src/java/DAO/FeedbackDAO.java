package DAO;

import Model.FeedbackDTO;
import java.util.List;

public class FeedbackDAO extends JPAGenericDAO<FeedbackDTO> {

    public FeedbackDAO() {
        super(FeedbackDTO.class);
    }
    
    public List<FeedbackDTO> getFeedbackByProductID(String productID) {
        if (!isValidString(productID)) {
            return null;
        }
        String jpql = "SELECT f FROM FeedbackDTO f WHERE f.productID = ?1";
        return super.query(jpql, productID);
    }

}
