 import java.sql.*;
 import java.util.*;

 /**
 * Created by Svava Hildur on 16/03/16.
 */

public class HotelController {
     // klasi sem sér um tengingu við gagnagrunn
     private dbHelper dbh;

    // Nær í hótel með nafninu name. Ef fleiri en eitt hótel hafa sama nafn (ætti ekki að gerast),
    // fæst fyrsta með því nafni

     /**
      * Leitar að hóteli eftir nafni, og skilar fyrsta sem finnst í gagnagrunni sem passar
      * @param name
      * @return Hoteli úr gagnagrunni með nafninu name
      * @throws SQLException
      */
    public Hotel getHotel(String name) throws SQLException {
        Object[] params = {name};
        ResultSet dbresults = dbh.runQuery("SELECT * FROM hotel WHERE hotelname=?", params);
        int columnCount = dbresults.getMetaData().getColumnCount();
        String[] results = new String[11];
        ArrayList<Array> taglist = new ArrayList<Array>();
        while(dbresults.next()) {
            for(int i=1; i<columnCount; i++) {
                results[i-1] = dbresults.getString(i);
            }
            taglist.add(dbresults.getArray(12));
        }
        Hotel hotel = createHotel(results);
        String tmp = taglist.get(0).toString();
        tmp = tmp.substring(1, tmp.length()-1);
        String[] tags = tmp.split(",");
        hotel.setTags(tags);

        return hotel;

    }

     // nær í öll hótel úr gagnagrunni, þægilegt að nota til að birta lista yfir öll hótelin

     /**
      * Nær í öll hótel úr gagnagrunninum
      * @return fylki með Hotel hlutum, öllum hótelum úr gagnagrunninum
      * @throws SQLException
      */
    public Hotel[] getAllHotels() throws SQLException {
        Object[] params = {(Integer) 1};
        ResultSet results = dbh.runQuery("SELECT * FROM hotel WHERE ?=1", params);
        ArrayList<Array> tagList = new ArrayList<Array>();
        ArrayList<String[]> resultList = new ArrayList<String[]>();

        int columnCount = results.getMetaData().getColumnCount();
        while (results.next()) {
            String[] row = new String[columnCount];
            for (int i = 0; i < columnCount - 1; i++) {
                row[i] = results.getString(i + 1);
            }
            tagList.add(results.getArray(12));
            resultList.add(row);
        }
        int size = resultList.size();
        Hotel[] hotels = new Hotel[size];
        for(int i = 0; i < size; i++) {
            String[] row = resultList.get(i);
            Hotel hotel = createHotel(row);
            String tmp = tagList.get(i).toString();
            tmp = tmp.substring(1, tmp.length()-1);
            String[] tags = tmp.split(",");
            hotel.setTags(tags);

            hotels[i] = hotel;
        }
        return hotels;
    }

    // vistar hótel í gagnagrunni, og skilar sama hóteli sem Hotel hlut þar sem allar breytur innihalda rétt gildi

     /**
      * Vistar hótel í gagnagrunni og býr til hótel hlut með réttum tilviksbreytum
      * @param hotel
      * @return Hotel sem inniheldur allar réttar upplýsingar um hótelið sem var tekið inn
      * @throws SQLException
      */
    public Hotel saveHotel(Hotel hotel) throws SQLException {
        String tagstring = Arrays.toString(hotel.getTags());
        tagstring = tagstring.substring(1,tagstring.length()-1);
        tagstring = "'{"+tagstring+"}');";
        Object[] params = {hotel.getName(), hotel.getAddress(), hotel.getType(), hotel.getDescription(), hotel.getPhoneNumber(),
                hotel.getStarCount(), hotel.getAvgPrice(), hotel.getCheckoutTime(), hotel.getRating(), hotel.getPlace()};
        String queryStr = "INSERT INTO hotel(hotelname, address, typeofhotel, " +
                "description, phonenumber, starcount, avgprice, checkouttime, " +
                "rating, place, tags) VALUES(?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, "+ tagstring;
        dbh.runQuery(queryStr, params);

        Object[] par = {hotel.getName()};
        ResultSet result = dbh.runQuery("SELECT id FROM hotel WHERE hotelname = ?", par);
        while(result.next()) {
            hotel.setId(Integer.parseInt(result.getString(1)));
        }
        return hotel;
    }


