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

    private ProductDTO mapProDTO(ResultSet rs) {
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
    
    // Tái sử dụng HÀM
    private List<ProductDTO> getProducts(String sql, Object... params) { // Object... params: có tham số hay không đều dược
        List<ProductDTO> list = new ArrayList<>();

        try( Connection conn = dbutils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {
            
            System.out.println("Chạy Lệnh SQL: " + sql); // Dòng để Debug
            
            if(params != null && params.length > 0){
                for(int i = 0; i < params.length; i++){
                    pst.setObject(i + 1, params[i]);
                }
            }
            
            try(ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    ProductDTO pDTO = mapProDTO(rs);
                    
                    if(pDTO != null){
                        list.add(pDTO);
                    }
                }
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Lấy toàn bộ sản phẩm
    public List<ProductDTO> getAllProducts(){
        String sql = "SELECT * FROM tblProducts WHERE status = 1";
        return getProducts(sql);
    }
    
    // Lọc sản phẩm theo mã catID
    public List<ProductDTO> getByCategory(String catID){
        String sql = "SELECT * FROM tblProducts WHERE status = 1 AND catID = ?";
        return getProducts(sql, catID);
    }
    
    // Tìm sản phẩm theo Name
    public List<ProductDTO> searchByName(String keyword){
        String sql = "SELECT * FROM tblProducts WHERE status = 1 AND productName LIKE ?";
        return getProducts(sql, "%" + keyword + "%");
    }
}
