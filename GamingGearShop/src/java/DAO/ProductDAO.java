package DAO;

import Model.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.dbutils;

public class ProductDAO {

    private ProductDTO mapDTO(ResultSet rs) {
        try {
            return new ProductDTO(
                    rs.getString("productID"),
                    rs.getString("productName"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getString("description"),
                    rs.getString("imageURL"),
                    rs.getBoolean("status"),
                    rs.getString("catID"),
                    rs.getString("brandID")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProductDTO> getAllProductDTO() {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblProducts";
        
        try ( Connection conn = dbutils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {

            try ( ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    ProductDTO checkMapProduct = mapDTO(rs);
                    
                    if(checkMapProduct != null){
                        list.add(checkMapProduct);
                    }                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();    // sẽ chỉ điểm chính xác dòng code nào gây ra lỗi
        }
        return list;
    }

}
