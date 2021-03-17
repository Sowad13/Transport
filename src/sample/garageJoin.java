package sample;

public class garageJoin {

    int garageId;
    String garageLocation;
    int garageStaff;
    String staffName;
    String transportPlate;
   // int parkingSpace;

    public garageJoin() {
    }

    public garageJoin(int garageId, String garageLocation, int garageStaff, String staffName, String transportPlate) {
        this.garageId = garageId;
        this.garageLocation = garageLocation;
        this.garageStaff = garageStaff;
        this.staffName = staffName;
        this.transportPlate = transportPlate;
        //this.parkingSpace = parkingSpace;
    }

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public String getGarageLocation() {
        return garageLocation;
    }

    public void setGarageLocation(String garageLocation) {
        this.garageLocation = garageLocation;
    }

    public int getGarageStaff() {
        return garageStaff;
    }

    public void setGarageStaff(int garageStaff) {
        this.garageStaff = garageStaff;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getTransportPlate() {
        return transportPlate;
    }

    public void setTransportPlate(String transportPlate) {
        this.transportPlate = transportPlate;
    }

   /* public int getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(int parkingSpace) {
        this.parkingSpace = parkingSpace;
    }*/
}