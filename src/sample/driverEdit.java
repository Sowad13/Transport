package sample;

public class driverEdit {

    int staffid;

    Boolean mediIns;
    String Driver_license;
    String name;
    String phone;
    String Transport_plate;

    public driverEdit() {
    }

    public driverEdit(int staffid, Boolean mediIns, String driver_license, String name, String phone, String transport_plate) {
        this.staffid = staffid;

        this.mediIns = mediIns;
        Driver_license = driver_license;
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



    public Boolean getMediIns() {
        return mediIns;
    }

    public void setMediIns(Boolean mediIns) {
        this.mediIns = mediIns;
    }

    public String getDriver_license() {
        return Driver_license;
    }

    public void setDriver_license(String driver_license) {
        Driver_license = driver_license;
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
