package DAO;

import Model.CategoryDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDAO extends GenericDAO<CategoryDTO>{

    @Override
    public CategoryDTO mapRow(ResultSet rs) {
        try{
            return new CategoryDTO(
                    rs.getString("catID"),
                    rs.getString("catName"),
                    rs.getBoolean("status")
            );
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<CategoryDTO> getAllCategories() {
        String sql = "SELECT * FROM tblCategories WHERE status = 1";
        return query(sql);
    }

}
