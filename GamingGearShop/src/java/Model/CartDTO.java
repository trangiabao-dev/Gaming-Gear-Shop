package Model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.*;

@Entity
@Table(name = "tblCart")
public class CartDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartID")
    private int cartID;

    private String userID;
    private String productID;
    private int quantity;

//    @Transient nghĩa là field đó không lưu xuống DB — chỉ dùng để hiển thị trên JSP!
//    tblProducts đã có sẵn productName và price rồi
//→ Lưu thêm vào tblCart = dữ liệu bị trùng lặp
//→ Nếu admin sửa giá sản phẩm → tblCart vẫn hiển thị giá cũ
//→ Không đúng chuẩn database
    @Transient
    private String productName;
    @Transient
    private double price;
    
    private static final NumberFormat PRICE_FORMAT = NumberFormat.getInstance(new Locale("vi", "VN"));
    
    public CartDTO() {
    }

    public CartDTO(String userID, String productID, int quantity) {
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int v) {
        this.cartID = v;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String v) {
        this.userID = v;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String v) {
        this.productID = v;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int v) {
        this.quantity = v;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String v) {
        this.productName = v;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double v) {
        this.price = v;
    }
    
    public String getPriceFormat(){
        return PRICE_FORMAT.format(this.price);
    }
    
    public String getTotalFormat(){
        return PRICE_FORMAT.format(this.price * this.quantity);
    }
}
