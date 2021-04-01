package sample;

public class StateTransport {
    String plateNumber;
    boolean insurance;
    boolean servicingQuery;
    int servicingCost;
    String condition;

    public StateTransport() {
    }

    public StateTransport(String plateNumber, boolean insurance, boolean servicingQuery, int servicingCost, String condition) {
        this.plateNumber = plateNumber;
        this.insurance = insurance;
        this.servicingQuery = servicingQuery;
        this.servicingCost = servicingCost;
        this.condition = condition;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public boolean isServicingQuery() {
        return servicingQuery;
    }

    public void setServicingQuery(boolean servicingQuery) {
        this.servicingQuery = servicingQuery;
    }

    public int getServicingCost() {
        return servicingCost;
    }

    public void setServicingCost(int servicingCost) {
        this.servicingCost = servicingCost;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
