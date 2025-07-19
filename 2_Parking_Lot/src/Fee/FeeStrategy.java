package Fee;

import ParkingTicket.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket ticket);
}
