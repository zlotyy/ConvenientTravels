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

    <%-- jQuery UI CSS --%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css" />

    <link rel="stylesheet" href="/resources/styles/menu/myNavbar.css?version=<%=cacheNumber%>">

    <link rel="stylesheet" href="/resources/styles/pageContent/pageContent.css?version=<%=cacheNumber%>">

    <link rel="stylesheet" href="/resources/styles/account/account.css?version=<%=cacheNumber%>">

    <title>Convenient Travels</title>
</head>

<body>
    <jsp:include page="/menu" />

    <div class="container">
        <div class="panel panel-primary my-fixed-panel">
            <div class="col-md-8">
                <form:form class="form-horizontal" action="/user/account/edit" modelAttribute="user" method="post"
                           id="profileForm" name="profileForm">
                    <div class="container-fluid">
                        <h2 class="col-md-offset-8" style="margin-top: 30px; margin-bottom: 40px">Twój profil</h2>
                        <br>
                        <div class="form-group">
                            <label for="login" class="col-md-1 col-md-offset-1 control-label">Login</label>
                            <div class="col-md-8 col-md-offset-1">
                                <form:input path="login" id="login" placeholder="Login" class="form-control" disabled="true" required="required" />
                                <form:errors path="login" cssclass="error" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="firstName" class="col-md-1 col-md-offset-1 control-label">Imię</label>
                            <div class="col-md-8 col-md-offset-1">
                                <form:input path="name" id="firstName" placeholder="Imię" class="form-control" disabled="true" required="required" />
                                <form:errors path="name" cssclass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-md-1 col-md-offset-1 control-label">Nazwisko</label>
                            <div class="col-md-8 col-md-offset-1">
                                <form:input path="lastname" id="lastName" placeholder="Nazwisko" class="form-control" disabled="true" required="required" />
                                <form:errors path="lastname" cssclass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-md-1 col-md-offset-1 control-label">Email</label>
                            <div class="col-md-8 col-md-offset-1">
                                <form:input path="mail" type="email" id="email" placeholder="Email" class="form-control" disabled="true" required="required" />
                                <form:errors path="mail" cssclass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-md-1 col-md-offset-1 control-label">Telefon</label>
                            <div class="col-md-8 col-md-offset-1">
                                <form:input path="phone" id="phone" placeholder="Telefon" class="form-control" disabled="true" required="required" />
                                <form:errors path="phone" cssclass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-3">
                                <div class="row col-md-3 edit">
                                    <form:button type="button" class="btn btn-primary btn-block">Edytuj</form:button>
                                </div>
                                <div class="row col-md-3 save">
                                    <button type="button" id="saveProfileChanges" name="saveProfileChanges" class="btn btn-primary btn-block">Zapisz</button>
                                </div>
                                <div class="col-md-3 cancel">
                                    <form:button type="reset" class="btn btn-danger btn-block">Anuluj</form:button>
                                </div>

                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />
                            </div>
                        </div>
                    </div>
                </form:form> <!-- /form -->

            </div>
            <div class="col-md-4">
                <div class="container-fluid details-margins">
                    <div class="form-group">
                        <form class="form-horizontal" >
                            <div class="form-group">
                                <div class="row col-md-8">
                                    <button type="button" name="changeCars" class="btn btn-primary btn-block">Zmień samochód</button>
                                </div>
                                <div class="row col-md-8">
                                    <img src="/resources/images/home/car.jpg" id="changeCars" name="changeCars" alt="Wybierz samochód" width="100%" height="20%" style="margin-top: 15px; margin-bottom: 15px; cursor: pointer;" />
                                </div>
                            </div>
                        </form>
                        <form class="form-horizontal" >
                            <div class="form-group">
                                <div class="row col-md-8">
                                    <button type="button" id="deleteUser" class="btn btn-danger btn-block">Usuń konto</button>
                                </div>
                            </div>
                        </form>
                        <form:form class="form-horizontal" action="/user/account/changePassword" modelAttribute="userPassword" method="post"
                                   id="passwordForm" name="passwordForm">
                            <div class="form-group">
                                <div class="row col-md-8 change-password">
                                    <form:button type="button" class="btn btn-primary btn-block">Zmień hasło</form:button>
                                </div>
                                <div class="row col-md-8" style="padding-left: 0px; padding-right: 0px;">
                                    <div class="col-md-6 save-password">
                                        <form:button type="submit" class="btn btn-primary btn-block">Zapisz</form:button>
                                    </div>
                                    <div class="col-md-6 cancel-password">
                                        <form:button type="reset" class="btn btn-primary btn-block">Anuluj</form:button>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${not empty wrongOldPassword}">
                                <li class="error" id="wrongPassword">${wrongOldPassword}</li>
                            </c:if>
                            <div class="form-group">
                                <div class="row col-md-8">
                                    <form:input type="password" path="oldPassword" id="oldPassword" placeholder="Poprzednie hasło" class="form-control" required="required" />
                                    <form:errors path="oldPassword" cssclass="error" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row col-md-8">
                                    <form:input type="password" path="newPassword" id="newPassword" placeholder="Nowe hasło" class="form-control" required="required" />
                                    <form:errors path="newPassword" cssclass="error" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row col-md-8">
                                    <input data-validate="true" type="password" id="confirmPassword" placeholder="Powtórz hasło" class="form-control" required />
                                </div>
                            </div>
                        </form:form>
                        <%--<form:form class="form-horizontal" modelAttribute="userRates" method="post" id="ratesForm" name="ratesForm">--%>
                            <%--<div class="form-group">--%>
                                <%--<div class="row col-md-8">--%>
                                    <%--<label for="userRates" class="control-label">Średnia Ocen</label>--%>
                                    <%--<form:hidden path="userRates" id="userRates" class="form-control" />--%>
                                    <%--<img src="/resources/images/user/rates/0stars.png" />--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</form:form>--%>
                    </div>
                </div>
            </div>

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

    <%-- Modal z samochodem --%>
    <jsp:include page="/car" />

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

            // jesli zwrotka ze login lub email nieunikalny to:
            $('.edit').hide();
            $('.save, .cancel').show();
            $("input").prop('disabled', false);

        </script>
    </c:if>

    <%-- Modal z potwierdzeniem --%>
    <jsp:include page="/user/account/delete/confirm" />

    <%--&lt;%&ndash; Modal z alertem &ndash;%&gt;--%>
    <%--<jsp:include page="/user/account/changePassword/alert" />--%>

    <script src="/resources/scripts/account/account.js?version=<%=cacheNumber%>" ></script>
    <script src="/resources/scripts/modals/dialogs/confirm.js?version=<%=cacheNumber%>" ></script>
    <script src="/resources/scripts/modals/alerts/alert.js?version=<%=cacheNumber%>" ></script>
    <script src="/resources/scripts/modals/dialogs/car.js?version=<%=cacheNumber%>" ></script>
</body>
</html>