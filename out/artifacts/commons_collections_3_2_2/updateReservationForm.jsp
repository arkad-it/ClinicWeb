<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*,clinic.entity.Reservation " %>
<html>
<head>
    <title>Update reservation</title>
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

<!-- Navigation -->
<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="index.html">HOME</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="units.html">UNITS</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="login.html">RESERVATION</a>
                </li>
            </ul>
        </div>
    </div>

</nav>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>
<center>
    <h1>Update reservation:</h1>
</center>
<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="jumbotron">
    <div class="container">

        <form action="AdminServlet" method="get">

        <c:set var="tempReservation" value="${load_reservation_attribute}" />
            <center>
            <input type="hidden" name="command" value="UPDATE"/>
            <input type="hidden" name="reservationID" value="${tempReservation.reservation_id}"/>
            <div class="form-group">
                <label>Date</label>
                <input type="date" style=" max-width: 300px;" class="form-control" name="reservationDate" value="${tempReservation.reservation_date}"/>
            </div>
            <div class="form-group">
                <label>Time</label>
                <input type="text" style=" max-width: 300px;" class="form-control" name="reservationTime" value="${tempReservation.reservation_time}"/>
            </div>
            <div class="form-group">
                <label>Unit</label>
                <input type="text" style=" max-width: 300px;" class="form-control" name="reservationDoc" value="${tempReservation.reservation_doc}"/>
            </div>
            <div class="form-group">
                <label>Address</label>
                <input type="text" style=" max-width: 300px;" class="form-control" name="reservationAddress" value="${tempReservation.reservation_address}"/>
            </div>
            <div class="form-group">
                <label>Reservation patient ID</label>
                <input type="number" style=" max-width: 300px;" class="form-control" name="reservationPatientId" value="${tempReservation.reservation_patient_id}"/>
            </div>
            <button type="submit" class="btn btn-success">Update</button>
                </center>
        </form>
    </div>
</div>

<div class="row form-group"></div>

<div class="row">
    <div class="container-fluid">
        <center>
        <div class="col-sm-9">
            <a href="AdminServlet" class="btn btn-outline-dark" role="button" aria-disabled="true">Back</a>
        </div>
        </center>
    </div>
</div>

</body>
</html>
