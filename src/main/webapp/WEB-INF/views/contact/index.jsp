<%--Wazne - dzieki temu nie trzeba czyscic cache'a css i js--%>
<%@ page import="java.util.Random" %>
<%
    int cacheNumber = 1;
    Random r = new Random();
    cacheNumber = r.nextInt();
%>
<%--
  Created by IntelliJ IDEA.
  User: zloty
  Date: 2017-09-06
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <link rel="stylesheet" href="/resources/styles/menu/myNavbar.css?version=<%=cacheNumber%>">

    <link rel="stylesheet" href="/resources/styles/pageContent/pageContent.css?version=<%=cacheNumber%>">

    <title>Convenient Travels</title>
</head>

<body>
<jsp:include page="/menu" />

<div class="container">
    <div class="panel panel-primary my-fixed-panel">
        <section>
            <div class="panel-body">
                <div>
                    <div class="text-center" style="margin-bottom: 100px">
                        <h2>Kontakt</h2>
                        <br>
                    </div>

                    <div class="col-md-12">
                        <div class="col-md-2 col-md-offset-2">
                            <h3>E - mail</h3>
                            <p>Czytamy wszystkie maile, jeśli masz jakiś problem, koresponduj z nami na:</p>
                            <strong><a href="mailto: convenient.travels@gmail.com" style="color: black">convenient.travels@gmail.com</a></strong>
                        </div>
                        <div class="col-md-2 col-md-offset-1">
                            <h3>Adres</h3>
                            <p>ul. Testowa 55/2
                                <br>Kraków
                                <br>30-123
                                <br>
                            </p>
                        </div>
                        <div class="col-md-2 col-md-offset-1">
                            <h3>Telefon</h3>
                            <p> Możesz się również z nami skontaktować pod numerem telefonu:</p>
                            <p><strong>+48 55 55 555</strong></p>
                        </div>
                        <!-- /.col-sm-4 -->
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<jsp:include page="/footer" />

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>
