/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.FeedbackDTO;
import java.sql.ResultSet;
import java.util.List;

public class FeedbackDAO extends GenericDAO<FeedbackDTO> {

    @Override
    public FeedbackDTO mapRow(ResultSet rs) {
        try {
            return new FeedbackDTO(
                    rs.getInt("feedID"),
                    rs.getString("content"),
                    rs.getInt("rating"),
                    rs.getString("userID"),
                    rs.getString("productID"),
                    rs.getString("date")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<FeedbackDTO> getFeedbackByProductID(String productID){
        String sql = "SELECT * FROM tblFeedbacks WHERE productID = ? ORDER BY date DESC";
        return query(sql, productID);
    }

}
