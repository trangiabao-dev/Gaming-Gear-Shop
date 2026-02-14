package DAO;

import Model.BrandDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.dbutils;

public class BrandDAO {
    
    private BrandDTO mapBrandDTO(ResultSet rs) throws SQLException {
        return new BrandDTO(
                rs.getString("brandID"),
                rs.getString("brandName"));
    }
    
    public List<BrandDTO> getAllBrands(){
        List<BrandDTO> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tblBrands";
                
        try(Connection conn = dbutils.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()){
            
            while(rs.next()){
                list.add(mapBrandDTO(rs));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
}
