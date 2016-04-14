 import java.sql.ResultSet;
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
        Object[] params = {hotel.getName(), hotel.getAddress(), hotel.getType(), hotel.getDescription(), hotel.getPhoneNumber(),
                (Double) hotel.getStarCount(), (Double) hotel.getAvgPrice(), hotel.getCheckoutTime(), hotel.getTags()};
        ResultSet results = dbh.runQuery("INSERT INTO hotel(hotelname, address, typeofhotel, " +
                "description, phonenumber, starcount, avgprice, checkouttime, " +
                "tags) VALUES(?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?)", params);
    }

    public void deleteHotel(Hotel hotel) {
        Object[] params = {hotel.getName()};
        ResultSet results = dbh.runQuery("DELETE FROM hotel WHERE name=?", params);

    }

    public void giveReview(Hotel hotel, String user, String review, double userRating) {
        Object[] params = {hotel.getId(), user, new Date(), review, userRating};
        ResultSet results = dbh.runQuery("INSERT INTO  review(hotelid, username, datewritten, helpcount," +
                " review, userrating) VALUES(?, ?, ?, 0, ?, ?)", params);
    }

    public void addRoom(Hotel hotel, Room room) {
        Object[] params = {(Integer)hotel.getId(), (Integer)room.getNumberOfBeds(),
                (Double)room.getSizeOfRoom(), room.getTypeOfBathroom(), (Integer)room.getRoomNumber(),
                (Integer)room.getMaxGuests(), room.getDescription(), (double)room.getRoomPrice()};
        ResultSet results = dbh.runQuery("INSERT INTO room(hotelId, numberOfBeds, sizeOfRoom, typeOfBathroom, " +
                "roomNumber, maxGuests, description, roomprice) VALUES(?, ?, ?, ?, ?, ?, ?, ?)", params);
    }

    public void removeRoom(Hotel hotel, Room room) {
        Object[] params = {room.getId()};
        ResultSet results = dbh.runQuery("DELETE FROM room WHERE room.id=?", params);
    }

    public Hotel[] findHotelsWithAvailableRooms(Date startDate, Date endDate, int guestCount, double minimumStars, int maxPrice) {
        return null;
    }

    public Hotel getRandomHotelOfTheWeek() {
        return null;
    }

    public void HotelController() {
    }
}
