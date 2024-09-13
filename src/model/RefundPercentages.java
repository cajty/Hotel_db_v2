package model;

public class RefundPercentages {
    private Integer id;
    private Integer roomTypeId;
    private Integer daysBeforeCheckin;
    private Integer refundPercentage;

    // Constructors
    public RefundPercentages() {}

    public RefundPercentages(Integer roomTypeId, Integer daysBeforeCheckin, Integer refundPercentage) {
        this.roomTypeId = roomTypeId;
        this.daysBeforeCheckin = daysBeforeCheckin;
        this.refundPercentage = refundPercentage;
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

    public Integer getDaysBeforeCheckin() {
        return daysBeforeCheckin;
    }

    public void setDaysBeforeCheckin(Integer daysBeforeCheckin) {
        this.daysBeforeCheckin = daysBeforeCheckin;
    }

    public Integer getRefundPercentage() {
        return refundPercentage;
    }

    public void setRefundPercentage(Integer refundPercentage) {
        this.refundPercentage = refundPercentage;
    }

    @Override
    public String toString() {
        return "RefundPercentages{" +
                "id=" + id +
                ", roomTypeId=" + roomTypeId +
                ", daysBeforeCheckin=" + daysBeforeCheckin +
                ", refundPercentage=" + refundPercentage +
                '}';
    }
}