package clinic.servlet;

import clinic.dbutil.DButilClient;
import clinic.entity.Reservation;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/PatientAccountServlet")
public class PatientAccountServlet extends HttpServlet {

    private DataSource dataSource;
    private DButilClient dButilClient;

    public PatientAccountServlet() { // Get jdbc/clinic context settings

        // Obtain our environment naming context
        Context initCtx = null;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            // Look up our data source
            dataSource = (DataSource)
                    envCtx.lookup("clinic");

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        dButilClient = new DButilClient(dataSource);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        // entry form get data
        String clientLogin = request.getParameter("inputName");
        String clientPassword = request.getParameter("inputPassword");
        boolean isFound = false;
        int session_patient_id = 0;

        // RESERVATION + HOTELBOOKING OBJ SETUP

        try {
            isFound = dButilClient.validatePatient(clientLogin, clientPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isFound){
            System.out.println("Access: permitted");

            // SET PATIENT ID FOR REGISTRATION
            try {
                session_patient_id = dButilClient.getPatientIdByNameAndPsw(clientLogin, clientPassword);
                request.setAttribute("patientID", session_patient_id);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Session: patient ID into cookies;
            Cookie c1 = new Cookie("cookie_patient_id", String.valueOf(session_patient_id));
            c1.setMaxAge(1800);
            response.addCookie(c1);

            try {
                request.setAttribute("reservation_list_attribute", dButilClient.getReservationById(session_patient_id));
            } catch (Exception e) {
                e.printStackTrace();
            }

            // set JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");
            // send to JSP
            dispatcher.forward(request, response);
        }
        else {
            // set JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.html");
            // send to JSP
            dispatcher.forward(request, response);
        }

    }

    private void listReservations(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int patientID = Integer.parseInt(request.getParameter("patientID"));
        List<Reservation> reservationList = dButilClient.getReservationById(patientID);

        request.setAttribute("reservation_list_attribute", reservationList);

        // set JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");

        // send to JSP
        dispatcher.forward(request, response);

    }

}