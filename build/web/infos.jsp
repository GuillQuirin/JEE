<%-- 
    Document   : infos
    Created on : 11 juil. 2017, 15:01:42
    Author     : guillaumequirin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>

<crt:choose>
    <crt:when test="${sessionScope.user == null}">
        <c:redirect url="/creation.jsp"/>
    </crt:when>
    <crt:otherwise>
        <div class="container">
            <div id="infosUser" class="paramsURL row">
                <form action="editUser" method="POST">
                    <div class="col-md-6 col-md-offset-3">
                        <label>
                            <span>Pseudo * : </span>
                            <input type="text" name="pseudo" value="<crt:out value="${sessionScope.user.getPseudo()}"/>" required>
                        </label>
                    </div>
                    <div class="col-md-6 col-md-offset-3">
                        <label>
                            <span>Email * : </span>
                            <input type="email" name="email" value="<crt:out value="${sessionScope.user.getEmail()}"/>" required>
                        </label>
                    </div>
                    <div class="col-md-6 col-md-offset-3">
                        <label>
                            <span>Nouveau mot de passe : </span>
                            <input type="password" name="pwd">
                        </label>
                    </div>
                    <div class="col-md-6 col-md-offset-3">
                        <label>
                            <span>Confirmation de mot de passe : </span>
                            <input type="password" name="confirm_pwd">
                        </label>
                    </div>
                    <div class="col-md-6 text-center col-md-offset-3">
                        <input type="submit">
                    </div>
                </form>
            </div>
        </div>
    </crt:otherwise>
</crt:choose>

<crt:choose>
    <crt:when test="${not empty erreur_pseudo}">
        <div class="row">
            <p class="erreur col-md-8 col-md-offset-2">
                Le pseudo n'a pas pu être modifié
            </p>
        </div>
    </crt:when>
</crt:choose>

<crt:choose>
    <crt:when test="${not empty erreur_email}">
        <div class="row">
            <p class="erreur col-md-8 col-md-offset-2">
                L'email n'a pas pu être modifiée
            </p>
        </div>
    </crt:when>
</crt:choose>

<crt:choose>
    <crt:when test="${not empty erreur_pwd}">
        <div class="row">
            <p class="erreur col-md-8 col-md-offset-2">
                Les nouveaux mots de passe doivent être identiques
            </p>
        </div>
    </crt:when>
</crt:choose>

<crt:choose>
    <crt:when test="${not empty modifications}">
        <div class="row">
            <p class="check col-md-8 col-md-offset-2">
                Les nouveaux mots de passe doivent être identiques
            </p>
        </div>
    </crt:when>
</crt:choose>
<%@include file="footer.jsp" %>