package sample;

public class AvailableTransport {
    String transportPlateNo;
    String className;
    int garageId;
    boolean insurance;
    int capacity;
    String transportCondition;

    public AvailableTransport(String transportPlateNo, String className, int garageId, boolean insurance, int capacity, String transportCondition) {
        this.transportPlateNo = transportPlateNo;
        this.className = className;
        this.garageId = garageId;
        this.insurance = insurance;
        this.capacity = capacity;
        this.transportCondition = transportCondition;
    }


    public String getTransportPlateNo() {
        return transportPlateNo;
    }

    public void setTransportPlateNo(String transportPlateNo) {
        this.transportPlateNo = transportPlateNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTransportCondition() {
        return transportCondition;
    }

    public void setTransportCondition(String transportCondition) {
        this.transportCondition = transportCondition;
    }


}
