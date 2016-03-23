import static org.junit.Assert.*;

/**
 * Created by Svava Hildur on 20.3.2016.
 */
public class HotelControllerTest {

    @org.junit.Before
    public void setUp() throws Exception {
        HotelController hcontroller = new HotelController();
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testGetHotel() throws Exception {
        Hotel hilton = hcontroller.getHotel("Hilton");
        assertEquals("Hilton", hilton.getName());
    }

    @org.junit.Test
    public void testSaveHotel() throws Exception {

    }

    @org.junit.Test
    public void testDeleteHotel() throws Exception {

    }

    @org.junit.Test
    public void testGiveReview() throws Exception {

    }

    @org.junit.Test
    public void testAddRoom() throws Exception {

    }

    @org.junit.Test
    public void testRemoveRoom() throws Exception {

    }

    @org.junit.Test
    public void testUpdateRoom() throws Exception {

    }

    @org.junit.Test
    public void testFindHotelWithAvailableRooms() throws Exception {

    }
}