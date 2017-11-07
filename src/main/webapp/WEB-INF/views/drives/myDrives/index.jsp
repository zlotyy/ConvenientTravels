<%--Wazne - dzieki temu nie trzeba czyscic cache'a css i js--%>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.Calendar" %>
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
                    <h2>Twoje przejazdy</h2>
                    <br>
                </div>
                <div>
                    <table class="table" name="myDrivesTable">
                        <thead>
                            <tr>
                                <th width="30%">Miejsce wyjazdu</th>
                                <th width="30%">Miejsce docelowe</th>
                                <th width="15%">Czas wyjazdu</th>
                                <th width="15%">Zajętych miejsc</th>
                                <th width="10%"></th>
                            </tr>
                        </thead>
                        <tbody name="myDrivesTableBody">
                            <c:forEach items="${userDrives}" var="drive" varStatus="status">
                                <c:set var = "i" value = "${status.index}"/>
                                    <tr>
                                        <td>${drive.cityStart}, ${drive.streetStart} ${drive.busStopStart}</td>
                                        <td>${drive.cityArrival}, ${drive.streetArrival}</td>
                                        <td>${drivesStartDates[i]}</td>
                                        <td class="text-center">${drivesBookedPlaces[i]}/${drivesMaxPlaces[i]}</td>
                                        <td>
                                            <button type="button" name="editDrive" title="Edytuj" class="btn btn-default delete" >
                                                <i class="glyphicon glyphicon-edit" style="color: blue"></i>
                                            </button>
                                            <%--<a href="/drives/myDrives/delete?driveId=${drive.driveId}" >--%>
                                                <button type="button" name="removeDrive" title="Usuń" class="btn btn-default delete" onclick="setDriveToDeleteId(${drive.driveId})" >
                                                <i class="glyphicon glyphicon-remove" style="color: red"></i>
                                                </button>
                                            <%--</a>--%>
                                        </td>
                                    </tr>
                            </c:forEach>
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



    <%--&lt;%&ndash; Modal z potwierdzeniem &ndash;%&gt;--%>
    <jsp:include page="/drives/myDrives/delete/confirm" />

    <script type="text/javascript">

        // usuwanie elementu z listy:
        // podczas klikniecia przycisku usuwania elementu pobierz jego ID
        // zmien atrybut ACTION dla formularza
        // potwierdz formularz

        var driveId;

        function setDriveToDeleteId(id){
            driveId = id;
        }

        $("[name=dialogSubmit]").on("click", function () {
            $("[name=deleteConfirmForm]").attr("action", "/drives/myDrives/delete?driveId=" + driveId);
            console.log("Action: " + $("[name=deleteConfirmForm]").attr("action"));

            $("[name=deleteConfirmForm]").submit();
        });

    </script>

    <script src="/resources/scripts/drive/myDrives.js?version=<%=cacheNumber%>"></script>
    <script src="/resources/scripts/modals/dialogs/confirm.js?version=<%=cacheNumber%>" ></script>

</body>
</html>
