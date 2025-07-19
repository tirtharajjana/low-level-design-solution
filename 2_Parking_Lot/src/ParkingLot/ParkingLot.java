package ParkingLot;

import Fee.FeeStrategy;
import Fee.FlatRateFeeStrategy;
import ParkingFloor.ParkingFloor;
import ParkingSpot.ParkingSpot;
import ParkingTicket.ParkingTicket;
import Vehicle.Vehicle;

import java.util.*;

public class ParkingLot {
    private static final ParkingLot INSTANCE = new ParkingLot();
    private final List<ParkingFloor> floors = new ArrayList<>();
    private final Map<String, ParkingTicket> activeTickets = new HashMap<>();
    private FeeStrategy feeStrategy;

    public ParkingLot() {
        this.feeStrategy = new FlatRateFeeStrategy();;
    }

    public static synchronized ParkingLot getInstance(){
        return INSTANCE;
    }

    public void addFloor(ParkingFloor floor){
        floors.add(floor);
    }

    public void setFeeStrategy(FeeStrategy feeStrategy){
        this.feeStrategy = feeStrategy;
    }

    public synchronized ParkingTicket parkVehicle(Vehicle vehicle) throws  Exception {
        for( ParkingFloor floor : floors){
            Optional<ParkingSpot> parkingSpot = floor.getAvailableParkingSpot(vehicle);

            if(parkingSpot.isPresent()){
                ParkingSpot spot = parkingSpot.get();
                if(spot.assignVehicle(vehicle)){
                    ParkingTicket parkingTicket = new ParkingTicket(vehicle, spot);
                    activeTickets.put(vehicle.getLicensePlate() , parkingTicket);
                    return parkingTicket;
                }
            }
        }

        throw new Exception("No available spot for " + vehicle.getType());
    }

    public synchronized double removeVehicle(String license) throws Exception{
        ParkingTicket parkingTicket = activeTickets.get(license);
        if(parkingTicket == null){
            if (parkingTicket == null) throw new Exception("Ticket not found");
        }

        parkingTicket.getSpot().removeVehicle();
        parkingTicket.setExitTimestamp();

        return feeStrategy.calculateFee(parkingTicket);

    }
}
