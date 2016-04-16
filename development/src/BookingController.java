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


    public BookingController() {
        dbh = new dbHelper();
    }

    public Booking getBooking(int id) throws SQLException {
        Object[] params = {id};
        ResultSet dbresults = dbh.runQuery("SELECT * FROM booking WHERE id = ?", params);
        String[] results = new String[9];
        while(dbresults.next()) {
            for(int j=1; j<=9; j++) {
                results[j-1] = dbresults.getString(j);
            }
        }
        Booking book = new Booking();
        book.setId(Integer.parseInt(results[0]));
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

    public Booking getBooking(String id) throws SQLException {
        return getBooking(Integer.parseInt(id));
    }

    public void deleteBooking(int id) {
        Object[] params = {(Integer) id};
        dbh.runQuery("DELETE FROM booking WHERE id=?", params);
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
            Booking book = new Booking();
            book.setId(Integer.parseInt((String) resultList.get(j)[0]));
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
            bookings[j] = book;
        }
        return bookings;
    }

    public Booking saveBooking(Booking booking) throws SQLException {
        Object[] params = {Booking.getHotelId(), Booking.getRoomId(), Booking.getPhoneNr(), Booking.getCustomerName(),
                Booking.getEmail(), Booking.getCreditCardNr(), java.sql.Date.valueOf(Booking.getStartDate()),
                java.sql.Date.valueOf(Booking.getEndDate())};
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



    public static void main(String[] args) throws SQLException {
        BookingController bcontroller = new BookingController();
        HotelController hcontroller = new HotelController();
        Booking book = bcontroller.getBooking(5);
        Hotel holt = hcontroller.getHotel("Hotel Holt");
        System.out.println(holt);
        Booking[] books = bcontroller.getBookings(holt);
        System.out.print(books);
    }
}
