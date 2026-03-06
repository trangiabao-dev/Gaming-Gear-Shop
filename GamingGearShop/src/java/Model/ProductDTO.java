package Model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblProducts")
public class ProductDTO implements Serializable {
    
    @Id
    @Column(name = "productID")
    private String productID;
    
    private String productName;  
    private double price;
    private int quantity;        
    private String description;
    private String imageURL;     
    private boolean status;      
    private String catID;        
    private String brandID;
    
    private static final NumberFormat PRICE_FORMAT = NumberFormat.getInstance(new Locale("vi", "VN"));

    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, double price, 
            int quantity, String description, String imageURL, boolean status,
            String catID, String brandID) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.imageURL = imageURL;
        this.status = status;
        this.catID = catID;
        this.brandID = brandID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    // Chỉnh FORMAT Giá lại
    public String getPriceFormat(){
        return PRICE_FORMAT.format(this.price);
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productID=" + productID + ", productName=" + productName + ", price=" + price + ", quantity=" + quantity + ", status=" + status + ", catID=" + catID + ", brandID=" + brandID + '}';
    }
    
}