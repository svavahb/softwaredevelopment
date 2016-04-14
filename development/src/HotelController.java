 import java.util.Date;
 import java.sql.*;
 import java.util.*;

 import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.description;

 /**
 * Created by Svava Hildur on 16/03/16.
 */

public class HotelController {
     private Hotel[] hotels;
     private dbHelper dbh = new dbHelper();


    public Hotel getHotel(String name) throws SQLException {
        Object[] params = {name};
        ResultSet dbresults = dbh.runQuery("SELECT * FROM hotel WHERE name=?", params);
        int columnCount = dbresults.getMetaData().getColumnCount();
        String[] results = new String[10];
        ArrayList<Array> taglist = new ArrayList<Array>();
        while(dbresults.next()) {
            for(int i=1; i<columnCount-1; i++) {
                results[i-1] = dbresults.getString(i);
            }
            taglist.add(dbresults.getArray(10));
        }
        Hotel hotel = new Hotel(Integer.parseInt(results[0]));
        hotel.setName(results[1]);
        hotel.setAddress(results[2]);
        hotel.setType(results[3])
        hotel.setDescription(results[4]);
        hotel.setPhoneNumber(results[5]);
        hotel.setStarCount(Double.parseDouble(results[6]));
        hotel.setAvgPrice(Double.parseDouble(results[7]));
        hotel.setCheckoutTime(results[8]);
        String tmp = taglist.get(0).toString();
        tmp = tmp.substring(1, tmp.length()-1);
        String[] tags = tmp.split(",");
        hotel.setTags(tags);

        return hotel;

    }

    public Hotel[] getAllHotels() {
        Object[] params = {(Integer) 1};
        ResultSet dbresults = dbh.runQuery("SELECT * FROM hotel", params);


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
        Object[] params = {hotel.getId(), user, "date", (Integer) };
        ResultSet results = dbh.runQuery("INSERT INTO  review(hotelid, username, datewritten, helpcount," +
                " review, userrating) VALUES(?, ?, 0, ?, ?)", params);
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

    public Hotel[] findHotelWithAvailableRooms(Date startDate, Date endDate, int guestCount, double minimumStars, int maxPrice) {
        return null;
    }

    public Hotel getRandomHotelOfTheWeek() {
        return null;
    }

    public void HotelController() {
    }

    public Review[] getReviews() {
        return null;
    }
 }
