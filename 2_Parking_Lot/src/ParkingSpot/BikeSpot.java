package ParkingSpot;

import Vehicle.*;

public class BikeSpot extends  ParkingSpot {
    public BikeSpot(String spotId){
        super(spotId);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getType() == VehicleType.BIKE;
    }
}
