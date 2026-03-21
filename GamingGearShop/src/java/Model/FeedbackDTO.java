package Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblFeedbacks")
public class FeedbackDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  Tự tăng
    @Column(name = "feedID")
    private int feedID;

    private String content;
    private int rating;
    private String userID;
    private String productID;

    private String date;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int feedID, String content, int rating,
            String userID, String productID, String date) {
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

    public void setContent(String c) {
        this.content = c;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int r) {
        this.rating = r;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String u) {
        this.userID = u;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String p) {
        this.productID = p;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String d) {
        this.date = d;
    }
}
