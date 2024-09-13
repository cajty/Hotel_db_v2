package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Reservation;
import util.DbConnection;

public class ReservationRepositoryImpl implements ReservationRepository {

    @Override
    public boolean createReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (user_id, room_id, start_date, end_date, total_price, is_cancelled, refund_amount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setReservationParameters(stmt, reservation);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int reservationId = generatedKeys.getInt(1);
                    reservation.setId(reservationId);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating reservation: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Reservation getReservationById(int id) {
        String sql = "SELECT * FROM reservations WHERE id = ?";

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createReservationFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reservation> getAllReservationsOfRoomType(int roomTypeId) {
        String sql = "SELECT * FROM reservations JOIN rooms ON reservations.room_id = rooms.id WHERE rooms.room_type_id = ? AND reservations.is_cancelled = false";
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roomTypeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reservations.add(createReservationFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public List<Reservation> getAllReservationsOfUser(int userId) {
        String sql = "SELECT * FROM reservations JOIN rooms ON reservations.room_id = rooms.id WHERE reservations.user_id = ? AND reservations.is_cancelled = false";
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reservations.add(createReservationFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }


    @Override
    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET user_id = ?, room_id = ?, start_date = ?, end_date = ?, total_price = ?, is_cancelled = ?, refund_amount = ? WHERE id = ?";

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            setReservationParameters(stmt, reservation);
            stmt.setInt(8, reservation.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelledReservation(int id) {
        String sql = "UPDATE reservations SET is_cancelled = true WHERE id = ?";

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getRoomTypeIdByReservationID(Integer id) {
        String sql = "SELECT room_type_id FROM reservations JOIN rooms ON reservations.room_id = rooms.id WHERE reservations.id = ?";

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("room_type_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setReservationParameters(PreparedStatement stmt, Reservation reservation) throws SQLException {
        stmt.setInt(1, reservation.getUserId());
        stmt.setInt(2, reservation.getRoomId());
        stmt.setDate(3, java.sql.Date.valueOf(reservation.getStartDate()));
        stmt.setDate(4, java.sql.Date.valueOf(reservation.getEndDate()));
        stmt.setFloat(5, reservation.getTotalPrice());
        stmt.setBoolean(6, reservation.getIsCancelled());
        stmt.setFloat(7, reservation.getRefundAmount());
    }

    private Reservation createReservationFromResultSet(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation(
                rs.getInt("user_id"),
                rs.getInt("room_id"),
                rs.getDate("start_date").toLocalDate(),
                rs.getDate("end_date").toLocalDate(),
                rs.getFloat("total_price"),
                rs.getBoolean("is_cancelled"),
                rs.getFloat("refund_amount")
        );
        reservation.setId(rs.getInt("id"));
        return reservation;
    }
}