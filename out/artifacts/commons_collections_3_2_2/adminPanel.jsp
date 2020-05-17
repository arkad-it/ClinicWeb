<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin panel</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
    <link rel='icon' href='img/favicon.ico' type='image/x-icon'/>
    <meta charset="utf-8">
</head>

<body>
<div>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<center>
<h1>Reservations</h1>
</center>
<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="jumbotron">
    <div class="container">

<table class="table table-striped">

    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Date</th>
        <th scope="col">Time</th>
        <th scope="col">Unit</th>
        <th scope="col">Address</th>
        <th scope="col">Patient ID</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="tempReservation" items="${reservation_list_attribute}">

        <%-- links --%>
        <c:url var="updateLink" value="AdminServlet">
            <c:param name="command" value="LOAD"></c:param>
            <c:param name="reservationID" value="${tempReservation.getReservation_id()}"></c:param>
        </c:url>

        <c:url var="deleteLink" value="AdminServlet">
            <c:param name="command" value="DELETE"></c:param>
            <c:param name="reservationID" value="${tempReservation.getReservation_id()}"></c:param>
        </c:url>


        <tr>
            <th scope="row">${tempReservation.reservation_id}</th>
            <td>${tempReservation.reservation_date}</td>
            <td>${tempReservation.reservation_time}</td>
            <td>${tempReservation.reservation_doc}</td>
            <td>${tempReservation.reservation_address}</td>
            <td>${tempReservation.reservation_patient_id}</td>
            <td><a href="${updateLink}">
                <button type="button" class="btn btn-success">Update</button>
            </a>
                <a href="${deleteLink}"
                   onclick="if(!(confirm('Please confirm deletion'))) return false">
                    <button type="button" class="btn btn-danger">Delete</button>
                </a></td>
        </tr>

    </c:forEach>

    </tbody>
</table>

        <div class="row form-group"></div>
        <div class="row form-group"></div>
        <div class="row form-group"></div>

</div>
</div>

<%--    <center>--%>
<%--        <h1>Patients</h1>--%>
<%--    </center>--%>
<%--    <div class="row form-group"></div>--%>
<%--    <div class="row form-group"></div>--%>
<%--    <div class="row form-group"></div>--%>

<%--    <div class="jumbotron">--%>
<%--        <div class="container">--%>

<%--            <table class="table table-striped">--%>

<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th scope="col">#</th>--%>
<%--                    <th scope="col">Login</th>--%>
<%--                    <th scope="col">Password</th>--%>
<%--                    <th scope="col">Name</th>--%>
<%--                    <th scope="col">Date of birth</th>--%>
<%--                    <th scope="col">Phone</th>--%>
<%--                    <th scope="col">Email</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>

<%--                <c:forEach var="tempPatient" items="${patient_list_attribute}">--%>

<%--                    <c:url var="deleteLinkPatient" value="AdminServlet">--%>
<%--                        <c:param name="command" value="DELETE-PATIENT"></c:param>--%>
<%--                        <c:param name="patientID" value="${tempPatient.getPatient_id()}"></c:param>--%>
<%--                    </c:url>--%>


<%--                    <tr>--%>
<%--                        <th scope="row">${tempPatient.patient_id}</th>--%>
<%--                        <td>${tempPatient.patient_login}</td>--%>
<%--                        <td>${tempPatient.patient_password}</td>--%>
<%--                        <td>${tempPatient.patient_name}</td>--%>
<%--                        <td>${tempPatient.patient_dateOfBirth}</td>--%>
<%--                        <td>${tempPatient.patient_phone}</td>--%>
<%--                        <td>${tempPatient.patient_email}</td>--%>
<%--                        <td>--%>
<%--                            <a href="${deleteLinkPatient}"--%>
<%--                               onclick="if(!(confirm('Please confirm deletion'))) return false">--%>
<%--                                <button type="button" class="btn btn-danger">Delete</button>--%>
<%--                            </a></td>--%>
<%--                    </tr>--%>

<%--                </c:forEach>--%>

<%--                </tbody>--%>
<%--            </table>--%>

<%--        </div>--%>
<%--    </div>--%>

<%--<div class="row form-group"></div>--%>
<%--<div class="row form-group"></div>--%>
<%--<div class="row form-group"></div>--%>

    <center>
<div class="row">
    <div class="container-fluid">

        <div class="col-sm-9">
            <a href="index.html" class="btn btn-outline-dark" role="button" aria-disabled="true">Logout</a>
        </div>
    </div>
</div>
    </center>

</body>
</html>