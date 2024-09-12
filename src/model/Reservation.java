package model;
import java.math.BigDecimal;
import java.time.LocalDate;


public class Reservation {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer userId;
    private Integer roomId;
    private Boolean isCancelled;
    private BigDecimal refundAmount;

    // Constructor
    public Reservation(LocalDate startDate, LocalDate endDate, Integer userId, Integer roomId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.roomId = roomId;
        this.isCancelled = false;
        this.refundAmount = BigDecimal.ZERO;
    }

    // Getters and setters
    public Integer getIdReservation() { return id; }
    public void setIdReservation(Integer id) { this.id = id; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getRoomId() { return roomId; }
    public void setRoomId(Integer roomId) { this.roomId = roomId; }
    public Boolean getIsCancelled() { return isCancelled; }
    public void setIsCancelled(Boolean isCancelled) { this.isCancelled = isCancelled; }
    public BigDecimal getRefundAmount() { return refundAmount; }
    public void setRefundAmount(BigDecimal refundAmount) { this.refundAmount = refundAmount; }

    @Override
    public String toString() {
        return "\n_________________________________"
                + "\nReservation id :" + id
                + "\n startDate:" + startDate
                + "\n endDate:" + endDate
                + "\n userId=" + userId
                + "\n roomId=" + roomId
                +"\n_________________________________" ;
    }
}