package model;

public class Room {
    private Integer id;
    private String roomNumber;
    private Integer roomTypeId;

    // Constructor
    public Room(String roomNumber, Integer roomTypeId) {
        this.roomNumber = roomNumber;
        this.roomTypeId = roomTypeId;
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public Integer getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(Integer roomTypeId) { this.roomTypeId = roomTypeId; }

    @Override
    public String toString() {
        return "Room{id=" + id + ", roomNumber='" + roomNumber + "', roomTypeId=" + roomTypeId + "}";
    }
}