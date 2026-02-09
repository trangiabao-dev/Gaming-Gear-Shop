package Model;

public class Product {
    private String id;
    private String name;
    private double price;
    private String image;
    private String description;
    private String cateID;      
    private int brandID;

    public Product() {
    }

    public Product(String id, String name, double price, String image, String description, String cateID, int brandID) {
        setId(id);
        setName(name);
        setPrice(price);
        setImage(image);
        this.description = description;
        this.cateID = cateID;
        this.brandID = brandID;
    }
    
    public String getId() {
        return id;
    }

    public final void setId(String id) {
        if(!id.trim().isEmpty() && id.length() <= 10){
            this.id = id;
        }else{
            throw new IllegalArgumentException("Id không được để trống!");
        }
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        if(!name.trim().isEmpty()){
            this.name = name;
        }else{
            throw new IllegalArgumentException("Tên không được để trống!");
        }
    }

    public double getPrice() {
        return price;
    }

    public final void setPrice(double price) {
        if(price > 0){
            this.price = price;
        }else{
            throw new IllegalArgumentException("Giá phải lớn hơn 0");
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        if(image != null && image.length() > 500){
            throw new IllegalArgumentException("Đường dẫn ảnh quá dài! Tối đa 500 ký tự.");
        }
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

}
