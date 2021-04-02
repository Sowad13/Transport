package sample;

public class staffEdit {


        int idStaff;
        String nameStaff;
        Boolean medins;
        String phoneStaff;
        int garageNo;

    public staffEdit() {
    }

    public staffEdit(int idStaff, String nameStaff, Boolean medins, String phoneStaff, int garageNo) {
        this.idStaff = idStaff;
        this.nameStaff = nameStaff;
        this.medins = medins;
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

    public Boolean getMedins() {
        return medins;
    }

    public void setMedins(Boolean medins) {
        this.medins = medins;
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
