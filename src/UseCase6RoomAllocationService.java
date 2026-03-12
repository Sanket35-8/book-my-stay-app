package usecase6;

import java.util.*;

// Reservation request class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

// Booking queue using FIFO
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.add(r);
    }

    public Reservation pollRequest() {
        return queue.poll();
    }

    public boolean hasPendingRequests() {
        return !queue.isEmpty();
    }
}

// Inventory class with room availability
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void decrementAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("=== Inventory ===");
        for(String room : inventory.keySet()) {
            System.out.println(room + " - Available: " + inventory.get(room));
        }
    }
}

// Room allocation service
class RoomAllocationService {
    private RoomInventory inventory;
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private int roomIdCounter = 100; // simple room ID generator

    public RoomAllocationService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void allocateRoom(Reservation r) {
        String roomType = r.getRoomType();
        if(!inventory.isAvailable(roomType)) {
            System.out.println("No rooms available for " + r.getGuestName() + " (" + roomType + ")");
            return;
        }

        // Generate unique room ID
        String roomId = roomType.substring(0, 1).toUpperCase() + roomIdCounter++;
        allocatedRooms.putIfAbsent(roomType, new HashSet<>());
        Set<String> ids = allocatedRooms.get(roomType);
        ids.add(roomId);

        // Decrement inventory
        inventory.decrementAvailability(roomType);

        System.out.println("Reservation confirmed for " + r.getGuestName() +
                " | Room Type: " + roomType + " | Room ID: " + roomId);
    }
}

public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay - Room Allocation Service (v6.1) ===");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 2);
        inventory.addRoomType("Double Room", 1);
        inventory.addRoomType("Suite Room", 1);

        BookingRequestQueue queue = new BookingRequestQueue();
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Double Room"));
        queue.addRequest(new Reservation("David", "Suite Room"));
        queue.addRequest(new Reservation("Eve", "Single Room")); // Will fail due to no availability

        RoomAllocationService allocationService = new RoomAllocationService(inventory);

        // Process booking requests
        while(queue.hasPendingRequests()) {
            allocationService.allocateRoom(queue.pollRequest());
        }

        System.out.println("\nFinal Inventory:");
        inventory.displayInventory();

        System.out.println("=== End of Use Case 6 ===");
    }
}
