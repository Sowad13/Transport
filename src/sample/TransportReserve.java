package sample;

public class TransportReserve {

    int reserveid;
    String rentName;
    String transportNum;
    String driverName;

    public TransportReserve() {
    }

    public TransportReserve(int reserveid, String rentName, String transportNum, String driverName) {
        this.reserveid = reserveid;
        this.rentName = rentName;
        this.transportNum = transportNum;
        this.driverName = driverName;
    }

    public int getReserveid() {
        return reserveid;
    }

    public void setReserveid(int reserveid) {
        this.reserveid = reserveid;
    }

    public String getRentName() {
        return rentName;
    }

    public void setRentName(String rentName) {
        this.rentName = rentName;
    }

    public String getTransportNum() {
        return transportNum;
    }

    public void setTransportNum(String transportNum) {
        this.transportNum = transportNum;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
