import java.sql.*;

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
                if(params[i]==null) {
                    stmt.setNull(i+1, 0);
                }
                else if(params[i].getClass()==Integer.class) {
                    stmt.setInt(i+1, (Integer) params[i]);
                }
                else if(params[i].getClass()==String.class) {
                    stmt.setString(i+1, (String)params[i]);
                }
                else if(params[i].getClass()==Double.class) {
                    stmt.setDouble(i+1, (Double) params[i]);
                }
                else if(params[i].getClass()==Date.class) {
                    stmt.setDate(i+1, (Date) params[i]);
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
            e.printStackTrace();
            System.exit(0);
        }
        return result;
    }
}