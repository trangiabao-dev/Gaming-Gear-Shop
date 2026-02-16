/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

public class FeedbackDTO {
    private int feedID;
    private String content;
    private int rating;
    private String userID;
    private String productID;
    private String date;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int feedID, String content, int rating, String userID, String productID, String date) {
        this.feedID = feedID;
        this.content = content;
        this.rating = rating;
        this.userID = userID;
        this.productID = productID;
        this.date = date;
    }

    public int getFeedID() {
        return feedID;
    }

    public void setFeedID(int feedID) {
        this.feedID = feedID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
