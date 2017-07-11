<%-- 
    Document   : presentation
    Created on : 10 juil. 2017, 15:05:21
    Author     : guillaumequirin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>

<div class="container">
    <div id="createURL" class="row col-md-6 col-md-offset-3">
        <crt:choose>
            <crt:when test="${sessionScope.user == null}">
                <c:redirect url="/creation.jsp"/>
            </crt:when>
            <crt:otherwise>
                <p>Bonjour ${sessionScope.user.getPseudo()},</p> 
                <p>vous pouvez désormais accéder à toutes nos options de création d’URL raccourcies</p> 
                <ul>
                    <li>Avec mot de passe
                    <li>Avec mot de passe différents
                    <li>A durée limitée
                    <li>A durée périodique
                    <li>Création par lots
                    <li>Visualisation des statistiques
                </ul>
            </crt:otherwise>
        </crt:choose>
    </div>

</div>
    
<%@include file="footer.jsp" %>