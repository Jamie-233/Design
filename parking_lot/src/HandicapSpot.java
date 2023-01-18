public class HandicapSpot extends ParkingSpot {

    public HandicapSpot() {
        this.hourlyRatio = 2;
    }

    public void park(ParkingLot parkingLot, ParkingSpot handicapSpot) {
        parkingLot.handicapId++;
        this.parkingLocation = "" + parkingLot.handicapId;

        parkingLot.availableHandicapSpot--;
        parkingLot.compactSpotMap.put("" + parkingLot.handicapId, handicapSpot);
    }

    @Override
    public void exit(ParkingLot parkingLot, ParkingSpot handicapSpot) {
        parkingLot.handicapSpotMap.remove(handicapSpot.parkingLocation);
        parkingLot.availableHandicapSpot++;
    }
}
