package sample;

public class transportEdit {

    String transportPlateNo;
    String className;
    int garageId;
    int capacity;
    String transportCondition;
    boolean svquery;
    boolean insquery;

    public transportEdit() {
    }


    public transportEdit(String transportPlateNo, String className, int garageId, int capacity, String transportCondition, boolean svquery, boolean insquery) {
        this.transportPlateNo = transportPlateNo;
        this.className = className;
        this.garageId = garageId;
        this.capacity = capacity;
        this.transportCondition = transportCondition;
        this.svquery = svquery;
        this.insquery = insquery;
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

    public boolean isSvquery() {
        return svquery;
    }

    public void setSvquery(boolean svquery) {
        this.svquery = svquery;
    }

    public boolean isInsquery() {
        return insquery;
    }

    public void setInsquery(boolean insquery) {
        this.insquery = insquery;
    }
}
