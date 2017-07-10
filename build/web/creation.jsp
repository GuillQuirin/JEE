<%-- 
    Document   : creation
    Created on : 10 juil. 2017, 15:05:21
    Author     : guillaumequirin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>

<div class="container">
    <div id="createURL" class="row">
        <crt:choose>
            <crt:when test="${sessionScope.sessionUtilisateur == null}">
                <form action="${pageContext.request.contextPath}/Create123" method="POST">
                    <div class="col-md-6 col-md-offset-2">
                        <div class="row">
                            <p class="col-md-3">Email</p>
                            <input class="col-md-6 col-md-offset-2" type="text" name="reg_login_user"> 
                        </div>
                        <div class="row">
                            <p class="col-md-3">Mot de passe</p>
                            <input class="col-md-6 col-md-offset-2" type="password" name="reg_pwd_user"> 
                        </div>
                        <!-- <div class="row">
                            <p class="col-md-3">Champs à remplir</p>
                            <input class="col-md-6 col-md-offset-2" type="text" name="url"> 
                        </div> -->
                    </div>
                    <div class="col-md-2">
                        <input type="submit" value="Créer un compte">
                    </div>
                </form>
            </crt:when>
            <crt:otherwise>
                Bonjour XXX, 
                vous pouvez désormais accéder à toutes nos options de création d’URL raccourcies 
                Avec mot de passe
                Avec mot de passe différents
                A durée limitée
                A durée périodique
                Création par lots
                Visualisation des statistiques
            </crt:otherwise>
        </crt:choose>
    </div>
    <p>Session : <%= session.getAttribute("sessionUtilisateur") %></p>
    <p>Lien: <%= request.getAttribute("lien") %></p>
</div>
    
<%@include file="footer.jsp" %>