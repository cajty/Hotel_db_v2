package model;

public class PriceModifiers {
    private Integer id;
    private String name;
    private Integer roomTypeId;
    private Integer startMonth;
    private Integer startDay;
    private Integer endMonth;
    private Integer endDay;
    private Float priceModifier;
    private String description;

    // Constructors
    public PriceModifiers() {}

    public PriceModifiers(String name, Integer roomTypeId, Integer startMonth, Integer startDay, Integer endMonth, Integer endDay, Float priceModifier, String description) {
        this.name = name;
        this.roomTypeId = roomTypeId;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.priceModifier = priceModifier;
        this.description = description;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getStartDay() {
        return startDay;
    }

    public void setStartDay(Integer startDay) {
        this.startDay = startDay;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public void setEndDay(Integer endDay) {
        this.endDay = endDay;
    }

    public Float getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(Float priceModifier) {
        this.priceModifier = priceModifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PriceModifiers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roomTypeId=" + roomTypeId +
                ", startMonth=" + startMonth +
                ", startDay=" + startDay +
                ", endMonth=" + endMonth +
                ", endDay=" + endDay +
                ", priceModifier=" + priceModifier +
                ", description='" + description + '\'' +
                '}';
    }
}