package DAO;

import Model.CategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.dbutils;

public class CategoryDAO {

    private CategoryDTO mapCateDTO(ResultSet rs) {
        try {
            return new CategoryDTO(
                    rs.getString("catID"),
                    rs.getString("catName"),
                    rs.getBoolean("status")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblCategories WHERE status = 1";

        try ( Connection conn = dbutils.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()){
            
            while (rs.next()) {
                CategoryDTO checkMapCategory = mapCateDTO(rs);
                if (checkMapCategory != null) {
                    list.add(checkMapCategory);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
