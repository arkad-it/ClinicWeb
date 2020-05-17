package clinic.servlet;

import clinic.dbutil.DButilClient;
import clinic.entity.Patient;
import clinic.security.PasswordEncoderConfig;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/PatientRegistrationServlet")
public class PatientRegistrationServlet extends HttpServlet {

    private DataSource dataSource;
    private DButilClient dButilClient;

    public PatientRegistrationServlet() { // Get jdbc/clinic context settings

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

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // entry form get data
        String patientLogin = request.getParameter("patientLogin");
        String patientPassword = request.getParameter("patientPassword");
        String patientNameSurname = request.getParameter("patientNameSurname");
        String patientEmail = request.getParameter("patientEmail");
        LocalDate patientDateOfBirth = LocalDate.parse(request.getParameter("patientDateOfBirth"));
        String patientPhone = request.getParameter("patientPhone");


        // >>> HASH PASSWORD <<<
        PasswordEncoderConfig passwordEncoderConfig = new PasswordEncoderConfig();
        String pswHash = passwordEncoderConfig.passwordEncoder().encode(patientPassword);
        System.out.println(pswHash);

        Patient patient = new Patient(patientLogin, patientPassword, patientNameSurname, patientDateOfBirth, patientPhone, patientEmail);

        // DB + JSP
        try {
            dButilClient.insertPatient(patient);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Temp object: " + patient.toString());

        request.setAttribute("registeredPatient", patient);

        // set JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/registrationComplete.jsp");

        // send to JSP
        dispatcher.forward(request, response);

    }

}