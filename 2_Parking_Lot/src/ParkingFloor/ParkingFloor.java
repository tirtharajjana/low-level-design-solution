package ParkingFloor;

import ParkingSpot.ParkingSpot;
import Vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class ParkingFloor {
    private final int floorNo;
    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNo, List<ParkingSpot> parkingSpots) {
        this.floorNo = floorNo;
        this.parkingSpots = parkingSpots;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public synchronized Optional<ParkingSpot> getAvailableParkingSpot(Vehicle vehicle){
        return parkingSpots.stream()
                .filter(parkingSpot -> parkingSpot.isAvailable() && parkingSpot.canFitVehicle(vehicle))
                .findFirst();
    }
}
