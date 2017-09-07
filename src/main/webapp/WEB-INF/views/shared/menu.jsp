<%-- https://bootsnipp.com/snippets/featured/toggle-navbar-with-slide-down-animation --%>

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
                    <li><a href="#">Dodaj przejazd</a></li>
                    <li><a href="#">Moje przejazdy</a></li>
                    <li><a href="#">Moje rezerwacje</a></li>
                    <li><a href="#">Wiadomości</a></li>
                    <li><a href="#">Kontakt</a></li>
                    <li>
                        <a class="btn btn-default btn-outline btn-circle collapsed"  data-toggle="collapse" href="#nav-collapse2" aria-expanded="false" aria-controls="nav-collapse2">Konto</a>
                    </li>
                </ul>
                <%-- Gdy zalogowany to pokaz inne MENU (login uzytkownika i button EDYTUJ KONTO) --%>
                <div class="collapse nav navbar-nav nav-collapse slide-down" id="nav-collapse2">
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
                </div>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav><!-- /.navbar -->
</div><!-- /.container-fluid -->
