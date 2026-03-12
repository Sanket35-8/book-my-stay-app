/**
 * ================================================================
 * Use Case 2 - Basic Room Types & Static Availability
 * ================================================================
 *
 * Version: 2.1 (refactored)
 *
 * Description:
 * Demonstrates object-oriented modeling using inheritance and abstraction
 * for hotel room types and their static availability.
 *
 * Author: Sanket35-8
 * Version: 2.1
 */

// Abstract Room class
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

// Concrete room classes
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 50.0);
    }

    @Override
    public void displayRoomInfo() {
        System.out.println(roomType + ": Beds = " + beds + ", Price per Night = $" + pricePerNight);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 90.0);
    }

    @Override
    public void displayRoomInfo() {
        System.out.println(roomType + ": Beds = " + beds + ", Price per Night = $" + pricePerNight);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 150.0);
    }

    @Override
    public void displayRoomInfo() {
        System.out.println(roomType + ": Beds = " + beds + ", Price per Night = $" + pricePerNight);
    }
}

public class UseCase2RoomInitialization {

    // Static availability for each room type
    static int availableSingleRooms = 10;
    static int availableDoubleRooms = 5;
    static int availableSuiteRooms = 2;

    public static void main(String[] args) {
        System.out.println("=== Book My Stay - Room Types & Availability (v2.1) ===");

        // Initialize rooms
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Display room info
        singleRoom.displayRoomInfo();
        System.out.println("Available: " + availableSingleRooms);

        doubleRoom.displayRoomInfo();
        System.out.println("Available: " + availableDoubleRooms);

        suiteRoom.displayRoomInfo();
        System.out.println("Available: " + availableSuiteRooms);

        System.out.println("=== End of Use Case 2 ===");
    }
}
