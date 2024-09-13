package repository;

import model.Room;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {
    @Override
    public Optional<Integer> addRoom(Room room) {
        String sql = "INSERT INTO rooms (room_type_id) VALUES (?)";

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, room.getRoomTypeId());
            pstmt.executeUpdate();

            try (var rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    room.setId(generatedId);
                    return Optional.of(generatedId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding room: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getIDRoomToReserve(LocalDate startDate, LocalDate endDate, int roomTypeId) {
        String sql = "SELECT id FROM rooms WHERE id NOT IN (SELECT room_id FROM reservations WHERE start_date <= ? AND end_date >= ?) AND room_type_id = ? LIMIT 1";

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setDate(1, java.sql.Date.valueOf(endDate));
            pstmt.setDate(2, java.sql.Date.valueOf(startDate));
            pstmt.setInt(3, roomTypeId);

            try (var rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting room ID: " + e.getMessage());
        }
        return Optional.empty();
    }
}