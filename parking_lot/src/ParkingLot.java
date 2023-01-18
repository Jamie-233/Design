import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    Map<String, ParkingSpot> largerSpotMap = new HashMap<>();
    Map<String, ParkingSpot> compactSpotMap = new HashMap<>();
    Map<String, ParkingSpot> handicapSpotMap = new HashMap<>();
    Map<String, Ticket> ticketMap = new HashMap<>();

    int availableLargerSpot = 100;
    int availableCompactSpot = 100;
    int availableHandicapSpot = 100;

    int largeId = 0;
    int compactId = 100;
    int handicapId = 200;

    public Ticket park(String vehicleType) throws Exception, ParkingFullException {
        // 1. check do we have enough space?
        if(checkFull(vehicleType)) {
            throw new ParkingFullException();
        }

        // 2. handle park spot update
        // 可以在这里加逻辑 小的 spot 没有查大的 spot
        ParkingSpot parkingSpot = getParkingLot(vehicleType);
        parkSpotUpdate(parkingSpot);

        // 3. handle ticket creation
        Ticket ticket = new Ticket();
        ticket.setEnter(Timestamp.from(Instant.now().minus(3, ChronoUnit.HOURS)));
        ticket.setParkingLocation(parkingSpot.parkingLocation);
        ticket.setHourlyRatio(parkingSpot.hourlyRatio);
        ticket.setParkingSpot(parkingSpot);

        ticketMap.put(parkingSpot.parkingLocation, ticket);

        System.out.println("Welcome Enter parking You enter at time = " + ticket.getEnter());
        // System.out.println("Your vehicle type is = " + vehicleType);
        int count = getAvailableSpot(vehicleType);
        System.out.println("current number of parking spaces available = " + count);

        return ticket;
    }

    public Ticket exit(String parkingLocation) {
        Ticket ticket = ticketMap.get(parkingLocation);

        // 1. update parking spaces
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        parkingSpot.exit(this, parkingSpot);

        // 2. calculate fee
        ticket.calculate();

        System.out.println("You have parked from " + ticket.getEnter() + " to " + ticket.getExit());
        System.out.println("Parking time: " + ticket.getDurationHours() + " hours | Total fee is: $" + ticket.getChargeAmount() + " | hourlyRatio: $" + ticket.getHourlyRatio());
        System.out.println();

        return ticket;
    }

    private void parkSpotUpdate(ParkingSpot parkingSpot) {
        parkingSpot.park(this, parkingSpot);
    }

    private ParkingSpot getParkingLot(String vehicleType) {
        if(vehicleType.equals("large")) {
            return new LargeSpot();
        } else if (vehicleType.equals("compact")) {
            return new CompactSpot();
        } else {
            return new HandicapSpot();
        }
    }

    private int getAvailableSpot(String vehicleType) {
        if(vehicleType.equals("large")) {
            return availableLargerSpot;
        } else if (vehicleType.equals("compact")) {
            return availableCompactSpot;
        } else {
            return availableHandicapSpot;
        }
    }

    private boolean checkFull(String vehicleType) throws Exception {
        if (vehicleType.equals("large")) {
            return availableLargerSpot <= 0;
        } else if (vehicleType.equals("compat")) {
            return availableCompactSpot <= 0;
        } else if(vehicleType.equals("handicap")) {
            return availableHandicapSpot <= 0;
        }

        throw new Exception("Invalid Vehicle type");
    }
}

