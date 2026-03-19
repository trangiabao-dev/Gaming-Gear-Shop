package DAO;

import Model.CategoryDTO;
import java.util.List;

public class CategoryDAO extends JPAGenericDAO<CategoryDTO>{

    public CategoryDAO() {
        super(CategoryDTO.class);
    }

    // Dành cho KHÁCH HÀNG
    public List<CategoryDTO> getAllCategories() {
        String jpql = "SELECT c FROM CategoryDTO c";
        return super.query(jpql);
    }

     // Dành cho ADMIN
    public List<CategoryDTO> getAllCategoriesForAdmin() {
        String jpql = "SELECT c FROM CategoryDTO c ORDER BY c.status DESC, c.catID ASC";
        return super.query(jpql);
    }
}
