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
    
    // Tái sử dụng HÀM, trả về danh sách sản phẩm 
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
    
    // Tái sử dụng HÀM, CHUYỂN ĐỂ ĐẾM
    private int getCount(String sql, Object... params){
        
        try(Connection conn = dbutils.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)){
            if(params != null){
                for(int i = 0; i < params.length; i++){
                    pst.setObject(i + 1, params[i]);
                }
            }
            try(ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    
    // Lấy toàn bộ sản phẩm - getProducts()
    public List<ProductDTO> getAllProducts(){
        String sql = "SELECT * FROM tblProducts WHERE status = 1";
        return getProducts(sql);
    }
    
    // Lọc sản phẩm theo mã catID - getProducts()
    public List<ProductDTO> getByCategory(String catID){
        String sql = "SELECT * FROM tblProducts WHERE status = 1 AND catID = ?";
        return getProducts(sql, catID);
    }
    
    // Tìm sản phẩm theo Name - getProducts()
    public List<ProductDTO> searchByName(String keyword){
        String sql = "SELECT * FROM tblProducts WHERE status = 1 AND productName LIKE ?";
        return getProducts(sql, "%" + keyword + "%");
    }
    
    // Đếm tổng số lượng sản phẩm - getCount()
    public int getTotalProduct(){
        String sql = "SELECT COUNT(*) FROM tblProducts WHERE status = 1";
        return getCount(sql);
    }
    
    // Lấy danh sách sản phẩm cho 1 trang
    public List<ProductDTO> numberProductOnPage(int index){
        int numberProductPage = 8;
        int offset = (index - 1) * numberProductPage;
        // "Bỏ qua 8 sản phẩm đầu tiên" (vì 8 cái này đã hiện ở trang 1 rồi).
        // Database sẽ bắt đầu lấy từ sản phẩm thứ 9 đến thứ 16.
        
        String sql = "SELECT * FROM tblProducts WHERE status = 1 ORDER BY productID OFFSET ? ROWS FETCH NEXT " + numberProductPage + " ROWS ONLY";
        // Sắp xếp theo ID, bỏ qua [offset] dòng, rồi lấy 8 dòng tiếp theo
        return getProducts(sql, offset);
    }
}
