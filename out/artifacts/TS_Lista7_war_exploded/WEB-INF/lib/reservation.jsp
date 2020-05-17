<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <link rel='icon' href='img/favicon.ico' type='image/x-icon'/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="style.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/datepicker/0.6.5/datepicker.min.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/datepicker/0.6.5/datepicker.min.js"></script>

    <style>
    .bg{
    background-image: url("img/bg-appointment.jpg");
    height: 43%;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    }

    .note
    {
      text-align: center;
      height: 80px;
      background: -webkit-linear-gradient(left, #24c4c9, #24c9b3);
      color: #fff;
      font-weight: bold;
      line-height: 80px;
    }

    .btnSubmit
    {
      border:none;
      border-radius:3rem;
      padding: 1%;
      width: 20%;
      cursor: pointer;
      background: #22c7c9;
      color: #fff;
    }

    </style>

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
                    <a class="nav-link" href="index.html">LOGOUT</a>
                </li>
            </ul>
        </div>
    </div>

</nav>



<!--JSP data-->
<form action="PatientReservationServlet" method="post">

    <c:set var="patientID" value="${patientID}" />
    <input type="hidden" name="patientID" value="${patientID}"/>

    <div class="container-fluid padding">
        <div class="col welcome text-center">
            <div class="col-12">
                <h1 class="display-4">Medical appointment reservation</h1>
            </div>
        </div>
    </div>

    <div class="bg">
    <div class="container register-form">
        <div class="form">
            <div class="note">
                <p>Please fill all necessary data</p>
            </div>
            <div class="transparent">
                <div class="form-content">
                    <div class="row">

                        <div class="col">
                          <div class="form-group">
                          <label>Appointment date: </label>
                            <input type="date" value="" name="appointmentDate" id="appointmentDate" required="required" class="form-control"/>
                          </div>
                        </div>

                        <div class="col">
                        <div class="form-group">
                          <center>
                            <label>Hour: </label>
                            <br>
                            <select id="appointmentHour" name="appointmentHour">
                                <option>9:00</option>
                                <option>9:30</option>
                                <option>10:00</option>
                                <option>10:30</option>
                                <option>11:00</option>
                                <option>11:30</option>
                                <option>12:00</option>
                                <option>12:30</option>
                                <option>13:00</option>
                                <option>13:30</option>
                                <option>14:00</option>
                            </select>
                            <br>
                      </center>
                        </div>
                        <div class="col">
                    </div>
            </div>

            <div class="col">
            <div class="form-group">
              <center>
            <label>Unit: </label>
            <br>
            <select id="appointmentUnit" name="appointmentUnit">
                <option>Addiction Treatments</option>
                <option>Psychiatry</option>
                <option>Neurology</option>
                <option>Vascular surgery</option>
                <option>Gastroenterology</option>
                <option>Ophthalmology</option>
            </select>
            <br>
          </center>
            </div>
            <div class="col">
        </div>
</div>

            </div>

        </div>

    </div>

</div>
</div>
</div>
<form action="PatientReservationServlet" method="post">
    <script language="text/javascript">

        function showInput() {
            return 'Please confirm your appointment: ' +
                document.getElementById("appointmentDate").value + ' '
                + document.getElementById("appointmentHour").value + ' '
                + document.getElementById("appointmentUnit").value;

        }
    </script>
    <script>
        let data = showInput();
        console.log(showInput());
        console.log(data);
    </script>

    <div>
    <center>
        <a onclick="if(!(confirm('Please confirm your appointment reservation'))) return false">
        <button type="submit" class="btnSubmit" value="Submit"> RESERVATE </button>
        </a>
    </center>
  </div>
</form>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<center>
    <h1>Your reservations: </h1>
    <form action="PatientReservationServlet" method="get">

        <c:set var="patientID" value="${patientID}" />
        <input type="hidden" name="patientID" value="${patientID}"/>

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

                </tr>
                </thead>
                <tbody>

                <c:forEach var="tempReservation" items="${reservation_list_attribute}">

                    <%-- links --%>
                    <c:url var="deleteLink" value="PatientReservationServlet">
                        <c:param name="command" value="DELETE"></c:param>
                        <c:param name="reservationID" value="${tempReservation.getReservation_id()}"></c:param>
                    </c:url>


                    <tr>
                        <th scope="row">${tempReservation.reservation_id}</th>

                        <td>${tempReservation.reservation_date}</td>
                        <c:set var="datePast" value="${tempReservation.reservation_date}" />
                        <input type="hidden" name="datePast" id="datePast" value="${tempReservation.reservation_date}"/>

                        <td>${tempReservation.reservation_time}</td>
                        <td>${tempReservation.reservation_doc}</td>
                        <td>${tempReservation.reservation_address}
                        </td> <td>
                        <a href="${deleteLink}" onclick="if(!(confirm('Please confirm deletion'))){ return false}">
                            <button type="button" id="delButton" class="btn btn-danger">Delete</button>
                            <script type="javascript1.2">
                                let datePast = document.getElementById("datePast");
                                const button = document.getElementById('delButton')

                                if(Date.parse(datePast)-Date.parse(new Date())<0)
                                {
                                    button.disabled = true;
                                }
                            </script>
                        </a></td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>
</center>
</form>
</body>
</html>
