package Model;

public class CategoryDTO {
    private String catID;
    private String catName;
    private boolean status;

    public CategoryDTO() {
    }

    public CategoryDTO(String catID, String catName, boolean status) {
        this.catID = catID;
        this.catName = catName;
        this.status = status;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" + "catID=" + catID + ", catName=" + catName + ", status=" + status + '}';
    }
    
}
