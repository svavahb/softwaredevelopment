import static org.junit.Assert.*;

/**
 * Created by Svava Hildur on 20.3.2016.
 */
public class BookingControllerTest {

    @org.junit.Before
    public void setUp() throws Exception {
        BookingController bcontroller = new BookingController();
        Booking book = new Booking(1);
        book.setEmail("palli@gmail.com");
        book.setCustomerName("palli");
        book.setStartDate(Date(2016,05,19));
        book.setEndDate(Date(2016,05,20));
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
        assertEquals(btest.getId(), 1);
        assertEquals(btest.getCustomerName(),"palli");
        assertEquals(btest.getEmail(),"palli@gmail.com");
        assertEquals(btest.getStartDate(),Date(2016,05,19));
        assertEquals(btest.getEndDate(),Date(2016,05,20));
    }

    @org.junit.Test
    public void testgetBookings() throws Exception {
        Booking[] bfylki = bcontroller.getBookings();
        assertNotNull(bfylki);
        assertEqual(bfylki[0].getId(), 1);
    }

    @org.junit.Test
    public void testgetBookingsByCustomer() throws Exception {
        Booking[] pallibook = bcontroller.getBookingsByCustomer("palli");
        assertNotNull(pallibook);
        assertEquals(pallibook[0].getCustomerName(),"palli");
    }
}