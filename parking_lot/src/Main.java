public class Main {

    public static void main(String[] args) throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 101; i++) {
            parkingLot.park("large");
            System.out.println();

            if(i == 99) {
                parkingLot.exit("1");
            }
        }
    }
}