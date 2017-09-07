<%--
  Created by IntelliJ IDEA.
  User: zloty
  Date: 2017-09-02
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <link rel="stylesheet" href="/resources/styles/login/mybootstrap.css">

    <!--Pulling Awesome Font -->
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

    <title>Convenient Travels</title>
</head>

<body>
    <jsp:include page="/menu" />

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-6">
                <div class="form-login">
                    <h4>Convenient Travels</h4>
                    <p class="text-center">Logowanie</p>
                    <input type="text" id="login" class="form-control input-sm chat-input" placeholder="Login" />
                    </br>
                    <input type="text" id="password" class="form-control input-sm chat-input" placeholder="Hasło" />
                    </br>
                    <div class="wrapper">
                        <span class="group-btn">
                            <a href="#" class="btn btn-success btn-md">Zarejestruj konto</a>
                            <a href="#" class="btn btn-primary btn-md">Zaloguj się <i class="fa fa-sign-in"></i></a>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/footer" />

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>