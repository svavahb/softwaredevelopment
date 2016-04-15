//import static org.junit.Assert.*;

/**
 * Created by Svava Hildur on 20.3.2016.
 */
public class BookingControllerTest {

    private BookingController bcontroller;
    private Booking book;
    private Booking testbooking;

    @org.junit.Before
    public void setUp() throws Exception {
        bcontroller = new BookingController();
        book = new Booking(1);
        book.setEmail("palli@gmail.com");
        book.setCustomerName("palli");
        book.setStartDate("2016-05-19");
        book.setEndDate("2016-05-20");
        bcontroller.saveBooking(book);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        bcontroller.deleteBooking(book);
        bcontroller = null;
    }

    @org.junit.Test(expected=IllegalArgumentException.class)
    public void testNoEmptyStringGetBooking() throws Exception {
        testbooking = bcontroller.getBooking("");
    }

    //ath hvort skili error ef við setjum inn streng með bókstöfum í getBooking
    @org.junit.Test(expected=IllegalArgumentException.class)
    public void testNoLettersGetBooking() throws Exception {
        testbooking = bcontroller.getBooking("a");
    }

    //ath hvort skili error ef við setjum inn streng með táknum í getBooking
    @org.junit.Test(expected=IllegalArgumentsException.class)
    public void testNoSpecialCharsGetBooking() throws Exception {
        testbooking = bcontroller.getBooking(".-/");
    }

    //ath hvort skili ekki error ef við setjum inn streng með tölum (aðferðin ætti að parsa það yfir í int)
    @org.junit.Test
    public void testIntStringGetBooking() throws Exception {
        testbooking = bcontroller.getBooking("1");
        assertEquals(testbooking.getId(), 1);
    }

    @org.junit.Test
    public void testgetBooking() throws Exception {
        Booking btest = bcontroller.getBooking(1);
        assertNotNull(btest);
        assertEquals(btest.getId(), 1);
        assertEquals(btest.getCustomerName(),"palli");
        assertEquals(btest.getEmail(),"palli@gmail.com");
        assertEquals(btest.getStartDate(),"2016-05-19");
        assertEquals(btest.getEndDate(),"2016-05-20");
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