package repository;

import model.RoomType;
import util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeRepositoryImpl implements RoomTypeRepository {



    @Override
    public void addRoomType(RoomType roomType) {
        String sql = "INSERT INTO room_types (name, price_per_night) VALUES (?, ?)";

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, roomType.getName());
                pstmt.setDouble(2, roomType.getPricePerNight());

                pstmt.executeUpdate();
            }

            dbConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        List<RoomType> roomTypes = new ArrayList<>();
        String sql = "SELECT id, name, price_per_night FROM room_types";

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    RoomType roomType = new RoomType(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price_per_night")
                    );
                    roomTypes.add(roomType);
                }
            }

            dbConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomTypes;
    }

    @Override
    public RoomType getRoomTypeById(int id) {
        String sql = "SELECT id, name, price_per_night FROM room_types WHERE id = ?";

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, id);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return new RoomType(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price_per_night")
                        );
                    }
                }
            }

            dbConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}