package ParkingSpot;

import Vehicle.Vehicle;

public abstract class ParkingSpot {
    private final String spotId;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpot(String spotId){
        this.spotId = spotId;
        this.isOccupied = false;
    }

    public synchronized boolean isAvailable(){
        return !isOccupied;
    }

    public synchronized boolean assignVehicle(Vehicle vehicle){
        if(this.isOccupied){
            return false;
        }

        this.vehicle = vehicle;
        this.isOccupied = true;
        return true;
    }

    public synchronized void removeVehicle(){
        this.vehicle = null;
        this.isOccupied = false;
    }

    public Vehicle getVehicle(){
        return this.vehicle;
    }

    public String getSpotId(){
        return this.spotId;
    }

    public abstract boolean canFitVehicle(Vehicle vehicle);



}
