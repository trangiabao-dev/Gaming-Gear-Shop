package DAO;

import Model.ProductDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDAO extends GenericDAO<ProductDTO> {

    @Override
    protected ProductDTO mapRow(ResultSet rs) {
        try {
            System.out.println("Dang map san pham: " + rs.getString("productID"));
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
        }
        return null;
    }

    // Lấy toàn bộ sản phẩm - query(String sql, Object... params)
    public List<ProductDTO> getAllProducts() {
        String sql = "SELECT * FROM tblProducts WHERE status = 1";
        return query(sql);
    }

    // Lọc sản phẩm theo mã catID - query()
    public List<ProductDTO> getByCategory(String catID) {
        String sql = "SELECT * FROM tblProducts WHERE status = 1 AND catID LIKE ?";
        return query(sql, catID.trim() + "%");
    }

    // Tìm sản phẩm theo Name - query()
    public List<ProductDTO> searchByName(String keyword) {
        String sql = "SELECT * FROM tblProducts WHERE status = 1 AND productName LIKE ?";
        return query(sql, "%" + keyword + "%");
    }
    
    // Lấy danh sách sản phẩm cho 1 trang - query()
    public List<ProductDTO> numberProductOnPage(int index) {
        int numberProductPage = 8;
        int offset = (index - 1) * numberProductPage;

        String sql = "SELECT * FROM tblProducts WHERE status = 1 ORDER BY productID OFFSET ? ROWS FETCH NEXT " + numberProductPage + " ROWS ONLY";
        // Sắp xếp theo ID, bỏ qua [offset] dòng, rồi lấy 8 dòng tiếp theo
        return query(sql, offset);
    }

    // Lấy toàn bộ Brand (thương hiệu) - query()
    public List<ProductDTO> getByBrand(String brandID) {
        String sql = "SELECT * FROM tblProducts WHERE status = 1 AND brandID LIKE ?";
        return query(sql, brandID);
    }

    // Lấy sản phẩm theo ID - query()
    public ProductDTO getProductByID(String productID) {
        String sql = "SELECT * FROM tblProducts WHERE status = 'True' AND productID LIKE ?";

        List<ProductDTO> list = query(sql, productID.trim() + "%");

        return list.isEmpty() ? null : list.get(0);
    }
    
    // Lọc theo giá
    public List<ProductDTO> getProductsByPriceRange(double min, double max){
        String sql = "SELECT * FROM tblProducts WHERE status = 1 AND price BETWEEN ? AND ?";
        return query(sql, min, max);
    }

    // Đếm tổng số lượng sản phẩm - getCount()
    public int getTotalProduct() {
        String sql = "SELECT COUNT(*) FROM tblProducts WHERE status = 1";
        return Count(sql);
    }
}
