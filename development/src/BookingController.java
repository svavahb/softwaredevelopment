import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;

/**
 * Created by arnorv on 16/03/16. NEI LYGAR
 */
public class BookingController {
    private Booking[] bookings;
    private dbHelper dbh;


    public void BookingController() {
        dbh = new dbHelper();
    }

    public Booking getBooking(int id) throws SQLException {
        Object[] params = {(Integer) id};
        ResultSet dbresults = dbh.runQuery("SELECT * FROM booking WHERE id=?", params);
        String[] results = new String[9];
        while(dbresults.next()) {
            for(int j=1; j<9; j++) {
                results[j-1] = dbresults.getString(j);
            }
        }
        Booking book = new Booking(Integer.parseInt(results[0]));
        book.setHotelId(Integer.parseInt(results[1]));
        book.setRoomId(Integer.parseInt(results[2]));
        book.setPhoneNr(results[3]);
        book.setCustomerName(results[4]);
        book.setEmail(results[5]);
        book.setCreditCardNr(results[6]);
        book.setStartDate(results[7]);
        book.setStartDate(results[8]);
        return book;
    }

    public void deleteBooking(int id) {
        Object[] params = {(Integer) id};
        dbh.runQuery("DELETE * FROM hotel WHERE id=?", params);
    }

    public Booking[] getBookingsByCustomer(String customerName) throws SQLException {
        Object[] params = {customerName};
        ResultSet results = dbh.runQuery("SELECT * FROM booking WHERE customername=?", params);
        ArrayList<Object[]> resultList = new ArrayList<Object[]>();

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
            Booking book = new Booking(Integer.parseInt((String) resultList.get(j)[0]));
            book.setHotelId(Integer.parseInt((String) resultList.get(j)[1]));
            book.setRoomId(Integer.parseInt((String) resultList.get(j)[2]));
            book.setPhoneNr((String) resultList.get(j)[3]);
            book.setCustomerName((String) resultList.get(j)[4]);
            book.setEmail((String) resultList.get(j)[5]);
            book.setCreditCardNr((String) resultList.get(j)[6]);
            book.setStartDate((String) resultList.get(j)[7]);
            book.setStartDate((String) resultList.get(j)[8]);

            bookings[j] = book;

        }
        return bookings;
    }

    public Booking[] getBookings(Hotel hotel) throws SQLException {
        Object[] params = {hotel.getId()};
        ResultSet results = dbh.runQuery("SELECT * FROM booking WHERE hotelid=?", params);
        ArrayList<Object[]> resultList = new ArrayList<Object[]>();

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
            Booking book = new Booking(Integer.parseInt((String) resultList.get(j)[0]));
            book.setHotelId(Integer.parseInt((String) resultList.get(j)[1]));
            book.setRoomId(Integer.parseInt((String) resultList.get(j)[2]));
            book.setPhoneNr((String) resultList.get(j)[3]);
            book.setCustomerName((String) resultList.get(j)[4]);
            book.setEmail((String) resultList.get(j)[5]);
            book.setCreditCardNr((String) resultList.get(j)[6]);
            book.setStartDate((String) resultList.get(j)[7]);
            book.setStartDate((String) resultList.get(j)[8]);

            bookings[j] = book;
        }
        return null;
    }

    public void saveBooking(Booking booking) {
        Object[] params = {(Integer) Booking.getHotel().getId(), Booking.getRooms()[0].getId(),
                Booking.getPhoneNr(), Booking.getCustomerName(), Booking.getEmail(), Booking.getCreditCardNr(), Booking.getStartDate(), Booking.getEndDate()};
        dbh.runQuery("INSERT INTO hotel(hotelid, roomid, phonenumber, customername, " +
                "email, creditcardnumber, startdate, enddate) " +
                "VALUES ('?','?','?','?','?','?','?','?')", params);
    }
}
