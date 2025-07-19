package ParkingSpot;

import Vehicle.Vehicle;
import Vehicle.VehicleType;

public class LargeSpot extends ParkingSpot{

    public LargeSpot(String spotId){
        super(spotId);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getType() == VehicleType.TRUCK;
    }
}
