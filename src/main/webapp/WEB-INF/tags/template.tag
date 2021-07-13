<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="headerTemplate" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@attribute name="title" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<html class="no-js" lang="zxx">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title><jsp:invoke fragment="title"/></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Favicons -->
    <link rel="shortcut icon" href="resources/images/favicon.ico">
    <link rel="apple-touch-icon" href="resources/images/icon.png">

    <!-- Google font (font-family: 'Roboto', sans-serif; Poppins ; Satisfy) -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,500,600,600i,700,700i,800" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">

    <!-- Stylesheets -->
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/plugins.css">
    <link rel="stylesheet" href="resources/css/style.css">

    <!-- Cusom css -->
    <link rel="stylesheet" href="resources/css/custom.css">

    <!-- Ajax jQuery js -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <!-- Modernizer js -->
    <script src="resources/js/vendor/modernizr-3.5.0.min.js"></script>
</head>
<body>
    <!--[if lte IE 9]>
    <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
    <![endif]-->

    <!-- Main wrapper -->
    <div class="wrapper" id="wrapper">
        <headerTemplate:header-template />

        <jsp:doBody/>

        <headerTemplate:footer-template />
    </div>
    <!-- //Main wrapper -->

    <!-- JS Files -->
    <script src="resources/js/vendor/jquery-3.2.1.min.js"></script>
    <script src="resources/js/popper.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/plugins.js"></script>
    <script src="resources/js/active.js"></script>
</body>
</html>