package DAO;

import Model.CategoryDTO;
import java.util.List;

public class CategoryDAO extends JPAGenericDAO<CategoryDTO>{

    public CategoryDAO() {
        super(CategoryDTO.class);
    }

    public List<CategoryDTO> getAllCategories() {
        String jpql = "SELECT c FROM CategoryDTO c";
        return super.query(jpql);
    }

}
