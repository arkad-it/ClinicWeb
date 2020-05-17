package clinic.dbutil;

import clinic.entity.Patient;
import clinic.entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;

public class DButilAdmin extends DButil{

    private String URL;
    private String name;
    private String password;

    public DButilAdmin(String URL) {
        this.URL = URL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //-----------------------------------------------------------------------------------------------RESERVATIONS
    public List<Reservation> getReservations() throws Exception {

        List<Reservation> reservations = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // DB connect
            conn = DriverManager.getConnection(URL, name, password);

            // SELECT
            String sql = "SELECT * FROM reservation;";
            statement = conn.createStatement();

            // SQL execute
            resultSet = statement.executeQuery(sql);

            // Result mapping
            while (resultSet.next()) {

                // progressively read rows

                int reservation_id = resultSet.getInt("reservation_id");
                // SQL-Date to java-LocalDate------------------------------------------------
                LocalDate reservation_date;
                java.util.Date dateFrom = null;
                Timestamp timestamp = resultSet.getTimestamp("reservation_date");
                if (timestamp != null){
                    dateFrom = new java.util.Date(timestamp.getTime());
                }

                assert dateFrom != null;
                reservation_date = dateFrom.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                // SQL-Date to java-LocalDate------------------------------------------------
                String reservation_time = resultSet.getString("reservation_time");
                String reservation_doc = resultSet.getString("reservation_doc");
                String reservation_address = resultSet.getString("reservation_address");
                int reservation_patient_id = resultSet.getInt("reservation_patient_id");

                // add new res obj to list
                reservations.add(new Reservation(reservation_id, reservation_date, reservation_time, reservation_doc,
                        reservation_address, reservation_patient_id));
            }

        } finally {
            // close JDBC
            close(conn, statement, resultSet);
        }
        return reservations;
    }

