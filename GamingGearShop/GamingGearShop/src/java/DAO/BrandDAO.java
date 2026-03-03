package DAO;

import Model.BrandDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BrandDAO extends GenericDAO<BrandDTO> {

    @Override
    protected BrandDTO mapRow(ResultSet rs) {
        try {
            return new BrandDTO(
                    rs.getString("brandID"),
                    rs.getString("brandName")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BrandDTO> getAllBrands() {
        String sql = "SELECT * FROM tblBrands";
        return query(sql);
    }

}
