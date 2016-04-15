import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class HotelControllerTest {

    private HotelController hcontroller;
    private Hotel testing;
    private Room roomtest;

    @Before
    public void setUp() throws Exception {
        hcontroller = new HotelController();
        testing = new Hotel();
        testing.setName("test");
        testing.setAddress("test");
        testing.setType("test");
        testing.setAvgPrice(0.0);
        testing.setCheckoutTime("test");
        testing.setDescription("test");
        testing.setPhoneNumber("test");
        testing.setRating(0.0);
        testing.setStarCount(0.0);
        String[] tags = {"t","e","s","t"};
        testing.setTags(tags);
        testing = hcontroller.saveHotel(testing);
        roomtest = new Room();
        roomtest.setHotelId(testing.getId());
        roomtest.setNumberOfBeds(2);
        roomtest.setSizeOfRoom(3);
        roomtest.setTypeOfBathroom("test");
        roomtest.setRoomNumber(215);
        roomtest.setMaxGuests(2);
        roomtest.setDescription("test");
        roomtest.setRoomPrice(5.0);
    }

    @After
    public void tearDown() throws Exception {

        hcontroller.deleteHotel(testing);
        hcontroller = null;
    }

    @Test
    public void testGetHotel() throws Exception {
        Hotel test = hcontroller.getHotel("test");
        assertEquals("test", test.getName());
    }

    //Tékka hvort það skili error fyrir of stutt input
    @org.junit.Test(expected=IllegalArgumentException.class)
    public void testGetHotelNotTooShort() throws Exception {
        String input = "h";
        Hotel testhotel = hcontroller.getHotel(input);
    }

    //Tékka hvort það skili error fyrir of langt input
    @org.junit.Test(expected=IllegalArgumentException.class)
    public void testGetHotelNotTooLong() throws Exception {
        String input = "aaaabbbbccccddddeeeeffffiiiikkk";
        Hotel testhotel = hcontroller.getHotel(input);
    }

    @Test
    public void testGiveReview() throws Exception {
        int oldNumber = testing.getReviews().length;
        hcontroller.giveReview(testing, "pallim", "fínt hótel sko", 8.0, "2016-05-21");
        int newNumber = testing.getReviews().length;
        assertNotEquals(oldNumber, newNumber);
    }

    @Test
    public void testAddRoom() throws Exception {
        int oldNumber = testing.getNumberOfRooms();
        hcontroller.addRoom(testing, roomtest);
        int newNumber = testing.getNumberOfRooms();
        assertNotEquals(oldNumber, newNumber);
    }

    @Test
    public void testRemoveRoom() throws Exception {
        roomtest = hcontroller.addRoom(testing, roomtest);
        int oldNumber = testing.getRooms().length;
        hcontroller.removeRoom(testing, roomtest);
        int newNumber = testing.getRooms().length;
        assertNotEquals(oldNumber, newNumber);
    }

    @Test
    public void testFindHotelsWithAvailableRooms() throws Exception {
        String start = "2020-5-19";
        String end = "2020-5-20";
        Hotel[] test = hcontroller.findHotelWithAvailableRooms(start, end, 1, 5000000);
        assertNotNull(test);
    }

    @org.junit.Test
    public void testgetRandomHotelOfTheWeek() throws Exception {
        Hotel testhotel = hcontroller.getRandomHotelOfTheWeek();
        assertNotNull(testhotel);
    }
}