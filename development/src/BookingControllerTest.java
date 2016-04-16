import static org.junit.Assert.*;

public class BookingControllerTest {

    private BookingController bcontroller = new BookingController();
    private HotelController hcontroller;
    private Booking book;
    private Booking testbooking;
    private Hotel hotel;

    @org.junit.Before
    public void setUp() throws Exception {
        hcontroller = new HotelController();
        hotel = hcontroller.getHotel("Hilton");
        book = new Booking();
        book.setEmail("palli@gmail.com");
        book.setHotelId(hotel.getId());
        book.setRoomId(20);
        book.setPhoneNr("894-7896");
        book.setCreditCardNr("4567894512345698");
        book.setCustomerName("palli");
        book.setStartDate("2016-05-19");
        book.setEndDate("2016-05-20");
        book = bcontroller.saveBooking(book);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        bcontroller.deleteBooking(book.getId());
        bcontroller = null;
    }

    @org.junit.Test(expected=IllegalArgumentException.class)
    public void testNoEmptyStringGetBooking() throws Exception {
        testbooking = bcontroller.getBooking(Integer.parseInt(""));
    }

    //ath hvort skili error ef við setjum inn streng með bókstöfum í getBooking
    @org.junit.Test(expected=IllegalArgumentException.class)
    public void testNoLettersGetBooking() throws Exception {
        testbooking = bcontroller.getBooking(Integer.parseInt("a"));
    }

    //ath hvort skili error ef við setjum inn streng með táknum í getBooking
    @org.junit.Test(expected=IllegalArgumentException.class)
    public void testNoSpecialCharsGetBooking() throws Exception {
        testbooking = bcontroller.getBooking(Integer.parseInt(".-/"));
    }

    //ath hvort skili ekki error ef við setjum inn streng með tölum (aðferðin ætti að parsa það yfir í int)
    @org.junit.Test
    public void testIntStringGetBooking() throws Exception {
        String id = ""+book.getId();
        testbooking = bcontroller.getBooking(id);
        assertEquals(testbooking.getId(), book.getId());
    }

    @org.junit.Test
    public void testgetBooking() throws Exception {
        Booking btest = bcontroller.getBooking(book.getId());
        assertNotNull(btest);
        assertEquals(btest.getId(), book.getId());
    }

    @org.junit.Test
    public void testgetBookings() throws Exception {
        Booking[] bfylki = bcontroller.getBookings(hotel);
        assertNotNull(bfylki);
        assertEquals(bfylki[1].getId(), book.getId());
    }

    @org.junit.Test
    public void testgetBookingsByCustomer() throws Exception {
        Booking[] pallibook = bcontroller.getBookingsByCustomer("palli");
        assertNotNull(pallibook);
        assertEquals(pallibook[0].getCustomerName(),"palli");
    }
}