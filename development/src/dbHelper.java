import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Created by Svava Hildur on 20/03/16.
 */
public class dbHelper {
    /*private String getBookingStr;
    private String saveBookingStr;
    private String getHotelStr;
    private String saveHotelStr;
    private String getReviewStr;
    private String saveReviewStr;
    private String getRoomStr;
    private String saveRoomStr;
    private String[] params;

    public dbHelper() {
        //Skrifa alla query strengi hér!! ah
        getBookingStr = "SELECT * from hotel.public.Hotel WHERE hotelname='blabla'";

    }

    public ResultSet runQuery(String queryStr, String[] params) {

        Connection c = null;
        Statement stmt = null;
        ResultSet result = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/hotel",
                            "postgres", "rugludallur");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = queryStr;
            result = sstmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public void setParams(String[] newParams) {
        params = newParams;
    }
*/
    public static void main(String[] args) {
        //dbHelper db = new dbHelper();
        System.out.println("bla");

        //db.runQuery("INSERT INTO hotel.public.Hotel (hotelname) values ('blub')");

    }
}
