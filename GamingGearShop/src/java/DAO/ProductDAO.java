package DAO;

import Model.ProductDTO;
import java.util.List;
import java.util.logging.Logger;

public class ProductDAO extends JPAGenericDAO<ProductDTO>{

    public ProductDAO() {
        super(ProductDTO.class);
    }
    
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    
    public List<ProductDTO> getAllProducts(){
        String jpql = "SELECT p FROM ProductDTO p";
        return super.query(jpql);
    }
    
    // Lấy sản phẩm theo ID
    public ProductDTO getProductByID(String id) {
        return super.findById(id);
    }
    
    public List<ProductDTO> searchByName(String keyword){
        if(keyword == null || keyword.trim().isEmpty()){
            return null;
        }        
        String jpql = "SELECT p FROM ProductDTO p WHERE p.productName LIKE ?1";
        return super.query(jpql, "%" + keyword.trim() + "%");
    }
    
    public boolean softDeleteProduct(String id){
        ProductDTO product = super.findById(id);
        if(product != null){
            product.setStatus(false);
            return super.update(product);
        }
        return false;
    }
    
    // Lọc sản phẩm theo mã catID
//    public List<ProductDTO> getByCategory(String catID) {
//        
//    }
//    
//    // Lấy danh sách sản phẩm cho 1 trang - query()
//    public List<ProductDTO> numberProductOnPage(int index) {
//        int numberProductPage = 8;
//        int offset = (index - 1) * numberProductPage;
//
//        String sql = "SELECT * FROM tblProducts WHERE status = 1 ORDER BY productID OFFSET ? ROWS FETCH NEXT " + numberProductPage + " ROWS ONLY";
//        // Sắp xếp theo ID, bỏ qua [offset] dòng, rồi lấy 8 dòng tiếp theo
//        return query(sql, offset);
//    }
//
//    // Lấy toàn bộ Brand (thương hiệu) - query()
//    public List<ProductDTO> getByBrand(String brandID) {
//        String sql = "SELECT * FROM tblProducts WHERE status = 1 AND brandID LIKE ?";
//        return query(sql, brandID);
//    }
//    
//    // Lọc theo giá
//    public List<ProductDTO> getProductsByPriceRange(double min, double max){
//        String sql = "SELECT * FROM tblProducts WHERE status = 1 AND price BETWEEN ? AND ?";
//        return query(sql, min, max);
//    }
//
//    // Đếm tổng số lượng sản phẩm - getCount()
//    public int getTotalProduct() {
//        String sql = "SELECT COUNT(*) FROM tblProducts WHERE status = 1";
//        return Count(sql);
//    }
}
