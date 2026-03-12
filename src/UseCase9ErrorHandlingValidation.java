package usecase9;

import java.util.*;

// Custom exception for invalid bookings
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Represents a reservation with validation
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private static final Set<String> VALID_ROOM_TYPES = Set.of("Single", "Double", "Suite");

    public Reservation(String reservationId, String guestName, String roomType) throws InvalidBookingException {
        if (reservationId == null || guestName == null || roomType == null || reservationId.isEmpty() || guestName.isEmpty()) {
            throw new InvalidBookingException("Reservation ID and Guest Name cannot be empty.");
        }
        if (!VALID_ROOM_TYPES.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void displayReservation() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay - Error Handling & Validation (v9.1) ===");

        List<Reservation> bookings = new ArrayList<>();

        try {
            bookings.add(new Reservation("R101", "Alice", "Single"));
            bookings.add(new Reservation("R102", "Bob", "Double"));
            bookings.add(new Reservation("R103", "Charlie", "Penthouse")); // Invalid room type
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

        try {
            bookings.add(new Reservation("", "David", "Suite")); // Missing reservation ID
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

        System.out.println("\n--- Valid Bookings ---");
        for (Reservation r : bookings) {
            r.displayReservation();
        }

        System.out.println("=== End of Use Case 9 ===");
    }
}
