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
                    <h2>Dokąd zmierzasz?</h2>
                    <br>
                </div>
                <form class="form-horizontal">
                    <div class="container-fluid">
                        <div class="col-md-3" style="float: left">
                            <fieldset>
                                <legend>Miejsce wyjazdu</legend>
                                <div class="grid-filter">
                                    <div class="form-group row">
                                        <input class="form-control" placeholder="Miasto" autofocus="autofocus"
                                               required="required"/>
                                    </div>
                                    <div class="form-group row">
                                        <input class="form-control" placeholder="Ulica" required="required"/>
                                    </div>
                                    <div class="form-group row">
                                        <input class="form-control" placeholder="Przystanek" required="required"/>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset>
                                <legend>Czas wyjazdu</legend>
                                <div class="form-group row">
                                    <div class='input-group date' name='datetimepicker'>
                                        <input type='text' class="form-control" />
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                        <div class="col-md-4 col-md-offset-1">
                            <fieldset>
                                <legend>Szczegóły przejazdu</legend>
                                <div class="grid-filter">
                                    <div class="form-group row">
                                        <label for="Price" class="col-md-2 control-label">Koszt:</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" id="Price">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="checkbox">
                                            <label class="control-label"><input type="checkbox" value="">Można palić</label>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="LuggageSize" class="col-md-6 control-label">Rozmiar bagażu:</label>
                                        <div class="col-md-6">
                                            <select class="form-control" id="LuggageSize">
                                                <option>Mały</option>
                                                <option>Średni</option>
                                                <option>Duży</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="PassengersCount">Ilość pasażerów:</label>
                                        <select class="form-control" id="PassengersCount">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                        </select>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                        <div class="col-md-3 col-md-offset-1" style="float: right">
                            <fieldset>
                                <legend>Miejsce docelowe</legend>
                                <div class="grid-filter">
                                    <div class="form-group row">
                                        <input class="form-control" placeholder="Miasto" required="required"/>
                                    </div>
                                    <div class="form-group row">
                                        <input class="form-control" placeholder="Ulica" required="required"/>
                                    </div>
                                    <div class="form-group row">
                                        <button type="button" class="btn btn-primary form-control">Miejsca pośrednie
                                        </button>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset>
                                <legend>Czas przyjazdu</legend>
                                <div class="form-group row">
                                    <div class='input-group date' name='datetimepicker'>
                                        <input type='text' class="form-control" />
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="DriverComment">Komentarz na temat przejazdu:</label>
                                <textarea class="form-control" rows="5" id="DriverComment"></textarea>
                            </div>
                        </div>
                        <div class="col-md-3" style="float: right">
                            <fieldset>
                                <div class="form-group row">
                                    <label class="control-label"><input type="checkbox" id="RoundTrip" /> Przejazd w obie strony</label>
                                </div>
                                <div class="round-trip" >
                                    <div class="form-group row">
                                        <div class='input-group date' name='datetimepicker'>
                                            <input type='text' class="form-control" />
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class='input-group date' name='datetimepicker'>
                                            <input type='text' class="form-control" />
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </form>
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

    <script type="text/javascript">
        $(function () {
            $('[name=datetimepicker]').datetimepicker({
                locale: 'pl',
                format: 'YYYY-MM-DD HH:mm'
            });
        });
//
//        function toggleRoundTrip() {
//            if ($('#RoundTrip').is(':checked')) {
//                $('.round-trip').find("input").removeAttr('disabled')
//            } else {;
//                $('.round-trip').find("input").attr('disabled', true);
//            }
//        }
    </script>
</body>
</html>
