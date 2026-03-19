package DAO;

import Model.ProductDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import utils.JPAUtils;

public class ProductDAO extends JPAGenericDAO<ProductDTO>{
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    
    public ProductDAO() {
        super(ProductDTO.class);
    }
    
    // Dành cho KHÁCH HÀNG
    public List<ProductDTO> getAllProducts(){
        String jpql = "SELECT p FROM ProductDTO p WHERE p.status = true";
        return super.query(jpql);
    }
    
    // Dành cho ADMIN
    public List<ProductDTO> getAllProductsForAdmin() {
        String jpql = "SELECT p FROM ProductDTO p ORDER BY p.status DESC, p.productID ASC";
        return super.query(jpql);
    }
    
    public ProductDTO getProductByID(String id){
        if(!isValidString(id)){
            return null;
        }
        return super.findById(id.trim());
    }
    
    public List<ProductDTO> searchByName(String keyword){
        if(!isValidString(keyword)){
            return null;
        }
        String jpql = "SELECT p FROM ProductDTO p WHERE p.status = true AND p.productName LIKE ?1";
        return super.query(jpql, "%" + keyword.trim() + "%");
    }
    
    public boolean softDeleteProduct(String id){
        if(!isValidString(id)){
            return false;
        }
        ProductDTO product = super.findById(id.trim());
        if(product != null){
            product.setStatus(false);
            return super.update(product);
        }
        return false;
    }
    
    public List<ProductDTO> getByCategory(String catID){
        if(!isValidString(catID)){
            return null;
        }
        String jpql = "SELECT p FROM ProductDTO p WHERE p.status = true AND p.catID = ?1";
        return super.query(jpql, catID.trim());
    }
    
    public List<ProductDTO> getByBrand(String brandID){
        if(!isValidString(brandID)){
            return null;
        }
        String jpql = "SELECT p FROM ProductDTO p WHERE p.status = true AND p.brandID = ?1";
        return super.query(jpql, brandID.trim());
    }
    
    public List<ProductDTO> getProductsByPriceRange(double min, double max){
        if(min < 0){
            min = 0;
        }
        String jpql = "SELECT p FROM ProductDTO p WHERE p.status = true AND p.price BETWEEN ?1 AND ?2";
        return super.query(jpql, min, max);
    }
    
     // Khôi phục sản phẩm đã ẩn — ngược lại với softDelete
    public boolean restoreProduct(String id) {
        if (!isValidString(id)) {
            return false;
        }
        ProductDTO product = super.findById(id.trim());
        if (product != null) {
            product.setStatus(true);
            return super.update(product);
        }
        return false;
    }
    
    // Nhóm hàm tùy chỉnh theo yêu cầu, trước khi lấy dữ liệu SQL trả về
    public int getCountProduct(){
        EntityManager em = null;
        try{
            em = JPAUtils.getEntityManager();
            String jpql = "SELECT COUNT(p) FROM ProductDTO p WHERE p.status = true";
            
            Long count = (Long) em.createQuery(jpql).getSingleResult();
            return count.intValue();
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "Lỗi khi đếm tổng sản phẩm", e);
            return 0;
        }finally{
            if(em != null && em.isOpen()){
                em.close();
            }
        }
    }
    
    public List<ProductDTO> numberProductOnPage(int index) {
        if(index < 1){
            index = 1;
        }
        
        int numberProductPage = 8;
        int offset = (index - 1) * numberProductPage;
        
        EntityManager em = null;
        try{
            em = JPAUtils.getEntityManager();
            String jpql = "SELECT p FROM ProductDTO p WHERE p.status = true ORDER BY p.productID ASC";
            
            // TypedQuery: tạm giữ câu truy vấn trước khi đưa xuống Database chạy
            TypedQuery<ProductDTO> q = em.createQuery(jpql, ProductDTO.class);
            q.setFirstResult(offset);
            q.setMaxResults(numberProductPage);
            
            return q.getResultList();
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "Lỗi khi phân trang sản phẩm", e);
            return null;
        }finally{
            if(em != null && em.isOpen()){
                em.close();
            }
        }
    }
}
