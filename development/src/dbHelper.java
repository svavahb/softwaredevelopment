import java.sql.*;
import java.util.*;
import java.util.Arrays;

/**
 * Created by Svava Hildur on 20/03/16.
 */
public class dbHelper {

    public ResultSet runQuery(String queryStr, Object[] params) {
        //fokkju
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/hotel",
                            "postgres", "lalli");
            System.out.println("Opened database successfully");

            stmt = c.prepareStatement(queryStr);
            for(int i=0; i<params.length; i++) {
                if(params[i].getClass()==Integer.class) {
                    stmt.setInt(i+1, (Integer) params[i]);
                }
                else if(params[i].getClass()==String.class) {
                    stmt.setString(i+1, (String)params[i]);
                }
                else if(params[i].getClass()==Double.class) {
                    stmt.setDouble(i+1, (Double) params[i]);
                }
            }
            if(queryStr.charAt(0)=='S') {
                result = stmt.executeQuery();
            }
            else {
                stmt.executeUpdate();
            }
            //stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return result;
    }

    public static void main(String[] args) throws SQLException {
        dbHelper db = new dbHelper();
        Hotel hotel = new Hotel();
        hotel.setName("hilton");
        hotel.setAddress("bla");
        hotel.setType("business");
        hotel.setAvgPrice(12.5);
        hotel.setCheckoutTime("12:00");
        hotel.setDescription("fínt");
        hotel.setPhoneNumber("521-6978");
        hotel.setRating(8.9);
        hotel.setStarCount(3.5);
        String[] tags = {"a","b","c"};
        hotel.setTags(tags);
        String tagstring = Arrays.toString(tags);
        tagstring = tagstring.substring(1,tagstring.length()-1);
        tagstring = "'{"+tagstring+"}');";
        Object[] params = {hotel.getName(), hotel.getAddress(), hotel.getType(), hotel.getDescription(), hotel.getPhoneNumber(),
                hotel.getStarCount(), hotel.getAvgPrice(), hotel.getCheckoutTime()};
        String queryStr = "INSERT INTO hotel(hotelname, address, typeofhotel, " +
                "description, phonenumber, starcount, avgprice, checkouttime, " +
                "tags) VALUES(?, ?, ?, ?, ?, ?, " +
                "?, ?, "+ tagstring;
        //System.out.print(queryStr);
        db.runQuery(queryStr, params);
    }
}