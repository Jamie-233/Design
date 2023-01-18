public class LargeSpot extends ParkingSpot {
    public LargeSpot() {
        this.hourlyRatio = 10;
    }

    public void park(ParkingLot parkingLot, ParkingSpot largeSpot) {
        parkingLot.largeId++;
        this.parkingLocation = "" + parkingLot.largeId;

        parkingLot.availableLargerSpot--;
        parkingLot.largerSpotMap.put("" + parkingLot.largeId, largeSpot);
    }

    @Override
    public void exit(ParkingLot parkingLot, ParkingSpot largeSpot) {
        parkingLot.largerSpotMap.remove(largeSpot.parkingLocation);
        parkingLot.availableLargerSpot++;
    }
}
