import java.sql.*;
import java.util.*;

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
                //else if(params[i].getClass()==Date.class) {
                 //   stmt.setDate(i+1, (Date) params[i]);
                //}
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
        String queryStr = "SELECT * FROM hotel.public.Hotel WHERE hotelname=?";
        Object[] params = {"Hilton"};
        ArrayList<Array> resultList = new ArrayList<Array>();
        ArrayList<Object[]> bla = new ArrayList<Object[]>();

        ResultSet result = db.runQuery(queryStr, params);

        int columnCount = result.getMetaData().getColumnCount();
        while (result.next()) {
            String[] row = new String[columnCount];
            for (int i = 0; i < columnCount - 1; i++) {
                row[i] = result.getString(i + 1);
            }
            resultList.add(result.getArray(10));
            bla.add(row);
            //nfdjbbgdgbgd
        }
        String tmp = resultList.get(0).toString();
        tmp = tmp.substring(1, tmp.length() - 1);
        String[] dot = tmp.split(",");
        System.out.print(dot[0]);
    }
}