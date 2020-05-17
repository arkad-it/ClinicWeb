package clinic.dbutil;

import clinic.entity.Patient;
import clinic.entity.Reservation;
import clinic.security.PasswordEncoderConfig;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class DButilClient extends DButil{

    private DataSource dataSource;

    // CONN ??? URL/NAME/PASSWORD

    public DButilClient(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //-----------------------------------------------------------------------------------------------RESERVATIONS
    public void insertReservation(Reservation reservation) throws Exception {

        Connection conn = null;
        Statement statement = null;

        try {
            // DB connect
            conn = dataSource.getConnection();

            // SQL query
            String sql = "INSERT INTO reservation(reservation_date, reservation_time, reservation_doc, reservation_address, reservation_patient_id) VALUES" +
                    "('"+ reservation.getReservation_date() +"', '"+ reservation.getReservation_time() +"', '"+ reservation.getReservation_doc() +"', '"+ reservation.getReservation_address() +"', "+ reservation.getReservation_patient_id() +");";
            statement = conn.createStatement();

            // SQL query execute
            statement.executeUpdate(sql);

        } finally {
            // close JDBC
            assert conn != null;
            conn.close();
            assert statement != null;
            statement.close();
        }

    }

    public List<Reservation> getReservations() throws Exception {

        List<Reservation> reservations = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // CONN ??? URL/NAME/PASSWORD
            conn = dataSource.getConnection();

            // SELECT
            String sql = "SELECT * FROM reservation group by reservation_date order by reservation_date asc;";
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

    public List<Reservation> getReservationById (int input_patient_id) throws Exception {

        List<Reservation> reservations= new ArrayList<>();
        Reservation reservation = null;

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            // ID

            // DB connect
            conn = dataSource.getConnection();

            // SELECT query
            String sql = "SELECT * FROM reservation WHERE reservation_patient_id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, input_patient_id);

            // execute query
            resultSet = statement.executeQuery();

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
                reservation = new Reservation(reservation_id, reservation_date, reservation_time, reservation_doc,
                        reservation_address, reservation_patient_id);
                reservations.add(reservation);

            }

            return reservations;

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);

        }

    }

    public void deleteReservation(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            conn = dataSource.getConnection();

            // ID
            int reservation_id = id;

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

    public boolean isReservationAvailable (String input_date, String input_time, String input_unit) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isAvailable = true;

        try {

            conn = dataSource.getConnection();

            // DELETE query
            String sql = "SELECT reservation_id FROM reservation where (reservation_date=? AND reservation_time=? AND reservation_doc=?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, input_date);
            statement.setString(2, input_time);
            statement.setString(3, input_unit);

            // execute query
            statement.execute();

            // execute query
            resultSet = statement.executeQuery();

            // Result mapping

            if (resultSet.next()) {
                isAvailable = false;
            }

        } finally {

            // close JDBC
            close(conn, statement, null);

        }

        return isAvailable;
    }
    //-----------------------------------------------------------------------------------------------RESERVATIONS

    //-----------------------------------------------------------------------------------------------PATIENT
    public void insertPatient(Patient patient) throws Exception {

        Connection conn = null;
        Statement statement = null;

        try {
            // DB connect
            conn = dataSource.getConnection();

            // >>> HASH PASSWORD <<<
            PasswordEncoderConfig passwordEncoderConfig = new PasswordEncoderConfig();
            String pswHash = passwordEncoderConfig.passwordEncoder().encode(patient.getPatient_password());

            // SQL query
            String sql = "INSERT INTO patient(patient_login, patient_password, patient_name, patient_dateOfBirth, patient_phone, patient_email) VALUES" +
                    "('"+ patient.getPatient_login() +"', '"+ pswHash +"', '"+ patient.getPatient_name() +"', '"+ patient.getPatient_dateOfBirth() +"', "+ patient.getPatient_phone() +", '"+ patient.getPatient_email() +"');";
            statement = conn.createStatement();

            // SQL query execute
            statement.executeUpdate(sql);

        } finally {
            // close JDBC
            assert conn != null;
            conn.close();
            assert statement != null;
            statement.close();
        }

    }

    public boolean validatePatient(String userLogin, String userPassword) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isFound = false;

        try {
            // DB connect
            conn = dataSource.getConnection();

            // >>> HASH PASSWORD <<<
            PasswordEncoderConfig passwordEncoderConfig = new PasswordEncoderConfig();
            //String pswHash = passwordEncoderConfig.passwordEncoder().encode(userPassword); random salt!

            System.out.println(userPassword);
            // SQL query
            String sql = "SELECT patient_password FROM patient where (patient_login=?);";
            statement = conn.prepareStatement(sql);
            statement.setString(1, userLogin);

            // execute query
            resultSet = statement.executeQuery();

            String pswHash = "";
            while (resultSet.next()) {
                pswHash = resultSet.getString("patient_password");

                if (BCrypt.checkpw(userPassword, pswHash)){
                    isFound=true;
                }
            }

        } finally {
            // close JDBC
            assert conn != null;
            conn.close();
            assert statement != null;
            statement.close();
        }

        return isFound;
    }

    public int getPatientIdByNameAndPsw (String userLogin, String userPassword) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = 0;

        try {
            // DB connect
            conn = dataSource.getConnection();

            // >>> HASH PASSWORD <<<
            PasswordEncoderConfig passwordEncoderConfig = new PasswordEncoderConfig();
            //String pswHash = passwordEncoderConfig.passwordEncoder().encode(userPassword); random salt!

            System.out.println(userPassword);
            // SQL query
            String sql = "SELECT patient_id, patient_password FROM patient where (patient_login=?);";
            statement = conn.prepareStatement(sql);
            statement.setString(1, userLogin);

            // execute query
            resultSet = statement.executeQuery();

            String pswHash = "";
            while (resultSet.next()) {
                pswHash = resultSet.getString("patient_password");

                if (BCrypt.checkpw(userPassword, pswHash)){
                    id = resultSet.getInt("patient_id");
                    break;
                }
            }



        } finally {
            // close JDBC
            assert conn != null;
            conn.close();
            assert statement != null;
            statement.close();
        }

        return id;
    }
    //-----------------------------------------------------------------------------------------------PATIENT

}
