package clinic.entity;

import java.time.LocalDate;

public class Reservation {

    private int reservation_id;
    private LocalDate reservation_date;
    private String reservation_time;
    private String reservation_doc;
    private String reservation_address;
    private int reservation_patient_id;

    public Reservation(int reservation_id, LocalDate reservation_date, String reservation_time, String reservation_doc, String reservation_address, int reservation_patient_id) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.reservation_time = reservation_time;
        this.reservation_doc = reservation_doc;
        this.reservation_address = reservation_address;
        this.reservation_patient_id = reservation_patient_id;
    }

    public Reservation(LocalDate reservation_date, String reservation_time, String reservation_doc, String reservation_address, int reservation_patient_id) {
        this.reservation_date = reservation_date;
        this.reservation_time = reservation_time;
        this.reservation_doc = reservation_doc;
        this.reservation_address = reservation_address;
        this.reservation_patient_id = reservation_patient_id;
    }

    public Reservation() {
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public LocalDate getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(LocalDate reservation_date) {
        this.reservation_date = reservation_date;
    }

    public String getReservation_time() {
        return reservation_time;
    }

    public void setReservation_time(String reservation_time) {
        this.reservation_time = reservation_time;
    }

    public String getReservation_doc() {
        return reservation_doc;
    }

    public void setReservation_doc(String reservation_doc) {
        this.reservation_doc = reservation_doc;
    }

    public String getReservation_address() {
        return reservation_address;
    }

    public void setReservation_address(String reservation_address) {
        this.reservation_address = reservation_address;
    }

    public int getReservation_patient_id() {
        return reservation_patient_id;
    }

    public void setReservation_patient_id(int reservation_patient_id) {
        this.reservation_patient_id = reservation_patient_id;
    }

    @Override
    public String toString() {
        return  "Date: " + reservation_date + '\n' + "</br>" +
                "Time: " + reservation_time + '\n' + "</br>" +
                "Unit: " + reservation_doc + '\n' + "</br>" +
                "Address: " + reservation_address + '\n' + "</br>"
                ;
    }
}
