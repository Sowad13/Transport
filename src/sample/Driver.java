package sample;

public class Driver {

    int staffid;
    int nid;
    String stafftype;
    int conductorid;
    String Driver_license;
    boolean Insurance;
    String name;
    String phone;
    String Transport_plate;

    public Driver(int staffid, int nid, String driver_license, boolean insurance, String name, String phone, String transport_plate) {
        this.staffid = staffid;
        this.nid = nid;
        Driver_license = driver_license;
        Insurance = insurance;
        this.name = name;
        this.phone = phone;
        Transport_plate = transport_plate;
    }

    public Driver(int staffid, int nid, String stafftype, int conductorid, String driver_license, boolean insurance, String name, String phone, String transport_plate) {
        this.staffid = staffid;
        this.nid = nid;
        this.stafftype = stafftype;
        this.conductorid = conductorid;
        Driver_license = driver_license;
        Insurance = insurance;
        this.name = name;
        this.phone = phone;
        Transport_plate = transport_plate;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getStafftype() {
        return stafftype;
    }

    public void setStafftype(String stafftype) {
        this.stafftype = stafftype;
    }

    public int getConductorid() {
        return conductorid;
    }

    public void setConductorid(int conductorid) {
        this.conductorid = conductorid;
    }

    public String getDriver_license() {
        return Driver_license;
    }

    public void setDriver_license(String driver_license) {
        Driver_license = driver_license;
    }

    public boolean isInsurance() {
        return Insurance;
    }

    public void setInsurance(boolean insurance) {
        Insurance = insurance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTransport_plate() {
        return Transport_plate;
    }

    public void setTransport_plate(String transport_plate) {
        Transport_plate = transport_plate;
    }

}
