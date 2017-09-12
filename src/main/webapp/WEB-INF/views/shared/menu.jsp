<%-- https://bootsnipp.com/snippets/featured/toggle-navbar-with-slide-down-animation --%>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
                    <li><a href="#">Kontakt</a></li>
                    <li>
                        <a class="btn btn-default btn-outline btn-circle collapsed"  data-toggle="collapse" href="#nav-collapse2" aria-expanded="false" aria-controls="nav-collapse2">Konto</a>
                    </li>
                </ul>
                <%-- Gdy zalogowany to pokaz inne MENU (login uzytkownika i button EDYTUJ KONTO) --%>
                <div class="collapse nav navbar-nav nav-collapse slide-down" id="nav-collapse2">
                    <sec:authorize access="!hasRole('ROLE_USER') and !hasRole('ROLE_ADMIN')">
                        <form class="navbar-form navbar-right form-inline" role="form">
                            <div class="form-group">
                                <label class="sr-only" for="login">Login</label>
                                <input type="text" class="form-control" id="login" placeholder="Login" autofocus required />
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="password">Hasło</label>
                                <input type="password" class="form-control" id="password" placeholder="Hasło" required />
                            </div>
                            <%-- button domyslnie ma type="submit", input tak nie ma --%>
                            <button type="submit" class="btn btn-primary">Zaloguj się</button>
                            <a href="/user/register"><input class="btn btn-success" value="Zarejestruj konto" /></a>
                        </form>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">
                        <c:url value="/logout" var="logoutUrl" />
                        <form action="${logoutUrl}" method="post" id="logoutForm">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                        </form>
                        <script>
                            function formSubmit() {
                                document.getElementById("logoutForm").submit();
                            }
                        </script>

                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <div class="navbar-form navbar-right form-inline">
                                <a href="/user/profile"><label>${pageContext.request.userPrincipal.name}</label></a>
                            </div>
                            <a href="/user/edit"><input class="btn btn-success" value="Edytuj konto" /></a>
                            <a href="javascript:formSubmit()"><input class="btn btn-danger" value="Wyloguj" /></a>
                        </c:if>
                    </sec:authorize>
                </div>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav><!-- /.navbar -->
</div><!-- /.container-fluid -->
