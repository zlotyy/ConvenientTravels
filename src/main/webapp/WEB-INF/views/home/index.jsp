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

    <link rel="stylesheet" href="/resources/styles/menu/myNavbar.css">

    <%-- okresla style dla komunikatow odnosnie logowania --%>
    <link rel="stylesheet" href="/resources/styles/menu/loginForm.css">

    <link rel="stylesheet" href="/resources/styles/pageContent/pageContent.css">

    <title>Convenient Travels</title>
</head>

<body>
    <jsp:include page="/menu" />

    <%--<h1 class="text-center text-primary">Witaj w Convenient Travels</h1>--%>

    <%--<div style="display: inline-block; align: center; vertical-align: middle;">
        <img src="resources/images/home/car.jpg" alt="TEKST ALTERNATYWNY" width="30%" height="30%"/>
    </div>

    <div style="display:inline-block;vertical-align: middle;">
        Wybierasz się w podróż i chcesz trochę zaoszczędzić? Zaloguj się i korzystaj z okazji na wspólne przejazdy z innymi użytkownikami.
    </div>--%>





    <%--<p style="text-align: justify">--%>
        <%--<img src="resources/images/home/car.jpg" alt="TEKST ALTERNATYWNY" width="60%" height="60%" style="float: right; margin: 6px; vertical-align: middle"/>--%>

        <%--Wybierasz się w podróż i chcesz trochę zaoszczędzić? Zaloguj się i korzystaj z okazji na wspólne przejazdy z innymi użytkownikami.--%>



    <%--</p>--%>



    <div class="container">
        <div class="panel panel-primary my-fixed-panel">
            <section>
                <div class="panel-body">
                    <h1 class="text-center text-primary">Witaj w Convenient Travels</h1>

                    <img src="resources/images/home/car.jpg" alt="TEKST ALTERNATYWNY" width="60%" height="60%" align="right" style="margin-left: 5%" />

                    <h4 class="next-to-img text-primary">
                        Wybierasz się w podróż i chcesz trochę zaoszczędzić?
                        Zaloguj się i korzystaj z okazji na wspólne przejazdy z innymi użytkownikami!
                        <br><br>
                        Jeżeli nie masz jeszcze konta, załóż je koniecznie w górnym panelu strony.
                    </h4>


                    <%--<div class="side-by-side">--%>

                        <%--<div class="text-left-by-img">TEXT</div>--%>

                        <%--<div class="img-right-by-text">--%>

                            <%--<img src="resources/images/home/car.jpg" alt="TEKST ALTERNATYWNY" width="60%" height="60%" />--%>

                        <%--</div>--%>

                    <%--</div>--%>



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
