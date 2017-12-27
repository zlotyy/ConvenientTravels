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
                    <h2>Edycja przejazdu</h2>
                    <br>
                </div>
                <form:form class="form-horizontal" modelAttribute="driveDTO" name="driveForm" action="/drives/bookDrive?driveId=${driveId}" method="post">
                    <div class="container-fluid col-md-12">
                        <div class="col-md-3" style="float: left">
                            <fieldset>
                                <legend>Miejsce wyjazdu</legend>
                                <div class="form-group row">
                                    <label class="form-control" >${driveDTO.cityStart} ${driveDTO.streetStart} ${driveDTO.exactPlaceStart}</label>
                                </div>
                            </fieldset>
                        </div>
                        <div class="col-md-3 col-md-offset-1">

                        </div>
                        <div class="col-md-4 col-md-offset-1" style="float: right">

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
