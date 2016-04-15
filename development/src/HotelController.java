 import java.sql.*;
 import java.util.*;

 //halldóra hálfviti

 /**
 * Created by Svava Hildur on 16/03/16.
 */

public class HotelController {
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
        ResultSet results = dbh.runQuery("SELECT * FROM hotel WHERE ?=1", params);
        ArrayList<Array> resultList = new ArrayList<Array>();
        ArrayList<String[]> bla = new ArrayList<String[]>();

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
        ArrayList<Hotel> hotels = new ArrayList<Hotel>(size);
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
        String tagstring = Arrays.toString(hotel.getTags());
        tagstring = tagstring.substring(1,tagstring.length()-1);
        tagstring = "'{"+tagstring+"}');";
        Object[] params = {hotel.getName(), hotel.getAddress(), hotel.getType(), hotel.getDescription(), hotel.getPhoneNumber(),
                hotel.getStarCount(), hotel.getAvgPrice(), hotel.getCheckoutTime()};
        String queryStr = "INSERT INTO hotel(hotelname, address, typeofhotel, " +
                "description, phonenumber, starcount, avgprice, checkouttime, " +
                "tags) VALUES(?, ?, ?, ?, ?, ?, " +
                "?, ?, "+ tagstring;
        dbh.runQuery(queryStr, params);
    }

    public void deleteHotel(Hotel hotel) {
        Object[] params = {hotel.getName()};
        dbh.runQuery("DELETE FROM hotel WHERE name=?", params);

    }

    public void giveReview(Hotel hotel, String user, String review, double userRating, String date) {
        Object[] params = {hotel.getId(), user, 0, review, userRating, date  };
        dbh.runQuery("INSERT INTO  review(hotelid, username, helpcount," +
                " review, userrating, datewritten) VALUES(?, ?, ?, ?, ?, ?)", params);

    }

    public void addRoom(Hotel hotel, Room room) {
        Object[] params = {(Integer)hotel.getId(), (Integer)room.getNumberOfBeds(),
                (Double)room.getSizeOfRoom(), room.getTypeOfBathroom(), (Integer)room.getRoomNumber(),
                (Integer)room.getMaxGuests(), room.getDescription(), (double)room.getRoomPrice()};
        dbh.runQuery("INSERT INTO room(hotelId, numberOfBeds, sizeOfRoom, typeOfBathroom, " +
                "roomNumber, maxGuests, description, roomprice) VALUES(?, ?, ?, ?, ?, ?, ?, ?)", params);
    }

    public void removeRoom(Hotel hotel, Room room) {
        Object[] params = {room.getId()};
        dbh.runQuery("DELETE FROM room WHERE room.id=?", params);
    }

    public Hotel[] findHotelWithAvailableRooms(String startDate, String endDate, int guestCount, double minimumStars, int maxPrice) {
        return null;
    }

     public Hotel[] findHotelByTags(String[] tags) throws SQLException {
         Object[] params = tags;
         String queryStr = "SELECT * FROM hotel WHERE ? = ANY (tags)";
         for(int i=1; i<tags.length; i++) {
             queryStr += " AND ? = ANY (tags) ";
         }
         ResultSet result = dbh.runQuery(queryStr, params);
         ArrayList<String[]> resultList = new ArrayList<String[]>();
         ArrayList<Array> tagList = new ArrayList<Array>();

         int columnCount = result.getMetaData().getColumnCount();
         while (result.next()) {
             String[] row = new String[columnCount];
             for (int i = 0; i < columnCount - 1; i++) {
                 row[i] = result.getString(i + 1);
             }
             tagList.add(result.getArray(10));
             resultList.add(row);
         }

         int size = resultList.size();
         ArrayList<Hotel> hotels = new ArrayList<Hotel>(size);
         for(int i = 0; i < size; i++) {
             String[] row = resultList.get(i);
             Hotel hotel = new Hotel(Integer.parseInt(row[0]));
             hotel.setName(row[1]);
             hotel.setAddress(row[2]);
             hotel.setType(row[3]);
             hotel.setDescription(row[4]);
             hotel.setPhoneNumber(row[5]);
             hotel.setStarCount(Double.parseDouble(row[6]));
             hotel.setAvgPrice(Double.parseDouble(row[7]));
             hotel.setCheckoutTime(row[8]);
             String tmp = tagList.get(i).toString();
             tmp = tmp.substring(1, tmp.length()-1);
             String[] tagstmp = tmp.split(",");
             hotel.setTags(tagstmp);

             hotels.add(hotel);
         }
         return hotels.toArray(new Hotel[hotels.size()]);

     }

    public Hotel getRandomHotelOfTheWeek() {
        return null;
    }

    public void HotelController() {
        dbh = new dbHelper();
    }

    public Review[] getReviews(Hotel hotel) throws SQLException {
        Object[] params = {hotel.getId()};
        ResultSet results = dbh.runQuery("SELECT * FROM hotel", params);
        ArrayList<String[]> resultList = new ArrayList<String[]>();

        int columnCount = results.getMetaData().getColumnCount();
        while (results.next()) {
            String[] row = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = results.getString(i + 1);
            }
            resultList.add(row);
        }
        //mjá
        int size = resultList.size();
        Review[] reviews = new Review[size];
        for(int i=0; i<size; i++) {
            String[] row = resultList.get(i);
            Review review = new Review(Integer.parseInt(row[0]));
            review.setHotelId(Integer.parseInt(row[1]));
            review.setUser(row[2]);
            review.setHelpfulCount(Integer.parseInt(row[3]));
            review.setReview(row[4]);
            review.setUserRating(Double.parseDouble(row[5]));
            review.setDate(row[6]);
            reviews[i] = review;
        }
        return reviews;
    }


 }
