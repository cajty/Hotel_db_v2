package repository;

import model.Room;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;


public class RoomRepositoryImpl implements RoomRepository {

    @Override
    public Optional<Integer> addRoom(Room room) {
        String sql = "INSERT INTO rooms (room_number, room_type_id) VALUES (?, ?)";

        // Initialize the ID holder for the inserted room
        Integer generatedId = null;

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            try (PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, room.getRoomNumber());
                pstmt.setInt(2, room.getRoomTypeId());

                // Execute the query
                pstmt.executeUpdate();

                // Retrieve the generated key (ID)
                var rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    room.setId(generatedId); // Set the generated ID in the Room object
                }
            } catch (SQLException e) {
                System.err.println("Error adding room: " + e.getMessage());
                return Optional.empty(); // Return an empty Optional in case of error
            } finally {
                dbConnection.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty(); // Return an empty Optional if connection fails
        }

        // Return the generated ID wrapped in Optional
        return Optional.ofNullable(generatedId);
    }
}
