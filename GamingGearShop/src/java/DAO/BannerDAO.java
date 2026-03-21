package DAO;

import Model.BannerDTO;
import java.util.List;

public class BannerDAO extends JPAGenericDAO<BannerDTO>{

    public BannerDAO() {
        super(BannerDTO.class);
    }
     
    public List<BannerDTO> getActiveBanners(){
        String jpql = "SELECT b FROM BannerDTO b WHERE b.status = true ORDER BY b.bannerID ASC";
        return super.query(jpql);
    }
    
    // Banner dành cho ADMIN
    public List<BannerDTO> getAllBanners(){
        String jpql = "SELECT b FROM BannerDTO b ORDER BY b.status DESC, b.bannerID ASC";
        return super.query(jpql);
    }
}
