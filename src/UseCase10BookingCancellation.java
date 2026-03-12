package usecase10;

import java.util.*;

// Simple reservation class
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() { return reservationId; }
    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    public void displayReservation() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}

// Manages inventory
class Inventory {
    private Map<String, Integer> roomAvailability = new HashMap<>();

    public void addRoomType(String type, int count) {
        roomAvailability.put(type, count);
    }

    public void incrementRoom(String type) {
        roomAvailability.put(type, roomAvailability.get(type) + 1);
    }

    public void decrementRoom(String type) {
        roomAvailability.put(type, roomAvailability.get(type) - 1);
    }

    public int getAvailability(String type) {
        return roomAvailability.getOrDefault(type, 0);
    }

    public void displayInventory() {
        System.out.println("=== Current Inventory ===");
        roomAvailability.forEach((type, count) -> System.out.println(type + ": " + count));
    }
}

// Handles booking cancellations
class CancellationService {
    private Inventory inventory;
    private List<Reservation> bookingHistory;
    private Stack<String> rollbackRoomIds = new Stack<>();

    public CancellationService(Inventory inventory, List<Reservation> bookingHistory) {
        this.inventory = inventory;
        this.bookingHistory = bookingHistory;
    }

    public void cancelBooking(String reservationId) {
        Reservation toCancel = null;
        for (Reservation r : bookingHistory) {
            if (r.getReservationId().equals(reservationId)) {
                toCancel = r;
                break;
            }
        }

        if (toCancel == null) {
            System.out.println("Cancellation failed: No reservation with ID " + reservationId);
            return;
        }

        bookingHistory.remove(toCancel);
        rollbackRoomIds.push(toCancel.getRoomType());
        inventory.incrementRoom(toCancel.getRoomType());

        System.out.println("Booking cancelled: " + reservationId + ", room type rolled back: " + toCancel.getRoomType());
    }

    public void rollbackLast() {
        if (!rollbackRoomIds.isEmpty()) {
            String roomType = rollbackRoomIds.pop();
            inventory.incrementRoom(roomType);
            System.out.println("Rolled back last room of type: " + roomType);
        }
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay - Booking Cancellation & Inventory Rollback (v10.1) ===");

        Inventory inventory = new Inventory();
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 3);
        inventory.addRoomType("Suite", 2);

        List<Reservation> bookings = new ArrayList<>();
        bookings.add(new Reservation("R101", "Alice", "Single"));
        bookings.add(new Reservation("R102", "Bob", "Double"));
        bookings.add(new Reservation("R103", "Charlie", "Suite"));

        inventory.decrementRoom("Single");
        inventory.decrementRoom("Double");
        inventory.decrementRoom("Suite");

        inventory.displayInventory();

        CancellationService cancelService = new CancellationService(inventory, bookings);
        cancelService.cancelBooking("R102"); // Cancel Bob's booking
        cancelService.cancelBooking("R200"); // Invalid ID
        inventory.displayInventory();

        System.out.println("\n--- Remaining Bookings ---");
        for (Reservation r : bookings) {
            r.displayReservation();
        }

        System.out.println("=== End of Use Case 10 ===");
    }
}