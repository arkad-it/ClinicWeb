<%--
  Created by IntelliJ IDEA.
  User: arkad
  Date: 23.04.2020
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Registration completed</title>
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

  <div class="row form-group"></div>
  <div class="row form-group"></div>
  <div class="row form-group"></div>

  <div class="col">
    <div class="transparent"
         style="top: 7%; width:100%; opacity: 70%; height:auto; margin:0 auto;">
      <div>
        <center>
          <h3 class="display-5"> Something went wrong! <br></h3>
          <div class="row form-group"></div>
          <div class="row form-group"></div>
          <h5>${reservationResponse} </h5>
          <div class="row form-group"></div>
          <div class="row form-group"></div>
          <h5>Please go back and choose different date for specified medical unit. </h5>
          <div class="row form-group"></div>
          <div class="row form-group"></div>
        </center>
      </div>
    </div>
  </div>

  <div class="jumbotron">
    <div class="container">
      <center>
        <h1>Unavailable reservations: </h1>
        <div class="row form-group"></div>
        <div class="row form-group"></div>

        <table class="table table-striped">

          <thead>
          <tr>
            <th scope="col">Date</th>
            <th scope="col">Time</th>
            <th scope="col">Unit</th>

          </tr>
          </thead>
          <tbody>

          <c:forEach var="tempReservation" items="${reservation_list_attribute}">

            <tr>
              <td>${tempReservation.reservation_date}</td>
              <c:set var="datePast" value="${tempReservation.reservation_date}"/>
              <input type="hidden" name="datePast" id="datePast" value="${tempReservation.reservation_date}"/>

              <td>${tempReservation.reservation_time}</td>
              <td>${tempReservation.reservation_doc}</td>
              </td>
            </tr>

          </c:forEach>

          </tbody>
        </table>
      </center>
    </div>
  </div>

</body>
</html>