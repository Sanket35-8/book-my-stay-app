package usecase7;

import java.util.*;

// Represents an optional service
class AddOnService {
    private String name;
    private double cost;

    public AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() { return name; }
    public double getCost() { return cost; }

    public void displayService() {
        System.out.println("Service: " + name + " | Cost: $" + cost);
    }
}

// Manages services for reservations
class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
        System.out.println("Added " + service.getName() + " to reservation " + reservationId);
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = reservationServices.getOrDefault(reservationId, Collections.emptyList());
        System.out.println("Reservation ID: " + reservationId + " - Add-On Services:");
        double totalCost = 0;
        for (AddOnService s : services) {
            s.displayService();
            totalCost += s.getCost();
        }
        System.out.println("Total Add-On Cost: $" + totalCost + "\n");
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay - Add-On Service Selection (v7.1) ===");

        AddOnServiceManager manager = new AddOnServiceManager();

        // Simulate adding services to reservations
        manager.addService("R101", new AddOnService("Breakfast", 15.0));
        manager.addService("R101", new AddOnService("Airport Pickup", 30.0));
        manager.addService("R102", new AddOnService("Spa Package", 50.0));

        // Display services and total cost
        manager.displayServices("R101");
        manager.displayServices("R102");
        manager.displayServices("R103"); // Reservation with no services

        System.out.println("=== End of Use Case 7 ===");
    }
}
