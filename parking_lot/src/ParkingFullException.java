public class ParkingFullException extends RuntimeException {
    public ParkingFullException() {
        super("Parking spaces is full");
    }
}
