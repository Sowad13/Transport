package sample;

public class Transport {

    String transportPlateNo;
    String className;
    int garageId;
    int capacity;
    String transportCondition;

    public Transport(String transportPlateNo, String className, int garageId, int capacity, String transportCondition) {
        this.transportPlateNo = transportPlateNo;
        this.className = className;
        this.garageId = garageId;
        this.capacity = capacity;
        this.transportCondition = transportCondition;
    }

    public Transport() {
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

    public void setGarageId(int servicingCost) {
        this.garageId = servicingCost;
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
