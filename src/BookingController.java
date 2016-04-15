import javax.xml.transform.Result;
import java.util.Date;
import java.sql.*;

/**
 * Created by arnorv on 16/03/16. NEI LYGAR
 */
public class BookingController {
    private Booking[] bookings;
    private dbHelper dbh = new dbHelper();


    public void BookingController() {
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
        //book.setStartDate(results[7]);
        //book.setStartDate(results[8]);
        return book;
    }

    public void deleteBooking(int id) {
        Object[] params = {(Integer) id};
        dbh.runQuery("DELETE * FROM hotel WHERE id=?", params);
    }

    public Booking[] getBookingsByCustomer(String customerName) {
        return null;
    }

    public Booking[] getBookings(Hotel hotel) {
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
