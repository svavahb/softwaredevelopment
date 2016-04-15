import java.util.Date;

/**
 * Created by arnorv on 16/03/16.
 */
public class Booking {
    private int id;
    private static String phoneNr;
    private static String customerName;
    private static String email;
    private static String creditCardNr;
    private static Date startDate;
    private static Date endDate;
    private static Room[] rooms;
    private static Hotel hotel;
    private int hotelId;
    private int roomId;

    //djöfull er halldóra fkn heimsk wow

    //Smiður
    public Booking( int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHotelId(int id) {this.hotelId = id;}

    public static String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getCreditCardNr() {
        return creditCardNr;
    }

    public void setCreditCardNr(String creditCardNr) {
        this.creditCardNr = creditCardNr;
    }

    public static Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public static Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setRoomId(int id) {this.roomId = id;}

    public static Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public static Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
