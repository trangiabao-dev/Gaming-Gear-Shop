package Model;

public class BrandDTO {
    private String brandID;
    private String brandName;

    public BrandDTO() {
    }

    public BrandDTO(String brandID, String brandName) {
        this.brandID = brandID;
        this.brandName = brandName;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    
}
