<%--
  Created by IntelliJ IDEA.
  User: zloty
  Date: 2017-07-25
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <link rel="stylesheet" href="/resources/styles/navbar/mybootstrap.css">
</head>

<body>
<header>
    <div class="container">
        <nav class="navbar-my">
            <!-- Brand and toggle get grouped for better mobile display -->
            <!-- <div class="navbar-header"> -->
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Convenient Travels</a>
            <!-- </div> -->

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Szukaj przejazdu<span class="sr-only">(current)</span></a></li>
                    <li><a href="#">Dodaj przejazd</a></li>
                    <li><a href="#">Moje przejazdy</a></li>
                    <li><a href="#">Moje rezerwacje</a></li>
                    <li><a href="#">Wiadomości</a></li>
                    <!-- <li class="dropdown"> -->
                    <!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a> -->
                    <!-- <ul class="dropdown-menu"> -->
                    <!-- <li><a href="#">Action</a></li> -->
                    <!-- <li><a href="#">Another action</a></li> -->
                    <!-- <li><a href="#">Something else here</a></li> -->
                    <!-- <li role="separator" class="divider"></li> -->
                    <!-- <li><a href="#">Separated link</a></li> -->
                    <!-- <li role="separator" class="divider"></li> -->
                    <!-- <li><a href="#">One more separated link</a></li> -->
                    <!-- </ul> -->
                    <!-- </li> -->
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Zlotyy</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Konto<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Edytuj konto</a></li>
                            <!-- <li><a href="#">Another action</a></li> -->
                            <!-- <li><a href="#">Something else here</a></li> -->
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Wyloguj</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </div><!-- /.container -->
</header>


<div class="container">
    <div class="panel panel-primary">
        <section>
            <div class="panel-body">
                TUTAJ BEDZIE ZAWARTOSC<br>
                <br><br><br><br><br><br><br>
                <br><br><br><br><br><br><br>
                <br><br><br><br><br><br><br>
                <br><br><br><br><br><br><br>
            </div>
        </section>
        <footer>
            <div class="panel-footer">
                <p><strong>Mail: </strong><a href="mailto:convenient.travels@gmail.com">convenient.travels@gmail.com</a></p>
                <p>&copy; 2017 Arkadiusz Gajewski</p>
            </div>
        </footer>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>