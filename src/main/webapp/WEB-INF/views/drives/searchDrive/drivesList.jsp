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
                    <h2>Przejazdy na trasie ${searchDrivesDTO.startPlace}-${searchDrivesDTO.arrivalPlace}</h2>
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
                            <c:forEach items="${filteredDrives}" var="drive" varStatus="status">
                                <c:set var = "i" value = "${status.index}"/>
                                <tr name="driveToBookRow" onclick="window.location.href='/drives/bookDrive?driveId=${drive.driveId}'" >
                                    <td>${drive.cityStart} ${drive.streetStart} ${drive.exactPlaceStart}</td>
                                    <td>${drive.cityArrival} ${drive.streetArrival} ${drive.exactPlaceArrival}</td>
                                    <td>${stopOverPlaces[i]}</td>
                                    <td>${drivesStartDates[i]}</td>
                                    <td class="text-center">${availableSeats[i]}</td>
                                    <td>${drive.cost} zł</td>
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


    <script src="/resources/scripts/drive/searchDrive.js?version=<%=cacheNumber%>"></script>

    <script type="text/javascript">

        var driveToBookId;       // id przejazdu ktory bedzie rezerwowany

        function setDriveToBookId(id){
            driveToBookId = id;
        }


        $("[name=searchedDrivesTableBody]").find("tr").hover(function(){
            $(this).css("background-color", "buttonface");
        }, function(){
            $(this).css("background-color", "white");
        });




//        $("[name=driveToBookRow]").on("click", function() {
//            window.location.href = "/drives/"
//        });
//
//        $("[name=dialogSubmit]").on("click", function () {
//            $("[name=deleteConfirmForm]").attr("action", "/drives/myDrives/delete?driveId=" + driveToProcessId);
//            console.log("Action: " + $("[name=deleteConfirmForm]").attr("action"));
//
//            $("[name=deleteConfirmForm]").submit();
//        });

    </script>

</body>
</html>
