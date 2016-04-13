import java.sql.*;

/**
 * Created by Svava Hildur on 20/03/16.
 */
public class dbHelper {
    private static String getBookingStr;
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
            result = stmt.executeQuery(sql);
            //stmt.close();
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

    public static void main(String[] args) throws SQLException {
        dbHelper db = new dbHelper();
        ResultSet result = db.runQuery(getBookingStr, new String[]{"bla"});
        while (result.next()) {
            System.out.println(result.getString(1));
        }
    }
}