package Model;

public class Category {
    private String id;
    private String name;
    private boolean status;

    public Category() {
    }

    public Category(String id, String name, boolean status) {
        setId(id);
        setName(name);
        this.status = status;
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
        if(!name.trim().isEmpty() && name.length() <= 50){
            this.name = name;
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
    
}
