package CarRentalSystem;

import CarRentalSystem.Payment.CreditCardPaymentProcessor;
import CarRentalSystem.Payment.PaymentProcessor;

import java.time.LocalDate;
import java.util.*;

public class CarRentalSystem {
    private static CarRentalSystem INSTANCE;
    private final Map<String, Car> cars;
    private final Map<String, Reservation> reservations;
    private final PaymentProcessor paymentProcessor;

    private CarRentalSystem() {
        this.cars = new HashMap<>();
        this.reservations = new HashMap<>();
        this.paymentProcessor = new CreditCardPaymentProcessor();
    }

    public static synchronized CarRentalSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CarRentalSystem();
        }
        return INSTANCE;
    }

    public void addCar(Car car) {
        cars.put(car.getLicensePlate(), car);
    }

    public void removeCar(String licensePlate) {
        cars.remove(licensePlate);
    }

    public List<Car> searchCars(String make, String model, LocalDate startDate, LocalDate endDate) {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars.values()) {
            if (car.getMake().equals(make) && car.getModel().equals(model)) {
                if(isCarAvailable(car, startDate, endDate)) {
                    availableCars.add(car);
                }
            }
        }
        return availableCars;

    }

    private boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate) {
        for(Reservation reservation : reservations.values()) {
            if(reservation.getCar().equals(car)){
                if(startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate())){
                    return false;
                }
            }
        }
        return true;
    }

    public synchronized Reservation makeReservation(Customer customer, Car car, LocalDate startDate, LocalDate endDate) {
        if(isCarAvailable(car, startDate, endDate)) {
            String reservationId = generateReservationId();
            Reservation reservation = new Reservation(reservationId, customer, car, startDate, endDate);
            reservations.put(reservationId, reservation);
            car.setIaAvailable(false);
            return reservation;
        }

        return null;
    }

    public synchronized void cancelReservation(String reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if(reservation != null) {
            reservation.getCar().setIaAvailable(true);
        }
    }

    public boolean processPayment(Reservation reservation){
        return paymentProcessor.processPayment(reservation.getTotalPrice());
    }



    private String generateReservationId() {
        return "RES" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
