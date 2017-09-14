<%-- https://bootsnipp.com/snippets/featured/toggle-navbar-with-slide-down-animation --%>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%@page import="com.mvc.controller.LoginController" %>--%>
<%--<body onload='document.loginForm.login.focus();'>--%>
<div class="container">
    <!-- Second navbar for categories -->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">Convenient Travels</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <sec:authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">
                        <li><a href="#">Dodaj przejazd</a></li>
                        <li><a href="#">Moje przejazdy</a></li>
                        <li><a href="#">Moje rezerwacje</a></li>
                        <li><a href="#">Wiadomości</a></li>
                    </sec:authorize>

                    <c:if test="${not empty logInError}">
                        <li class="error">${logInError}</li>
                    </c:if>
                    <c:if test="${not empty logoutSuccess}">
                        <li class="msg">${logoutSuccess}</li>
                    </c:if>

                    <li><a href="#">Kontakt</a></li>
                    <li>
                        <a class="btn btn-default btn-outline btn-circle collapsed"  data-toggle="collapse" href="#nav-collapse2" aria-expanded="false" aria-controls="nav-collapse2">Konto</a>
                    </li>
                </ul>
                <div class="collapse nav navbar-nav nav-collapse slide-down" id="nav-collapse2">
                    <sec:authorize access="!hasRole('ROLE_USER') and !hasRole('ROLE_ADMIN')">
                        <form class="navbar-form navbar-right form-inline" role="form" name="loginForm" action="<c:url value='/login' />" method="post">

                            <div class="form-group">
                                <label class="sr-only" for="login">Login</label>
                                <input type="text" class="form-control" id="login" name='login' placeholder="Login" autofocus required />
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="password">Hasło</label>
                                <input type="password" class="form-control" id="password" name='password' placeholder="Hasło" required />
                            </div>
                            <%-- button domyslnie ma type="submit", input tak nie ma --%>
                            <button type="submit" class="btn btn-primary">Zaloguj się</button>
                            <a href="/user/register" class="btn btn-success">Zarejestruj konto</a>
                            <%--<a href="/user/register"><button type="button" class="btn btn-success">Zarejestruj konto</button></a>--%>

                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />

                        </form>
                    </sec:authorize>
                    <%-- Gdy zalogowany to pokaz inne MENU (login uzytkownika i button EDYTUJ KONTO) --%>
                    <sec:authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">

                        <script>
                            function formSubmit() {
                                document.getElementById("logoutForm").submit();
                            }
                        </script>

                        <c:url value="/logout" var="logoutUrl" />
                        <form class="navbar-form navbar-right form-inline" role="form" action="${logoutUrl}" method="post" id="logoutForm">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />

                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                                <a href="/user/edit" class="btn btn-primary">${pageContext.request.userPrincipal.name} <span class="badge">Edytuj konto</span></a>
                                <%--<a href="/user/edit" class="btn btn-primary">Edytuj konto <span class="badge">${pageContext.request.userPrincipal.name}</span></a>--%>
                                <a href="javascript:formSubmit()" class="btn btn-danger">Wyloguj</a>
                            </c:if>
                        </form>
                    </sec:authorize>
                </div>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav><!-- /.navbar -->
</div><!-- /.container-fluid -->
