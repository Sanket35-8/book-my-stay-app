package usecase4;

import java.util.HashMap;

// Room classes (specific to Use Case 4)
abstract class Room {
    protected String roomType;
    protected int beds;
    protected double pricePerNight;

    public Room(String roomType, int beds, double pricePerNight) {
        this.roomType = roomType;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
    }

    public abstract void displayRoomInfo();
}

class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1, 50.0); }
    @Override
    public void displayRoomInfo() {
        System.out.println(roomType + ": Beds=" + beds + ", Price per Night=$" + pricePerNight);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 90.0); }
    @Override
    public void displayRoomInfo() {
        System.out.println(roomType + ": Beds=" + beds + ", Price per Night=$" + pricePerNight);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 150.0); }
    @Override
    public void displayRoomInfo() {
        System.out.println(roomType + ": Beds=" + beds + ", Price per Night=$" + pricePerNight);
    }
}

// Centralized inventory class (read-only access)
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Read-only inventory display
    public void displayAvailableRooms(Room[] rooms) {
        System.out.println("=== Available Rooms ===");
        for(Room room : rooms) {
            int available = inventory.getOrDefault(room.roomType, 0);
            if(available > 0) {
                room.displayRoomInfo();
                System.out.println("Available: " + available);
            }
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay - Room Search (v4.1) ===");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 10);
        inventory.addRoomType("Double Room", 0); // Not available
        inventory.addRoomType("Suite Room", 2);

        // Initialize room objects
        Room[] rooms = { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };

        // Display only available rooms
        inventory.displayAvailableRooms(rooms);

        System.out.println("=== End of Use Case 4 ===");
    }
}
