package sample;

public class Transport {

    String transportPlateNo;
    String className;
    int garageId;
    boolean insurance;
    int servicingCost;
    boolean servicingQuery;
    int capacity;
    String transportCondition;

    public Transport(String transportPlateNo, String className, int garageId, boolean insurance, int servicingCost, boolean servicingQuery, int capacity, String transportCondition) {
        this.transportPlateNo = transportPlateNo;
        this.className = className;
        this.garageId = garageId;
        this.insurance = insurance;
        this.servicingCost = servicingCost;
        this.servicingQuery = servicingQuery;
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

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public int getServicingCost() {
        return servicingCost;
    }

    public void setServicingCost(int servicingCost) {
        this.servicingCost = servicingCost;
    }

    public boolean isServicingQuery() {
        return servicingQuery;
    }

    public void setServicingQuery(boolean servicingQuery) {
        this.servicingQuery = servicingQuery;
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
