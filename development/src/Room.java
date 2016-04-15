/**
 * Created by Svava Hildur on 16/03/16.
 */
public class Room {
    private int numberOfBeds;
    private int hotelid;
    private double sizeOfRoom;
    private String typeOfBathroom;
    private int roomNumber;
    private int id;
    private int maxGuests;
    private String description;
    private int roomPrice;

    public Room(int id) {
        this.id = id;
    }

    public void setHotelId (int hotelid){ this.hotelid = hotelid;}

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public double getSizeOfRoom() {
        return sizeOfRoom;
    }

    public void setSizeOfRoom(double sizeOfRoom) {
        this.sizeOfRoom = sizeOfRoom;
    }

    public String getTypeOfBathroom() {
        return typeOfBathroom;
    }

    public void setTypeOfBathroom(String typeOfBathroom) {
        this.typeOfBathroom = typeOfBathroom;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getId() { return id;}
}
