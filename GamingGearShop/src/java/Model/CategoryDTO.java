package Model;

import utils.Validation;

public class CategoryDTO {
    private String catID;
    private String catName;
    private boolean status;

    public CategoryDTO() {
    }

    public CategoryDTO(String catID, String catName, boolean status) {
        setCatID(catID);
        setCatName(catName);
        this.status = status;
    }

    public String getCatID() {
        return catID;
    }

    public final void setCatID(String catID) {
        if(Validation.checkInput(catID, 10)){
            this.catID = catID;
        }else{
            throw new IllegalArgumentException("Id không được để trống!");
        }
    }

    public String getCatName() {
        return catName;
    }

    public final void setCatName(String catName) {
        if(Validation.checkInput(catName, 50)){
            this.catName = catName;
        }else{
            throw new IllegalArgumentException("Tên không được để trống!");
        }
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
