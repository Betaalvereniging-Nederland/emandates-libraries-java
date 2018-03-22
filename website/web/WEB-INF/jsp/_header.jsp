<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>eMandates Merchant Website</title>
    <link href="<c:url value="/public/css/bootstrap.min.css" />" rel="stylesheet" />
    <link href="<c:url value="/public/css/Site.css" />" rel="stylesheet">
</head>
<body>
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="<c:url value="/" />">eMandates Merchant Website</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="<c:url value="/Products" />">Products</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container body-content">