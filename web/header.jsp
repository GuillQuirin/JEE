<%-- 
    Document   : header
    Created on : 10 juil. 2017, 13:17:28
    Author     : guillaumequirin
--%>

<header>
    <div>
        <img class="col-md-4" src="public/img/logo.jpg" id="logo">
        <div class="col-md-8" id="publi"></div>
    </div>
    <div>
        <a href="<c:url value='/index'/>"><button class="col-md-1">Accueil</button></a>
        <a href="<c:url value='/presentation.jsp'/>"><button class="col-md-2">Présentation</button></a>
        <a href="<c:url value='/creation.jsp'/>"><button class="col-md-2">Création d'un compte</button></a>
         <crt:choose>
            <crt:when test="${sessionScope.user == null}">
                <form action="<c:url value='/login'/>" method="POST">
                    <input class="col-md-2" type="text" placeholder="login" name="auth_login_user">
                    <input class="col-md-2" type="password" placeholder="password" name="auth_pwd_user">
                    <input class="col-md-2" type="submit" value="Connexion">
                </form>
            </crt:when>
            <crt:otherwise>
                <a href="<c:url value='/raccourcir.jsp'/>"><button class="col-md-2">Raccourcir</button></a>
                <a href="<c:url value='/my_account.jsp'/>"><button class="col-md-2">Mon compte</button></a>
                <a href="#"><button class="col-md-2">Déconnexion</button></a>
            </crt:otherwise>
        </crt:choose>
    </div>
</header>
