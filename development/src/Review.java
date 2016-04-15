/**
 * Created by Svava Hildur on 16/03/16.
 */
public class Review {
    private String user;
    private String date;
    private int helpfulCount;
    private String review;
    private double userRating;
    private int id;
    private int hotelId;

    public Review() {

    }

    public void setId(int id) { this.id = id; }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHelpfulCount() {
        return helpfulCount;
    }

    public void setHelpfulCount(int helpfulCount) {
        this.helpfulCount = helpfulCount;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public int getHotelId() { return hotelId; }

    public void setHotelId(int hotelId) { this.hotelId = hotelId; }
}
