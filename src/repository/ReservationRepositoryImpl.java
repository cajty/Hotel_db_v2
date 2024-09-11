package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Reservation;
import util.DbConnection;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Connection connection;

    public ReservationRepositoryImpl() {
        DbConnection dbConnection = DbConnection.getInstance();
        this.connection = dbConnection.getConnection();
    }

    @Override
    public void createReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (start_date, end_date, user_id, room_id, is_cancelled, refund_amount) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, Date.valueOf(reservation.getStartDate()));
            stmt.setDate(2, Date.valueOf(reservation.getEndDate()));
            stmt.setInt(3, reservation.getUserId());
            stmt.setInt(4, reservation.getRoomId());
            stmt.setBoolean(5, reservation.getIsCancelled());
            stmt.setBigDecimal(6, reservation.getRefundAmount());

            stmt.executeUpdate();

            // Get generated reservation ID and set it to the Reservation object
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                reservation.getIdReservation(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation getReservationById(int id) {
        String sql = "SELECT * FROM reservations WHERE id = ";
        Reservation reservation = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reservation = new Reservation(
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getInt("user_id"),
                        rs.getInt("room_id")
                );
                reservation.getIdReservation(rs.getInt("id"));
                reservation.setIsCancelled(rs.getBoolean("is_cancelled"));
                reservation.setRefundAmount(rs.getBigDecimal("refund_amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservation;
    }

@Override
public List<Reservation> getAllReservationsOfRoomType(int roomTypeId) {
    String sql = "SELECT * FROM reservations JOIN rooms ON reservations.room_id = rooms.id WHERE rooms.room_type_id = ? AND reservations.is_cancelled = false";
    List<Reservation> reservations = new ArrayList<>();

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, roomTypeId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Reservation reservation = new Reservation(
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    rs.getInt("user_id"),
                    rs.getInt("room_id")
            );
            reservation.setIdReservation(rs.getInt("id"));
            reservation.setIsCancelled(rs.getBoolean("is_cancelled"));
            reservation.setRefundAmount(rs.getBigDecimal("refund_amount"));
            reservations.add(reservation);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return reservations;
}

    @Override
    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET start_date = ?, end_date = ?, user_id = ?, room_id = ?, is_cancelled = ?, refund_amount = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(reservation.getStartDate()));
            stmt.setDate(2, Date.valueOf(reservation.getEndDate()));
            stmt.setInt(3, reservation.getUserId());
            stmt.setInt(4, reservation.getRoomId());
            stmt.setBoolean(5, reservation.getIsCancelled());
            stmt.setBigDecimal(6, reservation.getRefundAmount());
            stmt.setInt(7, reservation.getIdReservation());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
