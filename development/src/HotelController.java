 import java.util.Date;

/**
 * Created by Svava Hildur on 16/03/16.
 */
public class HotelController {
    private Hotel[] hotels;
    private dbHelper dbh = new dbHelper();


    public Hotel getHotel(String name) {
        //results = dbh.runQuery("SELECT * FROM hotel WHERE name=$1", {"Hilton", 0});
        //Hotel hotel = new Hotel(results[1]);
        //hotel.setName(results[0]);
        //return hotel;
        return new Hotel(2);
    }

    public Hotel[] getAllHotels() {
        return null;
    }

    public void saveHotel(Hotel hotel) {
        Object[] params = {hotel.getName(), hotel.getAddress(), hotel.getType(), hotel.getDescription(), hotel.getPhoneNumber()
                (Double) hotel.getStarCount(), (Double) hotel.getAvgPrice(), hotel.getCheckoutTime(), hotel.getTags()};
        results = dbh.runQuery("INSERT INTO hotel(hotelname, address, typeofhotel, " +
                "description, phonenumber, starcount, avgprice, checkouttime, " +
                "tags) VALUES('?', '?', '?', '?', '?', '?', " +
                "'?', '?', '?', '?')", params);
    }

    public void deleteHotel(Hotel hotel) {
        results = dbh.runQuery("DELETE FROM hotel WHERE name=?", {"Hilton", 0});

    }

    public void giveReview(Hotel hotel, String user, String review, double userRating) {
        Object[] params = {hotel.getId(), Date}
        results = dbh.runQuery("INSERT INTO  review(hotelid, username, datewritten, helpcount," +
                " review, userrating) VALUES('?', '?', '0', '?', '?')", params)
    }

    public void addRoom(Hotel hotel, Room room) {
    }

    public void removeRoom(Hotel hotel, Room room) {
    }

    public Hotel[] findHotelWithAvailableRooms(Date startDate, Date endDate, int guestCount, double minimumStars, int maxPrice) {
        return null;
    }

    public Hotel getRandomHotelOfTheWeek() {
        return null;
    }

    public void HotelController() {
    }
}
