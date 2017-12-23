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
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <%-- jQuery UI CSS --%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css"/>

    <%-- Bootstrap DateTimePicker CSS --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">

    <link rel="stylesheet" href="/resources/styles/menu/myNavbar.css?version=<%=cacheNumber%>">

    <link rel="stylesheet" href="/resources/styles/pageContent/pageContent.css?version=<%=cacheNumber%>">

    <title>Convenient Travels</title>
</head>

<body>
    <jsp:include page="/menu" />

    <div class="container">
        <div class="panel panel-primary my-fixed-panel">
            <div class="panel-body">
                <div class="text-center">
                    <h2>Znajdź przejazd</h2>
                    <br>
                </div>
                <form class="form-horizontal" id="searchDrivesDTO" name="searchDrivesForm" action="/drives/searchDrive" method="post">
                    <div class="container-fluid">
                        <div class="col-md-12" style="margin-top:50px;">
                            <div class="col-md-3" style="float: left">
                                <fieldset>
                                    <legend>Miejsce wyjazdu</legend>
                                    <div class="form-group row">
                                        <input name="startPlace" class="form-control" placeholder="Miasto/Ulica/Dokładne miejsce" autofocus="autofocus" />
                                    </div>
                                </fieldset>
                            </div>
                            <div class="col-md-3 col-md-offset-1">
                                <fieldset>
                                    <legend>Czas wyjazdu</legend>
                                    <div class="form-group row">
                                        <div class='input-group date' name='datetimepicker'>
                                            <input name="startDate" type='text' class="form-control" />
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                            <div class="col-md-4 col-md-offset-1" style="float: right">
                                <fieldset>
                                    <legend>Miejsce docelowe</legend>
                                    <div class="form-group row">
                                        <input name="arrivalPlace" class="form-control" placeholder="Miasto/Ulica/Dokładne miejsce" required />
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="col-md-3" style="margin-top: 20px">
                                <fieldset>
                                    <legend>Szczegóły przejazdu</legend>
                                    <div class="form-group row">
                                        <label for="Price" class="col-md-6 control-label">Maks. koszt:</label>
                                        <div class="col-md-6">
                                            <input name="maxCost" type="text" class="form-control" id="Price" placeholder="zł" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="LuggageSize" class="col-md-4 control-label">Bagaż:</label>
                                        <div class="col-md-8">
                                            <select name="luggageSize" class="form-control" id="LuggageSize">
                                                <option value="">Bez różnicy</option>
                                                <option value="MALY">Mały</option>
                                                <option value="SREDNI">Średni</option>
                                                <option value="DUZY">Duży</option>
                                            </select>
                                        </div>
                                    </div>
                                    <%--<div class="form-group row">--%>
                                        <%--<label class="col-md-6 control-label">Można palić:</label>--%>
                                        <%--<div class="col-md-6" style="margin-top: 7px; margin-left: 0px;">--%>
                                            <%--<input name="isSmokePermitted" type="checkbox" value="true">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                </fieldset>
                            </div>
                            <div class="col-md-3 col-md-offset-1" style="margin-top: 20px">
                                <fieldset>
                                    <legend>Czas powrotu</legend>
                                    <%--<div class="col-md-12 form-group row">--%>
                                    <%--<label><input type="checkbox" id="RoundTrip" /> Przejazd w obie strony</label>--%>
                                    <%--</div>--%>

                                    <div class="form-group row">
                                        <div class="col-md-12">
                                            <label class="control-label"><input name="isRoundTrip" id="RoundTrip" type="checkbox" value="true"> Przejazd w obie strony</label>
                                        </div>
                                    </div>
                                    <div class="round-trip">
                                        <div class="form-group row">
                                            <div class='input-group date' name='datetimepicker' id="DateTimePicker_RoundTrip">
                                                <input name="returnDate" type='text' class="form-control" disabled />
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                            <div class="col-md-4 col-md-offset-1" style="margin-top: 113px">
                                <div class="col-md-6 row">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary btn-block">Szukaj przejazdu</button>
                                    </div>
                                </div>
                                <div class="col-md-5" style="float: right">
                                    <div class="form-group">
                                        <button type="reset" class="btn btn-danger btn-block">Resetuj wybór</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="/footer" />

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <%-- jQuery UI javascript --%>
    <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <%-- Moment JS - bez tego nie dziala DateTimePicker --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.19.1/moment-with-locales.min.js"></script>
    <%-- Bootstrap DateTimePicker JS --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

    <script src="/resources/scripts/drive/searchDrive.js?version=<%=cacheNumber%>"></script>
</body>
</html>
