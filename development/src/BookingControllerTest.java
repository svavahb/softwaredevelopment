import static org.junit.Assert.*;

/**
 * Created by Svava Hildur on 20.3.2016.
 */
public class BookingControllerTest {

    @org.junit.Before
    public void setUp() throws Exception {
        BookingController bcontroller = new BookingController();
        Booking book = new Booking(1);
        book.setCustomerName("palli");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        bcontroller = null;
    }

    @org.junit.Test
    public void testBookingController() throws Exception {
        assertNotNull(bcontroller);
    }

    @org.junit.Test
    public void testgetBooking() throws Exception {
        Booking btest = bcontroller.getBooking(1);
        assertNotNull(btest);
    }

    @org.junit.Test
    public void testgetBookings() throws Exception {
        Booking[] bfylki = bcontroller.getBookings();
        assertNotNull(bfylki);
    }

    @org.junit.Test
    public void testgetBookingsByCustomer() throws Exception {
        Booking[] pallibook = getBookingsByCustomer("palli");
        assertNotNull(pallibook);
    }
}