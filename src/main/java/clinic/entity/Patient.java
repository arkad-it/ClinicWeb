package clinic.entity;

import java.time.LocalDate;

public class Patient {

    private int patient_id;
    private String patient_login;
    private String patient_password;
    private String patient_name;
    private LocalDate patient_dateOfBirth;
    private String patient_phone;
    private String patient_email;

    public Patient(int patient_id, String patient_login, String patient_password, String patient_name, LocalDate patient_dateOfBirth, String patient_phone, String patient_email) {
        this.patient_id = patient_id;
        this.patient_login = patient_login;
        this.patient_password = patient_password;
        this.patient_name = patient_name;
        this.patient_dateOfBirth = patient_dateOfBirth;
        this.patient_phone = patient_phone;
        this.patient_email = patient_email;
    }

    public Patient(String patient_login, String patient_password, String patient_name, LocalDate patient_dateOfBirth, String patient_phone, String patient_email) {
        this.patient_login = patient_login;
        this.patient_password = patient_password;
        this.patient_name = patient_name;
        this.patient_dateOfBirth = patient_dateOfBirth;
        this.patient_phone = patient_phone;
        this.patient_email = patient_email;
    }

    public Patient() {
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_login() {
        return patient_login;
    }

    public void setPatient_login(String patient_login) {
        this.patient_login = patient_login;
    }

    public String getPatient_password() {
        return patient_password;
    }

    public void setPatient_password(String patient_password) {
        this.patient_password = patient_password;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public LocalDate getPatient_dateOfBirth() {
        return patient_dateOfBirth;
    }

    public void setPatient_dateOfBirth(LocalDate patient_dateOfBirth) {
        this.patient_dateOfBirth = patient_dateOfBirth;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    @Override
    public String toString() {
        return  "Login: " + patient_login  + '\n' + "</br>" +
                "Password: " + patient_password  + '\n' + "</br>" +
                "Name: " + patient_name  + '\n' + "</br>" +
                "Date of birth: " + patient_dateOfBirth  + '\n' + "</br>" +
                "Phone: " + patient_phone  + '\n' + "</br>" +
                "Email: " + patient_email  + '\n' + "</br>";
    }
}
