package sample;

import java.sql.Date;

public class Reserve {

    int reserve_id;
    String renter_name;
    String renter_phone_number;
    int duration;
    Date date;
    String transport_plate;


    public Reserve(int reserve_id, String renter_name, String renter_phone_number, int duration, Date date, String transport_plate) {
        this.reserve_id = reserve_id;
        this.renter_name = renter_name;
        this.renter_phone_number = renter_phone_number;
        this.duration = duration;
        this.date = date;
        this.transport_plate = transport_plate;
    }


    public Reserve(String renter_name, String renter_phone_number, int duration, Date date, String transport_plate) {
        this.renter_name = renter_name;
        this.renter_phone_number = renter_phone_number;
        this.duration = duration;
        this.date = date;
        this.transport_plate = transport_plate;
    }

    public String getTransport_plate() {
        return transport_plate;
    }

    public void setTransport_plate(String transport_plate) {
        this.transport_plate = transport_plate;
    }

    public int getReserve_id() {
        return reserve_id;
    }

    public void setReserve_id(int reserve_id) {
        this.reserve_id = reserve_id;
    }

    public String getRenter_name() {
        return renter_name;
    }

    public void setRenter_name(String renter_name) {
        this.renter_name = renter_name;
    }

    public String getRenter_phone_number() {
        return renter_phone_number;
    }

    public void setRenter_phone_number(String renter_phone_number) {
        this.renter_phone_number = renter_phone_number;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
