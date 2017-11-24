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
<jsp:include page="/menu"/>

<div class="container">
    <div class="panel panel-primary my-fixed-panel">
        <div class="panel-body">
            <div class="text-center">
                <h2>Edycja przejazdu</h2>
                <br>
            </div>
            <form:form class="form-horizontal" modelAttribute="driveDTO" name="driveForm" action="/drives/myDrives/edit?driveId=${driveId}" method="post">
                <div class="container-fluid">
                    <div class="col-md-12">
                        <div class="col-md-3" style="float: left">
                            <fieldset>
                                <legend>Miejsce wyjazdu</legend>
                                <div class="form-group row">
                                    <form:input path="cityStart" class="form-control" placeholder="Miasto" autofocus="autofocus"
                                           required="required"/>
                                </div>
                                <div class="form-group row">
                                    <form:input path="streetStart" class="form-control" placeholder="Ulica" required="required"/>
                                </div>
                                <div class="form-group row">
                                    <form:input path="exactPlaceStart" class="form-control" placeholder="Dokładne miejsce, np. Lotnisko Balice" />
                                </div>
                            </fieldset>
                            <fieldset>
                                <legend>Czas wyjazdu</legend>
                                <div class="form-group row">
                                    <div class='input-group date' name='datetimepicker'>
                                        <form:input path="startDate" type='text' class="form-control" required="required" />
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                        <div class="col-md-3 col-md-offset-1">
                            <fieldset>
                                <legend>Miejsce docelowe</legend>
                                <div class="form-group row">
                                    <form:input path="cityArrival" class="form-control" placeholder="Miasto" required="required"/>
                                </div>
                                <div class="form-group row">
                                    <form:input path="streetArrival" class="form-control" placeholder="Ulica" required="required"/>
                                </div>
                                <div class="form-group row">
                                    <form:input path="exactPlaceArrival" class="form-control" placeholder="Dokładne miejsce, np. Lotnisko Balice" />
                                </div>
                            </fieldset>
                            <fieldset>
                                <legend>Miejsca pośrednie</legend>
                                <div class="form-group row">
                                    <button name="chooseStopOverPlaces" type="button" class="btn btn-primary form-control">Wybierz miejsca pośrednie</button>
                                </div>
                            </fieldset>
                        </div>
                        <div class="col-md-4 col-md-offset-1" style="float: right">
                            <fieldset>
                                <legend>Szczegóły przejazdu</legend>
                                <div class="form-group row">
                                    <label for="PassengersCount" class="col-md-3 control-label">Pasażerów:</label>
                                    <div class="col-md-4">
                                        <form:select path="passengersQuantity" class="form-control" id="PassengersCount">
                                            <form:option value="1">1</form:option>
                                            <form:option value="2">2</form:option>
                                            <form:option value="3">3</form:option>
                                            <form:option value="4">4</form:option>
                                        </form:select>
                                    </div>
                                    <label for="Price" class="col-md-2 control-label">Koszt:</label>
                                    <div class="col-md-3">
                                        <form:input path="cost" class="form-control" id="Price" placeholder="zł" required="required" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="LuggageSize" class="col-md-2 control-label">Bagaż:</label>
                                    <div class="col-md-5">
                                        <form:select path="luggageSize" class="form-control" id="LuggageSize">
                                            <form:option value="MALY">Mały</form:option>
                                            <form:option value="SREDNI">Średni</form:option>
                                            <form:option value="DUZY">Duży</form:option>
                                        </form:select>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="control-label"><form:checkbox path="isSmokePermitted" value="true" /> Można palić</label>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <label class="control-label"><form:checkbox  path="isRoundTrip" id="RoundTrip" value="true" /> Przejazd w obie strony</label>
                                    </div>
                                </div>
                                <fieldset style="margin-top: 20px">
                                    <legend>Czas powrotu</legend>
                                    <div class="round-trip">
                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <div class='input-group date' name='datetimepicker' id="DateTimePicker_RoundTrip">
                                                    <form:input path="returnDate" type='text' class="form-control" required="required" />
                                                    <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </fieldset>
                        </div>
                        <div class="col-md-12">
                            <div class="col-md-7">
                                <div class="form-group">
                                    <label for="DriverComment">Komentarz na temat przejazdu:</label>
                                    <form:textarea path="driverComment" class="form-control" rows="5" id="DriverComment"></form:textarea>
                                </div>
                            </div>
                            <div class="col-md-4" style="float: right; margin-top: 100px">
                                <div class="col-md-5 row">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary btn-block">Zapisz zmiany</button>
                                    </div>
                                </div>
                                <div class="col-md-5" style="float: right">
                                    <div class="form-group">
                                        <a href="/drives/myDrives"><button type="button" class="btn btn-danger btn-block">Anuluj</button></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<jsp:include page="/footer"/>

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

<%-- Modal z miejscami posrednimi --%>
<jsp:include page="/drives/stopOverPlaces" />

<%-- Modal z alertem - blad z bazy --%>
<c:if test="${dbError}">
    <jsp:include page="/modal/alert/dbError" />
    <script type="text/javascript">
        console.log("${dbError}");
        <c:if test="${dbError}">

        $("[name=alertDialog]").dialog({
            autoOpen: false,
            modal: true
        });

        $("[name=alertClose]").on("click", function() {
            $("[name=alertDialog]").dialog("close");
        });

        $("[name=alertDialog]").dialog("open");

        </c:if>
    </script>
</c:if>

<script src="/resources/scripts/drive/addNewDrive.js?version=<%=cacheNumber%>"></script>
<script src="/resources/scripts/modals/alerts/alert.js?version=<%=cacheNumber%>"></script>
<script src="/resources/scripts/modals/dialogs/stopOverPlaces.js?version=<%=cacheNumber%>"></script>
</body>
</html>
