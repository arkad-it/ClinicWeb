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

  <center>
  <div class="transparent" style="top: 7%; width:100%; opacity: 70%; height:auto; margin:0 auto; position: absolute;">
    <div>
      <center>
        <h3 class="display-5"> Your account has been registered: <br></h3>
        <h5>${registeredPatient} </h5>
      </center>
    </div>
  </div>
  </center>

</div>


</body>
</html>