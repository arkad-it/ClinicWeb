package clinic.servlet;

import clinic.dbutil.DButilAdmin;
import clinic.entity.Patient;
import clinic.entity.Reservation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.List;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    private DButilAdmin dButilAdmin;
    private final String db_url = "jdbc:mysql://localhost:3306/clinic?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {

            dButilAdmin = new DButilAdmin(db_url);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private boolean validate(String name, String pass) {
        boolean status = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(db_url, name, pass);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String name = request.getParameter("inputName");
        String password = request.getParameter("inputPassword");

        dButilAdmin.setName(name);
        dButilAdmin.setPassword(password);

        if (validate(name, password)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminPanel.jsp");

            List<Reservation> reservationList = null;
            List<Patient> patientList = null;

            try {
                reservationList = dButilAdmin.getReservations();
                patientList = dButilAdmin.getPatients();
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.setAttribute("reservation_list_attribute", reservationList);
            request.setAttribute("patients_list_attribute", patientList);

            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.html");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // read statement
            String command = request.getParameter("command");

            if (command == null)
                command = "LIST";

            switch (command) {

                case "LOAD":
                    loadReservation(request, response);
                    break;
                case "UPDATE":
                    updateReservation(request, response);
                    break;
                case "DELETE":
                    deleteReservation(request, response);
                    break;
                case "DELETE-PATIENT":
                    deletePatient(request, response);
                    break;
                default:
                    listData(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        return;

    }

    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // get entry data
        int id = Integer.parseInt(request.getParameter("reservationID"));

        // DB delete
        dButilAdmin.deleteReservation(id);

        // send update to html with reservation list
        listData(request, response);

    }

    private void updateReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // entry form get data
        int reservationID = Integer.parseInt(request.getParameter("reservationID"));
        LocalDate reservationDate = LocalDate.parse(request.getParameter("reservationDate"));
        String reservationTime = request.getParameter("reservationTime");
        String reservationDoc = request.getParameter("reservationDoc");
        String reservationAddress = request.getParameter("reservationAddress");
        int reservationPatientId = Integer.parseInt(request.getParameter("reservationPatientId"));

        // new reservation
        Reservation reservation = new Reservation(reservationID, reservationDate, reservationTime, reservationDoc,
                reservationAddress, reservationPatientId);

        // update BD
        dButilAdmin.updateReservation(reservation);

        /// send update to html with reservation list
        listData(request, response);

    }

    private void loadReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // get entry reservation id
        int id = Integer.parseInt(request.getParameter("reservationID"));

        // load from DB
        Reservation reservation = dButilAdmin.getReservationById(id);

        // send to request
        request.setAttribute("load_reservation_attribute", reservation);

        // send to JSP entry form
        RequestDispatcher dispatcher = request.getRequestDispatcher("/updateReservationForm.jsp");
        dispatcher.forward(request, response);

    }

    private void listData(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Patient> patients = dButilAdmin.getPatients();

        request.setAttribute("patient_list_attribute", patients);

        List<Reservation> reservationList = dButilAdmin.getReservations();

        request.setAttribute("reservation_list_attribute", reservationList);

        // set JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminPanel.jsp");

        // send to JSP
        dispatcher.forward(request, response);

    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // get entry data
        int id = Integer.parseInt(request.getParameter("patientID"));

        // DB delete
        dButilAdmin.deletePatient(id);

        // send update to html with reservation list
        listData(request, response);

    }

}
