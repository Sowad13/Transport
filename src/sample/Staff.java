package sample;

public class Staff {

    int idStaff;
    String nameStaff;
    int nidStaff;
    String phoneStaff;
    int garageNo;


    public Staff() {
    }

    public Staff(int idStaff, String nameStaff, int nidStaff, String phoneStaff, int garageNo) {
        this.idStaff = idStaff;
        this.nameStaff = nameStaff;
        this.nidStaff = nidStaff;
        this.phoneStaff = phoneStaff;
        this.garageNo = garageNo;

    }

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public int getNidStaff() {
        return nidStaff;
    }

    public void setNidStaff(int nidStaff) {
        this.nidStaff = nidStaff;
    }

    public String getPhoneStaff() {
        return phoneStaff;
    }

    public void setPhoneStaff(String phoneStaff) {
        this.phoneStaff = phoneStaff;
    }

    public int getGarageNo() {
        return garageNo;
    }

    public void setGarageNo(int garageNo) {
        this.garageNo = garageNo;
    }

}
