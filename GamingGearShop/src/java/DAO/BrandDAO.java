package DAO;

import Model.BrandDTO;
import java.util.List;

public class BrandDAO extends JPAGenericDAO<BrandDTO> {

    public BrandDAO() {
        super(BrandDTO.class);
    }

    public List<BrandDTO> getAllBrands() {
        String jpql = "SELECT b FROM BrandDTO b";
        return super.query(jpql);
    }
}