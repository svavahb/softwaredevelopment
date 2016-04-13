//import static org.junit.Assert.*;

/**
 * Created by Svava Hildur on 20.3.2016.
 */
public class HotelControllerTest {
/*
    @org.junit.Before
    public void setUp() throws Exception {
        HotelController hcontroller = new HotelController();
        Hotel testing = new Hotel(7);
        hcontroller.saveHotel(testing);
        Room roomtest = new Room(1);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        hcontroller.deleteHotel(testing);
        hcontroller = null;
    }

    @org.junit.After
    public void testGetHotel() throws Exception {
        Hotel hilton = hcontroller.getHotel("Hilton");
        assertEquals("Hilton", hilton.getName());
    }

    //Tékka hvort það skili error fyrir of stutt input
    @org.junit.Test(expected=IllegalArgumentException.class)
    public void testGetHotelNotTooShort() throws Expection {
        String input = "h";
        Hotel testhotel = hcontroller.getHotel(input);
    }

    //Tékka hvort það skili error fyrir of langt input
    @org.junit.Test(expected=IllegalArgumentException.class)
    public void testGetHotelNotTooLong() throws Expection {
        String input = "aaaabbbbccccddddeeeeffffiiiikkk";
        Hotel testhotel = hcontroller.getHotel(input);
    }

    @org.junit.Test
    public void testGiveReview() throws Exception {
        int oldNumber = testing.getReviews().length;
        hcontroller.giveReview(testing, "palli", "fínt hótel sko", 8);
        int newNumber = testing.getReviews().length;
        assertNotEqual(oldNumber, newNumber);
    }

    @org.junit.Test
    public void testAddRoom() throws Exception {
        int oldNumber = testing.getNumberOfRooms();
        hcontroller.addRoom(testing, roomtest);
        int newNumber = testing.getNumberOfRooms();
        assertNotEqual(oldNumber, newNumber);
    }

    @org.junit.Test
    public void testRemoveRoom() throws Exception {
        int oldNumber = testing.getNumberOfRooms();
        hcontroller.removeRoom(testing, roomtest);
        int newNumber = testing.getNumberOfRooms();
        assertNotEqual(oldNumber, newNumber);
    }

    @org.junit.Test
    public void testFindHotelWithAvailableRooms() throws Exception {
        Date start = Date(2020,05,19);
        Date end = Date(2020,05,20);
        Hotel test = hcontroller.findHotelWithAvailableRooms(start, end, 1, 1, 5000000);
        assertNotNull(test);
    }

    @org.junit.Test
    public void testgetRandomHotelOfTheWeek() throws Exception {
        Hotel testhotel = hcontroller.getRandomHotelOfTheWeek();
        assertNotNull(testhotel);
    }*/
}