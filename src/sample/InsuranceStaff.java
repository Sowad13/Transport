package sample;

public class InsuranceStaff {

    int staffId;
    String name;
    String nid;
    String phone;

    public InsuranceStaff() {
    }

    public InsuranceStaff(int staffId, String name, String nid, String phone) {
        this.staffId = staffId;
        this.name = name;
        this.nid = nid;
        this.phone = phone;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
