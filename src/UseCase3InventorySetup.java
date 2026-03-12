package usecase3;

import java.util.HashMap;

// Abstract Room class specific to Use Case 3
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

// Centralized inventory class
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() { inventory = new HashMap<>(); }

    public void addRoomType(String roomType, int count) { inventory.put(roomType, count); }

    public int getAvailability(String roomType) { return inventory.getOrDefault(roomType, 0); }

    public void updateAvailability(String roomType, int count) {
        if(inventory.containsKey(roomType)) inventory.put(roomType, count);
    }

    public void displayInventory() {
        System.out.println("=== Current Room Inventory ===");
        for(String roomType : inventory.keySet()) {
            System.out.println(roomType + " - Available: " + inventory.get(roomType));
        }
    }
}

public class UseCase3InventorySetup {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay - Centralized Inventory (v3.1) ===");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 10);
        inventory.addRoomType("Double Room", 5);
        inventory.addRoomType("Suite Room", 2);

        Room single = new SingleRoom();
        Room doubleR = new DoubleRoom();
        Room suite = new SuiteRoom();

        single.displayRoomInfo();
        doubleR.displayRoomInfo();
        suite.displayRoomInfo();

        inventory.displayInventory();
        System.out.println("=== End of Use Case 3 ===");
    }
}