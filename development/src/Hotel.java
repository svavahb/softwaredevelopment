
/**
 * Created by Svava Hildur on 16/03/16.
 */
public class Hotel {
    private Review[] reviews;
    private int id;
    private String name;
    private String address;
    private String type;
    private String phoneNumber;
    private String description;
    private double starCount;
    private double rating; //????
    private Room[] rooms;
    private String[] tags;
    private double avgPrice;
    private String checkoutTime;
    //private DayTrip[] dayTours;

    public Hotel(int id) {

        this.id = id;
    }

    public Review[] getReviews() {

        return reviews;
    }

    public int getId() {
        return id;
    }
    public void setReviews(Review[] reviews) {

        this.reviews = reviews;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public double getStarCount() {

        return starCount;
    }

    public void setStarCount(double starCount) {

        this.starCount = starCount;
    }

    public double getRating() {

        return rating;
    }

    public void setRating(double rating) {

        this.rating = rating;
    }

    public Room[] getRooms() {

        return rooms;
    }

    public void setRooms(Room[] rooms) {

        this.rooms = rooms;
    }

    public int getNumberOfRooms() {

        return rooms.length;
    }

    public String[] getTags() {

        return tags;
    }

    public void setTags(String[] tags) {

        this.tags = tags;
    }

    public double getAvgPrice() {

        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {

        this.avgPrice = avgPrice;
    }

    public String getCheckoutTime() {

        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {

        this.checkoutTime = checkoutTime;
    }

    //public DayTrip[] getDayTours() {
    //    return dayTours;
    //}

    //public void setDayTours(DayTrip[] dayTours) {
    //    this.dayTours = dayTours;
    //}
}
