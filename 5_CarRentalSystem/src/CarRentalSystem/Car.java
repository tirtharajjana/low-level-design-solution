package CarRentalSystem;

public class Car {
    private final String make;
    private final String model;
    private final int year;
    private final String licensePlate;
    private final double rentalPricePerDay;
    private boolean iaAvailable;

    public Car(String make, String model, int year, String licensePlate, double rentalPrice) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.rentalPricePerDay = rentalPrice;
        this.iaAvailable = true;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public boolean isIaAvailable() {
        return iaAvailable;
    }

    public void setIaAvailable(boolean iaAvailable) {
        this.iaAvailable = iaAvailable;
    }
}

