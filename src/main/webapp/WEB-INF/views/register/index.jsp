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
  Date: 2017-09-05
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <%-- jQuery UI CSS --%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css" />

    <link rel="stylesheet" href="/resources/styles/menu/myNavbar.css?version=<%=cacheNumber%>">

    <link rel="stylesheet" href="/resources/styles/pageContent/pageContent.css?version=<%=cacheNumber%>">

    <link rel="stylesheet" href="/resources/styles/register/register.css?version=<%=cacheNumber%>">

    <title>Convenient Travels</title>
</head>

<body>
    <jsp:include page="/menu" />

    <div class="container">
        <div class="panel panel-primary my-fixed-panel">
            <%-- Modal z alertem --%>
            <jsp:include page="/user/register/wrongPassword/alert" />
            <%-- Modal z samochodem --%>
            <jsp:include page="/car" />

            <form:form class="form-horizontal" action="/user/register" modelAttribute="user" method="post" id="registerForm" autocomplete="true">
                <div class="container-fluid">
                    <h2 class="col-md-offset-2">Rejestracja konta</h2>
                    <br>
                    <div class="form-group">
                        <label for="login" class="col-md-1 col-md-offset-1 control-label">Login</label>
                        <div class="col-md-9">
                            <form:input path="login" id="login" placeholder="Login" class="form-control" autofocus="autofocus" required="required" />
                            <form:errors path="login" cssclass="error" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-1 col-md-offset-1 control-label">Hasło</label>
                        <div class="col-md-9">
                            <form:input path="password" type="password" id="password" placeholder="Hasło" class="form-control" required="required" />
                            <form:errors path="password" cssclass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword" class="col-md-1 col-md-offset-1 control-label">Potwierdź Hasło</label>
                        <div class="col-md-9">
                            <input type="password" id="confirmPassword" placeholder="Powtórz hasło" class="form-control" required >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstName" class="col-md-1 col-md-offset-1 control-label">Imię</label>
                        <div class="col-md-9">
                            <form:input path="name" id="firstName" placeholder="Imię" class="form-control" required="required" />
                            <form:errors path="name" cssclass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastName" class="col-md-1 col-md-offset-1 control-label">Nazwisko</label>
                        <div class="col-md-9">
                            <form:input path="lastname" id="lastName" placeholder="Nazwisko" class="form-control" required="required" />
                            <form:errors path="lastname" cssclass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-md-1 col-md-offset-1 control-label">Email</label>
                        <div class="col-md-9">
                            <form:input path="mail" type="email" id="email" placeholder="Email" class="form-control" required="required" />
                            <form:errors path="mail" cssclass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-md-1 col-md-offset-1 control-label">Telefon</label>
                        <div class="col-md-9">
                            <form:input path="phone" id="phone" placeholder="Telefon" class="form-control" required="required" />
                            <form:errors path="phone" cssclass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="birthDate" class="col-md-1 col-md-offset-1 control-label">Data urodzenia</label>
                        <div class="col-md-9">
                            <form:input path="birthDate" type="date" id="birthDate" class="form-control" required="required" />
                            <form:errors path="birthDate" cssclass="error"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label col-md-2 col-md-offset-2">Płeć</label>
                            <div class="col-md-8">
                                <div class="row">
                                    <div class="col-md-4">
                                        <label class="radio-inline" for="femaleRadio">
                                            <form:radiobutton path="male" id="femaleRadio" value="KOBIETA" required="required" />
                                            Kobieta
                                            <form:errors path="male" cssclass="error"/>
                                        </label>
                                    </div>
                                    <div class="col-md-4">
                                        <label class="radio-inline" for="maleRadio">
                                            <form:radiobutton path="male" id="maleRadio" value="MEZCZYZNA" />
                                            Mężczyzna
                                            <form:errors path="male" cssclass="error"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="car" class="col-md-2 col-md-offset-2 control-label">Wybierz Samochód</label>
                            <div class="col-md-8">
                                <img src="/resources/images/home/car.jpg" id="car" alt="Wybierz samochód" width="60%" height="20%" />
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <div class="col-md-5 col-md-offset-5">
                                <div class="checkbox pull-right">
                                    <label>
                                        <input type="checkbox" required >Akceptuję <a href="/terms">regulamin</a>     <%-- LINK DO REGULAMINU --%>
                                    </label>
                                </div>
                            </div>
                        </div> <!-- /.form-group -->
                        <div class="form-group">
                            <div class="col-md-5 col-md-offset-5">
                                <form:button type="submit" class="btn btn-primary btn-block">Zarejestruj konto</form:button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-5 col-md-offset-5">
                                <a href="/"><form:button type="button" class="btn btn-primary btn-block">Anuluj</form:button></a>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form> <!-- /form -->
        </div>
    </div> <!-- ./container -->

    <jsp:include page="/footer" />


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <%-- jQuery UI javascript --%>
    <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

    <script src="/resources/scripts/register/register.js?version=<%=cacheNumber%>" ></script>
    <script src="/resources/scripts/modals/alerts/alert.js?version=<%=cacheNumber%>" ></script>
    <script src="/resources/scripts/modals/dialogs/car.js?version=<%=cacheNumber%>" ></script>
</body>
</html>