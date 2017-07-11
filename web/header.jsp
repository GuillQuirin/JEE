<%-- 
    Document   : header
    Created on : 10 juil. 2017, 13:17:28
    Author     : guillaumequirin
--%>

<header class="container">
    <div class="row">
        <img class="col-md-4" src="public/img/logo.jpg" id="logo">
        <div class="col-md-8" id="publi"></div>
    </div>
    <div class="row">
        <a href="<c:url value='/index.jsp'/>" class="col-md-2 col-md-offset-1"><button>Accueil</button></a>
        <crt:choose>
            <crt:when test="${sessionScope.user == null}">
                <a href="<c:url value='/creation.jsp'/>" class="col-md-2"><button>Création d'un compte</button></a>
                <form action="<c:url value='/login'/>" method="POST">
                    <input class="col-md-2" type="text" placeholder="login" name="auth_login_user" required>
                    <input class="col-md-2" type="password" placeholder="password" name="auth_pwd_user" required>
                    <input class="col-md-2" type="submit" value="Connexion">
                </form>
            </crt:when>
            <crt:otherwise>
                <a href="<c:url value='/presentation.jsp'/>" class="col-md-2"><button>Présentation</button></a>
                <a href="<c:url value='/raccourcir.jsp'/>" class="col-md-2"><button>Raccourcir</button></a>
                <a href="<c:url value='/my_account'/>" class="col-md-2"><button>Mon compte</button></a>
                <a href="<c:url value='/logout'/>" class="col-md-2"><button>Déconnexion</button></a>
            </crt:otherwise>
        </crt:choose>
    </div>
</header>
