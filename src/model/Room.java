package model;

public class Room {
    private Integer id;
    private Integer roomTypeId;

    // Constructors
    public Room() {}

    public Room(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomTypeId=" + roomTypeId +
                '}';
    }
}