package Model;

import java.text.NumberFormat;
import java.util.Locale;
import utils.Validation;

public class ProductDTO {
    private String productID;  
    private String productName;  
    private double price;
    private int quantity;        
    private String description;
    private String imageURL;     
    private boolean status;      
    private String catID;        
    private String brandID;      

    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, double price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
    
    

    public ProductDTO(String productID, String productName, double price, int quantity, 
                   String description, String imageURL, boolean status, 
                   String catID, String brandID) {
        setProductID(productID);
        setProductName(productName);
        setPrice(price);
        setQuantity(quantity);
        this.description = description;
        setImageURL(imageURL);
        this.status = status;
        this.catID = catID;
        this.brandID = brandID;
    }

    public String getProductID() {
        return productID;
    }

    public final void setProductID(String productID) {
        if (Validation.checkInput(productID, 10)) {
            this.productID = productID;
        } else {
            throw new IllegalArgumentException("ID không được để trống và tối đa 10 ký tự!");
        }
    }

    public String getProductName() {
        return productName;
    }

    public final void setProductName(String productName) {
        if (Validation.checkInput(productName, 500)) {
            this.productName = productName;
        } else {
            throw new IllegalArgumentException("Tên không được để trống và tối đa 500 ký tự!");
        }
    }

    public double getPrice() {
        return price;
    }

    public final void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Giá phải lớn hơn 0");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public final void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Số lượng phải >= 0");
        }
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

    public final void setImageURL(String imageURL) {
        if (Validation.checkInput(imageURL, 200)) {
            this.imageURL = imageURL;
        }else{
            throw new IllegalArgumentException("Link ảnh quá dài! Tối đa 200 ký tự.");
        }
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
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat PriceFormat = NumberFormat.getInstance(localeVN);
        return PriceFormat.format(this.price);
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productID=" + productID + ", productName=" + productName + ", price=" + price + ", quantity=" + quantity + ", status=" + status + ", catID=" + catID + ", brandID=" + brandID + '}';
    }
    
}