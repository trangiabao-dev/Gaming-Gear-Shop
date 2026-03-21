package Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblBanners")
public class BannerDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID này do database tự sinh ra, không cần tôi truyền vào
    @Column(name = "BannerID")
    private int bannerID;
    private String imageURL;
    private String title;
    private boolean status;

    public BannerDTO() {
    }

    public BannerDTO(String imageURL, String title, boolean status) {
        this.imageURL = imageURL;
        this.title = title;
        this.status = status;
    }

    public int getBannerID() {
        return bannerID;
    }

    public void setBannerID(int bannerID) {
        this.bannerID = bannerID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
