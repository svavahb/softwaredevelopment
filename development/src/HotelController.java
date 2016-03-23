import java.util.Date;

/**
 * Created by arnorv on 16/03/16.
 */
public class HotelController {
    private Hotel[] hotels;

    public Hotel getHotel(String name) {

        dbHelper dbh = new dbHelper();
        results = dbh.runQuery("SELECT * FROM hotel WHERE name=$1", {"Hilton", 0});
        Hotel hotel = new Hotel(results[1]);
        hotel.setName(results[0]);
        return hotel;
    }

    public Hotel[] getAllHotels() {
        return null;
    }

    public void saveHotel(Hotel hotel) {
    }

    public void deleteHotel(Hotel hotel) {
    }

    public void giveReview(Hotel hotel, String user, String review, double userRating) {
    }

    public void addRoom(Hotel hotel, Room room) {
    }

    public void removeRoom(Hotel hotel, Room room) {
    }

    public void updateRoom(Hotel hotel, Room room) {
    }

    public Hotel findHotelWithAvailableRooms(Date startDate, Date endDate, int guestCount, String location, double minimumStars, int maxPrice) {
        return null;
    }

    public Hotel getRandomHotelOfTheWeek() {
        return null;
    }

    public HotelController() {
    }
}
