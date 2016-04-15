 import java.util.Date;
 import java.sql.*;
 import java.util.*;

 //halldóra hálfviti

 /**
 * Created by Svava Hildur on 16/03/16.
 */

public class HotelController {
     private Hotel[] hotels;
     private dbHelper dbh;


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
        hotel.setType(results[3]);
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

    public Hotel[] getAllHotels() throws SQLException {
        Object[] params = {(Integer) 1};
        ResultSet results = dbh.runQuery("SELECT * FROM hotel", params);
        ArrayList<Array> resultList = new ArrayList<>();
        ArrayList<String[]> bla = new ArrayList<>();

        int columnCount = results.getMetaData().getColumnCount();
        while (results.next()) {
            String[] row = new String[columnCount];
            for (int i = 0; i < columnCount - 1; i++) {
                row[i] = results.getString(i + 1);
            }
            resultList.add(results.getArray(10));
            bla.add(row);
        }
        int size = resultList.size();
        ArrayList<Hotel> hotels = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            String[] row = bla.get(i);
            Hotel hotel = new Hotel(Integer.parseInt(row[0]));
            hotel.setName(row[1]);
            hotel.setAddress(row[2]);
            hotel.setType(row[3]);
            hotel.setDescription(row[4]);
            hotel.setPhoneNumber(row[5]);
            hotel.setStarCount(Double.parseDouble(row[6]));
            hotel.setAvgPrice(Double.parseDouble(row[7]));
            hotel.setCheckoutTime(row[8]);
            String tmp = resultList.get(i).toString();
            tmp = tmp.substring(1, tmp.length()-1);
            String[] tags = tmp.split(",");
            hotel.setTags(tags);

            hotels.add(hotel);
        }
        return hotels.toArray(new Hotel[hotels.size()]);
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
        Object[] params = {hotel.getId(), user, "date", review, userRating };
        ResultSet results = dbh.runQuery("INSERT INTO  review(hotelid, username, helpcount," +
                " review, userrating, datewritten) VALUES(?, ?, ?, 0, ?, ?)", params);

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
        dbh = new dbHelper();
    }

    public Review[] getReviews(Hotel hotel) {
        return null;
    }


 }
