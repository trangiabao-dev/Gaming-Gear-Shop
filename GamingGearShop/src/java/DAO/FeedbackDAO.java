/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
