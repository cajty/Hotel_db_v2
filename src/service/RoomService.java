package service;

import model.RoomType;
import repository.RoomTypeRepository;
import repository.RoomTypeRepositoryImpl;

import java.util.List;

public class RoomService {
    private RoomTypeRepository roomTypeRepository;

    public  RoomService() {
        this.roomTypeRepository = new RoomTypeRepositoryImpl();
    }



    public void addRoomType(String name, double pricePerNight) {
        RoomType newRoomType = new RoomType(1, name, pricePerNight);

        roomTypeRepository.addRoomType(newRoomType);
    }

    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.getAllRoomTypes();
    }

    public RoomType getRoomTypeById(int id) {
        return roomTypeRepository.getRoomTypeById(id);
    }

    public boolean isRoomTypeAvailable(int id) {
        RoomType roomType = getRoomTypeById(id);
        return roomType != null;
    }

    public double calculateTotalPrice(int roomTypeId, int numberOfNights) {
        RoomType roomType = getRoomTypeById(roomTypeId);
        if (roomType == null) {
            throw new IllegalArgumentException("Room type not found");
        }
        return roomType.getPricePerNight() * numberOfNights;
    }


    public void addrooms(){
        System.out.println("number of room");
        System.out.println("type of that room");


    }
}