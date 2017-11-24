<%--Wazne - dzieki temu nie trzeba czyscic cache'a css i js--%>
<%@ page import="java.util.Random" %>
<%
    int cacheNumber = 1;
    Random r = new Random();
    cacheNumber = r.nextInt();
%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <%-- jQuery UI CSS --%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css"/>

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
                    <h2>Przejazdy na trasie Kraków-Zagorzany</h2>
                    <br>
                </div>
                <div>
                    <table class="table" name="searchedDrivesTable">
                        <thead>
                            <tr>
                                <th width="22%">Miejsce wyjazdu</th>
                                <th width="22%">Miejsce docelowe</th>
                                <th width="20%">Miejsca pośrednie</th>
                                <th width="15%">Data</th>
                                <th width="14%">Wolnych miejsc</th>
                                <th width="5%">Koszt</th>
                            </tr>
                        </thead>
                        <tbody name="searchedDrivesTableBody">
                            <%--<c:forEach items="${userDrives}" var="drive" varStatus="status">--%>
                                <%--<c:set var = "i" value = "${status.index}"/>--%>
                                <%--<tr>--%>
                                    <%--<td>${drive.cityStart}, ${drive.streetStart} ${drive.exactPlaceStart}</td>--%>
                                    <%--<td>${drive.cityArrival}, ${drive.streetArrival}, ${drive.exactPlaceArrival}</td>--%>
                                    <%--<td>${drivesStartDates[i]}</td>--%>
                                    <%--<td class="text-center">${drivesBookedPlaces[i]}/${drivesMaxPlaces[i]}</td>--%>
                                    <%--<td>--%>
                                        <%--<a href="/drives/myDrives/edit?driveId=${drive.driveId}" >--%>
                                            <%--<button type="button" name="editDrive" title="Edytuj" class="btn btn-default delete" >--%>
                                                <%--<i class="glyphicon glyphicon-edit" style="color: blue"></i>--%>
                                            <%--</button>--%>
                                        <%--</a>--%>
                                        <%--<button type="button" name="removeDrive" title="Usuń" class="btn btn-default delete" onclick="setDriveToProcessId(${drive.driveId})" >--%>
                                            <%--<i class="glyphicon glyphicon-remove" style="color: red"></i>--%>
                                        <%--</button>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
                            <%--</c:forEach>--%>
                            <tr>
                                <td>Kraków, Majówny</td>
                                <td>Zagorzany, Zagorzany</td>
                                <td>Kraków, al. Mickiewicza<br>Michałowice<br>Słomniki, rynek<br>Miechów, centrum</td>
                                <td>2017-11-27 10:00</td>
                                <td class="text-center">1/3</td>
                                <td>15 zł</td>
                            </tr>
                            <tr>
                                <td>Kraków, Lublańska</td>
                                <td>Działoszyce, centrum</td>
                                <td>Michałowice<br>Słomniki<br>Miechów<br>Dziaduszyce</td>
                                <td>2017-11-31 12:00</td>
                                <td class="text-center">1/3</td>
                                <td>16 zł</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/footer" />

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <%-- jQuery UI javascript --%>
    <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <%-- Moment JS - bez tego nie dziala DateTimePicker --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.19.1/moment-with-locales.min.js"></script>
    <%-- Bootstrap DateTimePicker JS --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>


    <script src="/resources/scripts/drive/searchDrive.js?version=<%=cacheNumber%>"></script>

</body>
</html>
