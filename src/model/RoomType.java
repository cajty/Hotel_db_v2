package model;

public class RoomType {
    private Integer id;
    private String name;
    private Float  pricePerNight;

    // Constructor
    public RoomType(String name, Float  pricePerNight) {
        this.name = name;
        this.pricePerNight = pricePerNight;
    }

    public RoomType(int id, String name, double pricePerNight) {
        this.id = id;
        this.name = name;
        this.pricePerNight = (float)pricePerNight;
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Float  getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(Float  pricePerNight) { this.pricePerNight = pricePerNight; }

    @Override
    public String toString() {
        return "RoomType{id=" + id + ", name='" + name + "', pricePerNight=" + pricePerNight + "}";
    }
}