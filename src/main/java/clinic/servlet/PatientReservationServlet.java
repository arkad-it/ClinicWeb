package clinic.servlet;

import clinic.dbutil.DButilClient;
import clinic.entity.Patient;
import clinic.entity.Reservation;
import clinic.security.PasswordEncoderConfig;

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
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/PatientReservationServlet")
public class PatientReservationServlet extends HttpServlet {

    private DataSource dataSource;
    private DButilClient dButilClient;

    public PatientReservationServlet() { // Get jdbc/clinic context settings

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

        try {
            // read statement
            String command = request.getParameter("command");

            if (command == null)
                command = "LIST";

            switch (command) {
                case "DELETE":
                    deleteReservation(request, response);
                    break;
                default:
                    listReservations(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // entry form get data
        LocalDate appointmentDate = LocalDate.parse(request.getParameter("appointmentDate"));
        String appointmentHour = String.valueOf(request.getParameter("appointmentHour"));
        String appointmentUnit = String.valueOf(request.getParameter("appointmentUnit"));
        int patientID = Integer.parseInt(request.getParameter("patientID"));

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookie_patient_id")) {
                    patientID = Integer.parseInt(cookie.getValue());
                }
            }
        }

        String appointmentAddress = "";
        switch (appointmentUnit) {
            case "Addiction Treatments":
                appointmentAddress = "Seehofstrasse 4, 8008 Zürich, Switzerland";
                break;
            case "Psychiatry":
                appointmentAddress = " Jugoslávských partyzánů 636/20, 160 00 Praha 6-Dejvice, Czech Republic";
                break;
            case "Neurology":
                appointmentAddress = "Ariańska 7, 31-505 Kraków, Poland";
                break;
            case "Vascular surgery":
                appointmentAddress = " Ismaninger Str. 22, 81675 München, Germany";
                break;
            case "Gastroenterology":
                appointmentAddress = "17 Avenue Pierre Mendès France, 74100 Annemasse, France";
                break;
            case "Ophthalmology":
                appointmentAddress = "Sensengasse 3, 1090 Wien, Austria";
                break;
        }

        Reservation reservation = new Reservation(appointmentDate, appointmentHour, appointmentUnit,
                appointmentAddress, patientID);

        // DB + JSP
        try {
            if (!appointmentDate.isBefore(LocalDate.now()) && dButilClient.isReservationAvailable(appointmentDate.toString(), appointmentHour, appointmentUnit)) {
                dButilClient.insertReservation(reservation);
                System.out.println("New record 'reservation': " + reservation.toString());
                request.setAttribute("reservationResponse", reservation);
            } else {
//                PrintWriter out = response.getWriter();
//                response.setContentType("text/html");
//                out.println("<script type=\"text/javascript\">");
//                out.println("alert('Unfortunately chosen date is already taken.');");
//                out.println("</script>");
                System.out.println("error: Appointment date relates to the past or the exact date is already taken.");
                String error = "Could not execute reservation. </br> error: Appointment date relates to the past or the exact date is already taken.";
                request.setAttribute("reservationResponse", error);
                // set JSP
                listTakenReservations(request, response);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/reservationResponse.jsp");
                // GO BACK???
                dispatcher.forward(request, response);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            request.setAttribute("patientID", reservation.getReservation_patient_id());
            // Session: patient ID into cookies;
            Cookie c1 = new Cookie("cookie_patient_id", String.valueOf(patientID));
            c1.setMaxAge(1800);
            response.addCookie(c1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // send to JSP
        try {
            listReservations(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void listReservations(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int patientID = 0;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookie_patient_id")) {
                    patientID = Integer.parseInt(cookie.getValue());
                }
            }
        }

        List<Reservation> reservationList = dButilClient.getReservationById(patientID);

        request.setAttribute("reservation_list_attribute", reservationList);

        // set JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");

        // send to JSP
        dispatcher.forward(request, response);

    }

    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Cookie[] cookies = request.getCookies();
        int patientID= 0 ;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookie_patient_id")) {
                    patientID = Integer.parseInt(cookie.getValue());
                }
            }
        }

        request.setAttribute("patientID", patientID);

        // get entry data
        int id = Integer.parseInt(request.getParameter("reservationID"));

        // DB delete
        dButilClient.deleteReservation(id);

        // send update to html with reservation list
        listReservations(request, response);

    }

    private void listTakenReservations (HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Reservation> reservationList = dButilClient.getReservations();

        request.setAttribute("reservation_list_attribute", reservationList);

        // set JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/reservationResponse.jsp");

        // send to JSP
        dispatcher.forward(request, response);

    }



}
