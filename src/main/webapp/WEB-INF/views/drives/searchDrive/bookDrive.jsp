<%--Wazne - dzieki temu nie trzeba czyscic cache'a css i js--%>
<%@ page import="java.util.Random" %>
<%
    int cacheNumber = 1;
    Random r = new Random();
    cacheNumber = r.nextInt();
%>
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
                    <h2>Rezerwacja przejazdu</h2>
                    <br>
                </div>
                <form:form class="form-horizontal" modelAttribute="driveDTO" name="driveForm" action="/drives/bookDrive?driveId=${driveId}" method="post">
                    <div class="container-fluid">
                        <div class="col-md-12">
                            <div class="col-md-6" style="float: left">
                                <fieldset>
                                    <legend>Podstawowe informacje</legend>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Miejsce wyjazdu</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${driveDTO.cityStart} ${driveDTO.streetStart} ${driveDTO.exactPlaceStart}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Czas wyjazdu</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${driveDTO.startDate}</p>
                                        </div>
                                    </div>
                                    <hr class="divider">
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Miejsce docelowe</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${driveDTO.cityArrival} ${driveDTO.streetArrival} ${driveDTO.exactPlaceArrival}</p>
                                        </div>
                                    </div>
                                    <hr class="divider">
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Miejsca pośrednie</label>
                                        <div class="col-md-8">
                                            <c:forEach items="${stopOverPlaces}" var="place" varStatus="status">
                                                <p class="form-control-static">${place.stopOverCity} ${place.stopOverStreet}</p>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </fieldset>
                                <br>
                                <fieldset>
                                    <legend>Kierowca</legend>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Imię i nazwisko</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${driver.name} ${driver.lastname}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Wiek</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${driverAge}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Płeć</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">
                                                <c:choose>
                                                    <c:when test="${driver.male eq 'MEZCZYZNA'}">
                                                        Mężczyzna
                                                    </c:when>
                                                    <c:otherwise>
                                                        Kobieta
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Telefon</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${driver.phone}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">E-mail</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${driver.mail}</p>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                            <div class="col-md-5 col-md-offset-1" style="float: right">
                                <fieldset>
                                    <legend>Szczegóły przejazdu</legend>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Koszt</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${driveDTO.cost} zł</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Bagaż</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">
                                                <c:choose>
                                                    <c:when test="${driveDTO.luggageSize eq 'MALY'}">
                                                        Mały
                                                    </c:when>
                                                    <c:when test="${driveDTO.luggageSize eq 'SREDNI'}">
                                                        Średni
                                                    </c:when>
                                                    <c:otherwise>
                                                        Duży
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Czy można palić</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${driveDTO.isSmokePermitted}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Przejazd w obie strony</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">
                                                <c:choose>
                                                <c:when test="${driveDTO.isRoundTrip == 'Tak'}">
                                                    ${driveDTO.isRoundTrip}, powrót: ${driveDTO.returnDate}
                                                </c:when>
                                                <c:otherwise>
                                                    ${driveDTO.isRoundTrip}
                                                </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Komentarz kierującego</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">
                                                <c:choose>
                                                    <c:when test="${empty driveDTO.driverComment}">
                                                        Kierujący nie zostawił komentarza
                                                    </c:when>
                                                    <c:otherwise>${driveDTO.driverComment}</c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Samochód</label>
                                        <div class="col-md-8">
                                            <c:forEach items="${cars}" var="car" varStatus="status">
                                                <p class="form-control-static">${car.carBrand} ${car.carModel} ${car.color}</p>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </fieldset>
                                <br>
                                <fieldset>
                                    <legend>Pasażerowie</legend>
                                    <div class="form-group col-md-12">
                                        <c:forEach items="${passengers}" var="passenger" varStatus="status">
                                            <div class="col-md-3">
                                                <span name="passenger" class="glyphicon glyphicon-user text-danger" title="zajęte miejsce" style="font-size: 60px; cursor: pointer;"></span>
                                            </div>
                                        </c:forEach>
                                        <c:forEach var="i" begin="1" end="${availableSeats}">
                                            <div class="col-md-3">
                                                <span class="glyphicon glyphicon-user text-primary" title="wolne miejsce" style="font-size: 60px;"></span>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="col-md-5" style="float: right; ">
                                <div class="col-md-4 row">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary btn-block">Zarezerwuj</button>
                                    </div>
                                </div>
                                <div class="col-md-4" style="float: right">
                                    <div class="form-group">
                                        <a href="javascript:history.go(-1)"><button type="button" class="btn btn-danger btn-block">Anuluj</button></a>
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

    <%--<script src="/resources/scripts/drive/bookDrive.js?version=<%=cacheNumber%>"></script>--%>

</body>
</html>
