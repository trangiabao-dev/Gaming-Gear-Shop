/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author thinh
 */
public class UserDTO {

    private String userID;
    private String fullName;
    private String password;
    private int roleID;       // SQL là int -> Java là int
    private String address;
    private String phone;
    private boolean status;   // SQL là bit -> Java là boolean

    public UserDTO() {
    }

    public UserDTO(String userID, String fullName, String password, int roleID, String address, String phone, boolean status) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public UserDTO(String userID, String fullName, int roleID, String password) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID; // Gán int vào int -> OK
        this.password = password;
    }

    // Chỉ Get và Set 
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
