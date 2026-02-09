package Model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String image;
    private String description;
    private int cateID;      
    private int brandID;

    public Product() {
    }

    public Product(int id, String name, double price, String image, String description, int cateID, int brandID) {
        this.id = id;
        this.name = name;
        setPrice(price);
        this.image = image;
        this.description = description;
        this.cateID = cateID;
        this.brandID = brandID;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

}