     /**
      * Eyðir hóteli úr gagnagrunni
      * @param hotel
      */
    public void deleteHotel(Hotel hotel) {
        Object[] params = {hotel.getId()};
        dbh.runQuery("DELETE FROM room WHERE hotelid = ?", params);
        dbh.runQuery("DELETE FROM review WHERE hotelid = ?", params);
        dbh.runQuery("DELETE FROM booking WHERE hotelid = ?", params);
        dbh.runQuery("DELETE FROM hotel WHERE id=?", params);
    }

     /**
      * Vistar umsögn um hótel, bæði í gagnagrunni og viðeigandi tilviksbreytu Hotel hlutar
      * @param hotel
      * @param user username/nafn
      * @param reviewtext texti umsagnar
      * @param userRating einkunn notanda um hótel
      * @param date dagsetning sem það var skrifað
      * @return Review hlut sem inniheldur allar upplýsingar um umsögnina sem var vistuð
      * @throws SQLException
      */
    public Review giveReview(Hotel hotel, String user, String reviewtext, double userRating, String date) throws SQLException {
        Object[] params = {hotel.getId(), user, 0, reviewtext, userRating, java.sql.Date.valueOf(date)  };
        String queryStr = "INSERT INTO  review(hotelid, username, helpcount," +
                " review, userrating, datewritten) VALUES (?, ?, ?, ?, ?, ?)";
        dbh.runQuery(queryStr, params);
        Review[] reviews = getReviews(hotel);
        hotel.setReviews(reviews);
        hotel.updateRating(userRating);

        ArrayList<String[]> resultList = new ArrayList<String[]>();
        Object[] par = {java.sql.Date.valueOf(date), hotel.getId()};
        ResultSet result = dbh.runQuery("SELECT * FROM review WHERE datewritten = ? AND hotelid = ?", par);
        int columnCount = result.getMetaData().getColumnCount();
        while (result.next()) {
            String[] row = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i-1] = result.getString(i);
            }
            resultList.add(row);
        }
        String[] row = resultList.get(0);
        Review review = new Review();
        review.setId(Integer.parseInt(row[0]));
        review.setHotelId(Integer.parseInt(row[1]));
        review.setUser(row[2]);
        review.setHelpfulCount(Integer.parseInt(row[3]));
        review.setReview(row[4]);
        review.setUserRating(Double.parseDouble(row[5]));
        review.setDate(row[6]);
        return review;
    }

     // bætir herbergi í gagnagrunn, og viðeigandi tilviksbreytu Hotel hlutar

     /**
      * Bætir herbergi í gagnagrunn, og viðeigandi tilviksbreytu Hotel hlutar
      * @param hotel
      * @param room
      * @return skilar Room hlut með öllum réttum upplýsingum
      * @throws SQLException
      */
    public Room addRoom(Hotel hotel, Room room) throws SQLException {
        Object[] params = {(Integer)hotel.getId(), (Integer)room.getNumberOfBeds(),
                (Double)room.getSizeOfRoom(), room.getTypeOfBathroom(), (Integer)room.getRoomNumber(),
                (Integer)room.getMaxGuests(), room.getDescription(), (double)room.getRoomPrice()};
        dbh.runQuery("INSERT INTO room(hotelId, numberOfBeds, sizeOfRoom, typeOfBathroom, " +
                "roomNumber, maxGuests, description, roomprice) VALUES(?, ?, ?, ?, ?, ?, ?, ?)", params);
        Room[] rooms = getRooms(hotel);
        hotel.setRooms(rooms);

        Object[] par = {room.getRoomNumber(), hotel.getId()};
        ResultSet result = dbh.runQuery("SELECT id FROM room WHERE roomnumber = ? AND hotelid = ?", par);
        while(result.next()) {
            room.setId(Integer.parseInt(result.getString(1)));
        }
        return room;
    }

     /**
      * Eyðir herbergi úr gagnagrunni og viðeigandi tilviksbreytu Hotel hlutar
      * @param hotel
      * @param room
      * @throws SQLException
      */
    public void removeRoom(Hotel hotel, Room room) throws SQLException {
        Object[] params = {room.getId()};
        dbh.runQuery("DELETE FROM room WHERE room.id=?", params);
        Room[] rooms = getRooms(hotel);
        hotel.setRooms(rooms);
    }

     // Finnur öll hótel sem hafa laus herbergi á tímabilinu startDate-endDate, með lágmarksfjölda stjarna minimumStars
     // og hámarksverð maxPrice

     /**
      * FInnur öll hótel sem hafa laus herbergi á tímabilinu startdate-enddate, með lágmarksfjölda stjarna minimum stars,
      * hámarksverð maxPrice og staðsett í location
      * @param startDate
      * @param endDate
      * @param minimumStars
      * @param maxPrice
      * @param place
      * @return skilar hóteli sem passar við skilyrðin
      * @throws SQLException
      */
    public Hotel[] findHotelWithAvailableRooms(String startDate, String endDate, double minimumStars, int maxPrice, String place) throws SQLException {
        Object[] params = {java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate),
                            java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), minimumStars, maxPrice, place};
        String queryStr = "SELECT DISTINCT hotelname, address, typeofhotel, hotel.description, phonenumber, starcount, avgprice, "
                + "checkouttime, hotel.id, rating, place, tags  FROM hotel, room WHERE hotel.id=room.hotelid AND room.id IN "
                + "(SELECT roomid FROM booking WHERE (startdate NOT BETWEEN ? AND ?) OR (enddate NOT BETWEEN ? AND ?)) AND "
                + "starcount>=? AND avgprice<=? AND place = ?";
        ResultSet result = dbh.runQuery(queryStr, params);
        ArrayList<String[]> resultList = new ArrayList<String[]>();
        ArrayList<Array> tagList = new ArrayList<Array>();

        int columnCount = result.getMetaData().getColumnCount();
        while (result.next()) {
            String[] row = new String[columnCount];
            for (int i = 0; i < columnCount - 1; i++) {
                row[i] = result.getString(i + 1);
            }
            tagList.add(result.getArray(12));
            resultList.add(row);
        }

        int size = resultList.size();
        ArrayList<Hotel> hotels = new ArrayList<Hotel>(size);
        for(int i = 0; i < size; i++) {
            String[] row = resultList.get(i);
            Hotel hotel = createHotel(row);
            String tmp = tagList.get(i).toString();
            tmp = tmp.substring(1, tmp.length()-1);
            String[] tagstmp = tmp.split(",");
            hotel.setTags(tagstmp);

            hotels.add(hotel);
        }
        return hotels.toArray(new Hotel[hotels.size()]);
    }

     /**
      * FInnur hótel sem passar við öll tögin í fylkinu tags
      * @param tags fylki af tögum
      * @return skilar fylki af hótelum sem passa við öll tögin
      * @throws SQLException
      */
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
             tagList.add(result.getArray(12));
             resultList.add(row);
         }

         int size = resultList.size();
         ArrayList<Hotel> hotels = new ArrayList<Hotel>(size);
         for(int i = 0; i < size; i++) {
             String[] row = resultList.get(i);
             Hotel hotel = createHotel(row);
             String tmp = tagList.get(i).toString();
             tmp = tmp.substring(1, tmp.length()-1);
             String[] tagstmp = tmp.split(",");
             hotel.setTags(tagstmp);

             hotels.add(hotel);
         }
         return hotels.toArray(new Hotel[hotels.size()]);

     }


     /**
      * Skilar einhverju handahófskenndu hóteli
      * @return Hotel af handahófi
      * @throws SQLException
      */
    public Hotel getRandomHotelOfTheWeek() throws SQLException {
        Hotel[] hotels = getAllHotels();
        int size = hotels.length;
        int random = (int)(Math.random()*(size-1));
        return hotels[random];
    }

     /**
      * smiður
      */
    public HotelController() {
        dbh = new dbHelper();
    }

     // skilar fylki með öllum umsögnum hótelsins hotel

     /**
      * Nær í allar umsagnir viðeigandi hótels
      * @param hotel
      * @return Review fylki
      * @throws SQLException
      */
    public Review[] getReviews(Hotel hotel) throws SQLException {
        Object[] params = {hotel.getId()};
        ResultSet results = dbh.runQuery("SELECT * FROM review WHERE hotelid = ?", params);
        ArrayList<String[]> resultList = new ArrayList<String[]>();

        int columnCount = results.getMetaData().getColumnCount();
        while (results.next()) {
            String[] row = new String[columnCount];
            for (int i = 1; i < columnCount; i++) {
                row[i-1] = results.getString(i);
            }
            resultList.add(row);
        }
        int size = resultList.size();
        Review[] reviews = new Review[size];
        for(int i=0; i<size; i++) {
            String[] row = resultList.get(i);
            Review review = new Review();
            review.setId(Integer.parseInt(row[0]));
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

     // skilar fylki með öllum herbergjum hótelsins hotel

     /**
      * FInnur öll herbergi tiltekins hótels
      * @param hotel
      * @return Room fylki
      * @throws SQLException
      */
     public Room[] getRooms(Hotel hotel) throws SQLException {
         Object[] params = {hotel.getId()};
         ResultSet results = dbh.runQuery("SELECT * FROM room WHERE hotelid = ?", params);
         ArrayList<String[]> resultList = new ArrayList<String[]>();

         int columnCount = results.getMetaData().getColumnCount();
         while (results.next()) {
             String[] row = new String[columnCount];
             for (int i = 1; i <= columnCount; i++) {
                 row[i-1] = results.getString(i);
             }
             resultList.add(row);
         }
         int size = resultList.size();
         Room[] rooms = new Room[size];
         for(int i=0; i<size; i++) {
             String[] row = resultList.get(i);
             Room room = new Room();
             room.setId(Integer.parseInt(row[0]));
             room.setHotelId(Integer.parseInt(row[1]));
             room.setNumberOfBeds(Integer.parseInt(row[2]));
             room.setSizeOfRoom(Double.parseDouble(row[3]));
             room.setTypeOfBathroom(row[4]);
             room.setRoomNumber(Integer.parseInt(row[5]));
             room.setMaxGuests(Integer.parseInt(row[6]));
             room.setDescription(row[7]);
             room.setRoomPrice(Double.parseDouble(row[8]));
             rooms[i] = room;
         }
         return rooms;
     }

     /**
      * Raðar hótelum eftir verði í hækkandi röð
      * @param hotels
      * @return fylki af Hotel með verð í hækkandi röð
      */
     public Hotel[] sortByPrice(Hotel[] hotels) {
         for (int i=1; i<hotels.length; i++) {
             Hotel temp = hotels[i];
             int j;
             for(j=i-1; j>=0 && temp.getAvgPrice()<hotels[j].getAvgPrice(); j--) {
                 hotels[j+1] = hotels[j];
             }
             hotels[j+1] = temp;
         }
         return hotels;
     }

     // skilar öllum hótelum af sömu týpu

     /**
      * Nær í öll hótel af týpunni type
      * @param type
      * @return Fylki af hótelum með sömu type
      * @throws SQLException
      */
     public Hotel[] getHotelsByType( String type) throws SQLException {
         Object[] params = {type};
         ResultSet results = dbh.runQuery("SELECT * FROM hotel WHERE typeofhotel = ?", params);
         ArrayList<Array> tagList = new ArrayList<Array>();
         ArrayList<String[]> resultList = new ArrayList<String[]>();

         int columnCount = results.getMetaData().getColumnCount();
         while (results.next()) {
             String[] row = new String[columnCount];
             for (int i = 0; i < columnCount - 1; i++) {
                 row[i] = results.getString(i + 1);
             }
             tagList.add(results.getArray(12));
             resultList.add(row);
         }
         int size = resultList.size();
         Hotel[] hotels = new Hotel[size];
         for(int i = 0; i < size; i++) {
             String[] row = resultList.get(i);
             Hotel hotel = createHotel(row);
             String tmp = tagList.get(i).toString();
             tmp = tmp.substring(1, tmp.length()-1);
             String[] tags = tmp.split(",");
             hotel.setTags(tags);

             hotels[i] = hotel;
         }
         return hotels;
     }

     private Hotel createHotel(String[] row) {
         Hotel hotel = new Hotel();
         hotel.setId(Integer.parseInt(row[8]));
         hotel.setName(row[0]);
         hotel.setAddress(row[1]);
         hotel.setType(row[2]);
         hotel.setDescription(row[3]);
         hotel.setPhoneNumber(row[4]);
         hotel.setStarCount(Double.parseDouble(row[5]));
         hotel.setAvgPrice(Double.parseDouble(row[6]));
         hotel.setCheckoutTime(row[7]);
         hotel.setRating(Double.parseDouble((row[9])));
         hotel.setPlace(row[10]);
         return hotel;
     }

 }
