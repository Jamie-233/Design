import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

public class Ticket {
    public String parkingLocation;
    public Timestamp enter;
    public Timestamp exit;
    public float hourlyRatio;
    public float chargeAmount;
    public long durationHours;
    private ParkingSpot parkingSpot;

    public void calculate() {
        this.exit = Timestamp.from(Instant.now());
        Duration duration = Duration.between(enter.toLocalDateTime(), this.exit.toLocalDateTime());

        this.durationHours = duration.toHours();
        this.chargeAmount = durationHours * hourlyRatio;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public String getParkingLocation() {
        return parkingLocation;
    }

    public void setParkingLocation(String parkingLocation) {
        this.parkingLocation = parkingLocation;
    }

    public void setHourlyRatio(float hourlyRatio) {
        this.hourlyRatio = hourlyRatio;
    }

    public float getHourlyRatio() {
        return hourlyRatio;
    }

    public Timestamp getEnter() {
        return enter;
    }

    public void setEnter(Timestamp enter) {
        this.enter = enter;
    }

    public Timestamp getExit() {
        return exit;
    }

    public float getChargeAmount() {
        return chargeAmount;
    }

    public long getDurationHours() {
        return durationHours;
    }
}
