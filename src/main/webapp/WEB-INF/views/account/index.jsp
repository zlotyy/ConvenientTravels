<%--
  Created by IntelliJ IDEA.
  User: zloty
  Date: 2017-09-06
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <link rel="stylesheet" href="/resources/styles/menu/myNavbar.css">

    <link rel="stylesheet" href="/resources/styles/pageContent/pageContent.css">

    <link rel="stylesheet" href="/resources/styles/account/account.css">

    <title>Convenient Travels</title>
</head>

<body>
    <jsp:include page="/menu" />

    <div class="container">
        <div class="panel panel-primary my-fixed-panel">
            <div class="col-md-8">
                <c:url var="saveUrl" value="/user/account/edit" />
                <form:form class="form-horizontal" action="/user/account/edit" modelAttribute="user" method="post">
                    <div class="container-fluid">
                        <h2 class="col-md-offset-3">Twój profil</h2>
                        <br>
                        <div class="form-group">
                            <label for="login" class="col-md-1 col-md-offset-1 control-label">Login</label>
                            <div class="col-md-8 col-md-offset-1">
                                <form:input path="login" id="login" placeholder="Login" class="form-control" autofocus="autofocus" disabled="true"/>
                                <form:errors path="login" cssclass="error" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="firstName" class="col-md-1 col-md-offset-1 control-label">Imię</label>
                            <div class="col-md-8 col-md-offset-1">
                                <form:input path="name" id="firstName" placeholder="Imię" class="form-control" disabled="true" />
                                <form:errors path="name" cssclass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-md-1 col-md-offset-1 control-label">Nazwisko</label>
                            <div class="col-md-8 col-md-offset-1">
                                <form:input path="lastname" id="lastName" placeholder="Nazwisko" class="form-control" disabled="true" />
                                <form:errors path="lastname" cssclass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-md-1 col-md-offset-1 control-label">Email</label>
                            <div class="col-md-8 col-md-offset-1">
                                <form:input path="mail" type="email" id="email" placeholder="Email" class="form-control" disabled="true" />
                                <form:errors path="mail" cssclass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-md-1 col-md-offset-1 control-label">Telefon</label>
                            <div class="col-md-8 col-md-offset-1">
                                <form:input path="phone" id="phone" placeholder="Telefon" class="form-control" disabled="true" />
                                <form:errors path="phone" cssclass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-3">
                                <div class="row col-md-3 edit">
                                    <form:button type="button" class="btn btn-primary btn-block">Edytuj</form:button>
                                </div>
                                <div class="row col-md-3 save">
                                    <form:button type="submit" class="btn btn-primary btn-block">Zapisz</form:button>
                                </div>
                                <div class="col-md-3 cancel">
                                    <form:button type="reset" class="btn btn-primary btn-block">Anuluj</form:button>
                                </div>

                                <%--<input type="hidden" name="${_csrf.parameterName}"--%>
                                       <%--value="${_csrf.token}" />--%>
                            </div>
                        </div>
                    </div>
                </form:form> <!-- /form -->
            </div>
        </div>
    </div> <!-- ./container -->

    <jsp:include page="/footer" />

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <script src="/resources/scripts/account/account.js" ></script>
</body>
</html>
