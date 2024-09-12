package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Reservation;
import util.DbConnection;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Connection connection;

    public ReservationRepositoryImpl() {
        DbConnection dbConnection = DbConnection.getInstance();
        this.connection = dbConnection.getConnection();
    }

    @Override
    public Optional<Integer> createReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (start_date, end_date, user_id, room_id, is_cancelled, refund_amount, prix) " +
                "VALUES (?, ?, ?, ?, ?, ?, (SELECT rt.price_per_night * (DATE_PART('day', ?::date - ?::date)) " +
                "FROM rooms r JOIN room_types rt ON r.room_type_id = rt.id WHERE r.id = ?))";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Set the parameters for the prepared statement
            stmt.setDate(1, Date.valueOf(reservation.getStartDate()));
            stmt.setDate(2, Date.valueOf(reservation.getEndDate()));
            stmt.setInt(3, reservation.getUserId());
            stmt.setInt(4, reservation.getRoomId());
            stmt.setBoolean(5, reservation.getIsCancelled());
            stmt.setBigDecimal(6, reservation.getRefundAmount());

            // These are for the prix calculation
            stmt.setDate(7, Date.valueOf(reservation.getEndDate()));
            stmt.setDate(8, Date.valueOf(reservation.getStartDate()));
            stmt.setInt(9, reservation.getRoomId());

            // Execute the query
            stmt.executeUpdate();

            // Get the generated reservation ID and return it
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int reservationId = generatedKeys.getInt(1);
                reservation.setIdReservation(reservationId);
                return Optional.of(reservationId); // Return the reservation ID wrapped in an Optional
            } else {
                return Optional.empty(); // If no ID was generated, return an empty Optional
            }

        } catch (SQLException e) {
            // Log the exception (you can replace this with proper logging)
            System.err.println("Error creating reservation: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty(); // Return an empty Optional in case of an error
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
                reservation.setIdReservation(rs.getInt("id"));
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


    @Override
    public Integer getRoomTypeIdByReservationID(Integer id) {
        String sql = "SELECT room_type_id FROM reservations JOIN rooms ON reservations.room_id = rooms.id WHERE reservations.id = ?";
        Integer roomTypeId = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                roomTypeId = rs.getInt("room_type_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomTypeId;
    }
}
