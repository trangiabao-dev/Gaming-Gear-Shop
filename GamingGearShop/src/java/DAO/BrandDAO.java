package DAO;

import Model.BrandDTO;
import java.util.List;

public class BrandDAO extends JPAGenericDAO<BrandDTO> {

    public BrandDAO() {
        super(BrandDTO.class);
    }
    
    // Dành cho KHÁCH HÀNG
    public List<BrandDTO> getAllBrands() {
        String jpql = "SELECT b FROM BrandDTO b";
        return super.query(jpql);
    }
    
    // Dành cho ADMIN
    public List<BrandDTO> getAllBrandsForAdmin() {
        String jpql = "SELECT b FROM BrandDTO b ORDER BY b.status DESC, b.brandID ASC";
        return super.query(jpql);
    }
}