    public Reservation getReservationById (int input_patientID) throws Exception {

        Reservation reservation = null;

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            // ID
            int reservation_id = input_patientID;

            // DB connect
            conn = DriverManager.getConnection(URL, name, password);

            // SELECT query
            String sql = "SELECT * FROM reservation WHERE reservation_patient_id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, reservation_id);

            // execute query
            resultSet = statement.executeQuery();

            // Result mapping

            if (resultSet.next()) {

                // progressively read rows

                // SQL-Date to java-LocalDate------------------------------------------------
                LocalDate reservation_date;
                java.util.Date dateFrom = null;
                Timestamp timestamp = resultSet.getTimestamp("reservation_date");
                if (timestamp != null){
                    dateFrom = new java.util.Date(timestamp.getTime());
                }

                assert dateFrom != null;
                reservation_date = dateFrom.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                // SQL-Date to java-LocalDate------------------------------------------------
                String reservation_time = resultSet.getString("reservation_time");
                String reservation_doc = resultSet.getString("reservation_doc");
                String reservation_address = resultSet.getString("reservation_address");
                int reservation_patient_id = resultSet.getInt("reservation_patient_id");

                // add new res obj to list
                reservation = new Reservation(reservation_id, reservation_date, reservation_time, reservation_doc,
                        reservation_address, reservation_patient_id);
            } else {
                throw new Exception("No such reservation found: " + reservation_id);
            }

            return reservation;

        } finally {

            // close JDBC
            close(conn, statement, resultSet);

        }

    }

    public void updateReservation(Reservation reservation) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // DB connect
            conn = DriverManager.getConnection(URL, name, password);

            // UPDATE query
            String sql = "UPDATE reservation SET reservation_date=?, reservation_time=?, reservation_doc=?, reservation_address=?, reservation_patient_id=? WHERE reservation_id =?";


            statement = conn.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(reservation.getReservation_date()));
            statement.setString(2, reservation.getReservation_time());
            statement.setString(3, reservation.getReservation_doc());
            statement.setString(4, reservation.getReservation_address());
            statement.setInt(5, reservation.getReservation_patient_id());
            statement.setInt(6, reservation.getReservation_id());

            // execute query
            statement.executeUpdate();

        } finally {

            // close JDBC
            close(conn, statement, null);

        }

    }

    public void deleteReservation(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // ID
            int reservation_id = id;

            // DB connect
            conn = DriverManager.getConnection(URL, name, password);

            // DELETE query
            String sql = "DELETE FROM reservation WHERE reservation_id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, reservation_id);

            // execute query
            statement.execute();

        } finally {

            // close JDBC
            close(conn, statement, null);

        }

    }
    //-----------------------------------------------------------------------------------------------RESERVATIONS

    //-----------------------------------------------------------------------------------------------PATIENTS
    public List<Patient> getPatients() throws Exception {

        List<Patient> patients = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // DB connect
            conn = DriverManager.getConnection(URL, name, password);

            // SELECT
            String sql = "SELECT * FROM patient;";
            statement = conn.createStatement();

            // SQL execute
            resultSet = statement.executeQuery(sql);

            // Result mapping
            while (resultSet.next()) {

                // progressively read rows

                int patient_id = resultSet.getInt("patient_id");
                String patient_login = resultSet.getString("patient_login");
                String patient_password = resultSet.getString("patient_password");
                String patient_name = resultSet.getString("patient_name");
                // SQL-Date to java-LocalDate------------------------------------------------
                LocalDate patient_dateOfBirth;
                java.util.Date dateFrom = null;
                Timestamp timestamp = resultSet.getTimestamp("patient_dateOfBirth");
                if (timestamp != null){
                    dateFrom = new java.util.Date(timestamp.getTime());
                }

                assert dateFrom != null;
                patient_dateOfBirth = dateFrom.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                // SQL-Date to java-LocalDate------------------------------------------------
                String patient_phone = resultSet.getString("patient_phone");
                String patient_email = resultSet.getString("patient_email");

                // add new res obj to list
                patients.add(new Patient(patient_id, patient_login, patient_password, patient_name, patient_dateOfBirth,
                        patient_phone, patient_email));
            }

        } finally {
            // close JDBC
            close(conn, statement, resultSet);
        }
        return patients;
    }

    public Patient getPatientById (int input_patient_id) throws Exception {

        Patient patient = null;

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            // ID
            int patient_id = input_patient_id;

            // DB connect
            conn = DriverManager.getConnection(URL, name, password);

            // SELECT query
            String sql = "SELECT * FROM patient WHERE patient_id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, patient_id);

            // execute query
            resultSet = statement.executeQuery();

            // Result mapping

            if (resultSet.next()) {

                // progressively read rows

                String patient_login = resultSet.getString("patient_login");
                String patient_password = resultSet.getString("patient_password");
                String patient_name = resultSet.getString("patient_name");
                // SQL-Date to java-LocalDate------------------------------------------------
                LocalDate patient_dateOfBirth;
                java.util.Date dateFrom = null;
                Timestamp timestamp = resultSet.getTimestamp("patient_dateOfBirth");
                if (timestamp != null){
                    dateFrom = new java.util.Date(timestamp.getTime());
                }

                assert dateFrom != null;
                patient_dateOfBirth = dateFrom.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                // SQL-Date to java-LocalDate------------------------------------------------
                String patient_phone = resultSet.getString("patient_phone");
                String patient_email = resultSet.getString("patient_email");

                // add new res obj to list
                patient = new Patient(patient_id, patient_login, patient_password, patient_name, patient_dateOfBirth,
                        patient_phone, patient_email);
            } else {
                throw new Exception("No such patient found: " + patient_id);
            }

            return patient;

        } finally {

            // close JDBC
            close(conn, statement, resultSet);

        }

    }

    public void updatePatient (Patient patient) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // DB connect
            conn = DriverManager.getConnection(URL, name, password);

            // UPDATE query
            String sql = "UPDATE patient SET patient_login=?, patient_password=?, patient_name=?," +
                    "patient_dateOfBirth=?, patient_phone=?, patient_email=? " +
                    "WHERE patient_id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, patient.getPatient_login());
            statement.setString(2, patient.getPatient_password());
            statement.setString(3, patient.getPatient_name());
            statement.setDate(4, Date.valueOf(patient.getPatient_dateOfBirth()));
            statement.setString(5, patient.getPatient_phone());
            statement.setString(6, patient.getPatient_email());
            statement.setInt(7, patient.getPatient_id());

            // execute query
            statement.execute();

        } finally {

            // close JDBC
            close(conn, statement, null);

        }

    }

    public void deletePatient(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // ID
            int patient_id = id;

            // DB connect
            conn = DriverManager.getConnection(URL, name, password);

            // DELETE query
            String sql = "DELETE FROM patient WHERE patient_id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, patient_id);

            // execute query
            statement.execute();

        } finally {

            // close JDBC
            close(conn, statement, null);

        }

    }
    //-----------------------------------------------------------------------------------------------PATIENTS

}
