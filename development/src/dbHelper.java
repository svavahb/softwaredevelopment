/**
 * Created by Svava Hildur on 20/03/16.
 */
public class dbHelper {
    private String getBookingStr;
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
    }

    public String[] runQuery(String queryStr, String[] params) {

        return params;
    }

    public void setParams(String[] newParams) {
        params = newParams;
    }
}
