import java.util.ArrayList;
import java.sql.*;

/**
 * Created by thordis on 16/03/16.
 */
public class BookingController {
    private Booking[] bookings;
    private dbHelper dbh;
    //klasi sem sér um tenginu við gagnagrunn

    public BookingController() {
        dbh = new dbHelper();
    }

    //leitar að bókun eftir id og skilar henni sem fylki.

    /**
     * Finnur bókun eftir id númeri
     * @param id
     * @return Booking með réttum upplýsingum
     * @throws SQLException
     */
    public Booking getBooking(int id) throws SQLException {
        Object[] params = {id};
        ResultSet dbresults = dbh.runQuery("SELECT * FROM booking WHERE id = ?", params);
        String[] results = new String[9];
        while(dbresults.next()) {
            for(int j=1; j<=9; j++) {
                results[j-1] = dbresults.getString(j);
            }
        }
        Booking book = createBooking(results);
        return book;
    }

    /**
     * Finnur bókun eftir id númeri
     * @param id strengur sem er parsaður yfir í int
     * @return Booking með öllum réttum upplýsingum
     * @throws SQLException
     */
    public Booking getBooking(String id) throws SQLException {
        return getBooking(Integer.parseInt(id));
    }

    /**
     * Eyðir bókun eftir id
     * @param id
     */
    public void deleteBooking(int id) {
        Object[] params = {(Integer) id};
        dbh.runQuery("DELETE FROM booking WHERE id=?", params);
    }
    /**
     * Finnur allar bókanir sem eru skráðir á ákveðinn viðskiptavin
     * @param customerName
     * @return Booking fylki með bókunum viðskiptavinar
     * @throws SQLException
     */
    public Booking[] getBookingsByCustomer(String customerName) throws SQLException {
        Object[] params = {customerName};
        ResultSet results = dbh.runQuery("SELECT * FROM booking WHERE customername=?", params);
        ArrayList<String[]> resultList = new ArrayList<String[]>();

        int columnCount = results.getMetaData().getColumnCount();

        while(results.next()) {
            String[] row = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = results.getString(i + 1);
            }
            resultList.add(row);
        }

        int size = resultList.size();
        Booking[] bookings = new Booking[size];
        for (int j = 0; j < size; j++) {
            String[] row = resultList.get(j);
            Booking book = createBooking(row);
            bookings[j] = book;
        }
        return bookings;
    }


    /**
     * Finnur allar bókanir eins hótels
     * @param hotel
     * @return Booking fylki með bókunum eins hótels
     * @throws SQLException
     */
    public Booking[] getBookings(Hotel hotel) throws SQLException {
        Object[] params = {hotel.getId()};
        ResultSet results = dbh.runQuery("SELECT * FROM booking WHERE hotelid=?", params);
        ArrayList<String[]> resultList = new ArrayList<String[]>();

        int columnCount = results.getMetaData().getColumnCount();

        while(results.next()) {
            String[] row = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = results.getString(i + 1);
            }
            resultList.add(row);
        }

        int size = resultList.size();
        Booking[] bookings = new Booking[size];
        for (int j = 0; j < size; j++) {
            String[] row = resultList.get(j);
            Booking book = createBooking(row);
            bookings[j] = book;
        }
        return bookings;
    }
    // vistar nýja bókun, þarf að taka inn allar upplýsingar um bókunina, sbr dbh.runQuery
    // ef herbergið er þegar bókað, skilar aðferðin null. Annars skilar hún bókunarhlut sem inniheldur
    // allar réttar upplýsingar.

    /**
     * Vistar bókun í gagnagrunni
     * @param booking
     * @return Booking hlut sem hefur allar réttar upplýsingar. Ef herbergið er nú þegar bókað, skilar aðferðin null
     * @throws Exception
     */
    public Booking saveBooking(Booking booking) throws Exception {
        //Object[] param = {booking.getHotelId(), booking.getRoomId(), booking.getStartDate(), booking.getEndDate(), booking.getStartDate(), booking.getEndDate()};
        Object[] param = {1};
        ResultSet result = dbh.runQuery("SELECT * FROM booking WHERE hotelid = ? AND roomid = ? "
                +"AND ((startdate BETWEEN ? AND ?) OR (enddate BETWEEN ? AND ?) AND 1 = ?)", param);
        while(result.next()) {
            if(result.getString(1)!=null) {
                System.out.println("Þetta herbergi er nú þegar bókað");
                return null;
            }
        }

        Object[] params = {booking.getHotelId(), booking.getRoomId(), booking.getPhoneNr(), booking.getCustomerName(),
                booking.getEmail(), booking.getCreditCardNr(), java.sql.Date.valueOf(booking.getStartDate()),
                java.sql.Date.valueOf(booking.getEndDate())};
        dbh.runQuery("INSERT INTO booking(hotelid, roomid, phonenumber, customername, " +
                "email, creditcardnumber, startdate, enddate) " +
                "VALUES (?,?,?,?,?,?,?,?)", params);


        Object[] par = {booking.getHotelId(), booking.getRoomId(), java.sql.Date.valueOf(booking.getStartDate())};
        ResultSet dbresults = dbh.runQuery("SELECT id FROM booking WHERE hotelid = ? AND roomid = ? AND startdate = ?", par);
        while(dbresults.next()) {
            booking.setId(dbresults.getInt(1));
        }
        return booking;
    }

    private Booking createBooking(String[] row) {
        Booking book = new Booking();
        book.setId(Integer.parseInt(row[0]));
        book.setHotelId(Integer.parseInt(row[1]));
        book.setRoomId(Integer.parseInt(row[2]));
        book.setPhoneNr(row[3]);
        book.setCustomerName(row[4]);
        book.setEmail(row[5]);
        book.setCreditCardNr(row[6]);
        book.setStartDate(row[7]);
        book.setStartDate(row[8]);
        return book;
    }
}
