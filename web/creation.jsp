<%-- 
    Document   : creation
    Created on : 10 juil. 2017, 15:05:21
    Author     : guillaumequirin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>

<div class="container">
    <div id="createAccount" class="paramsURL row">
        <crt:choose>
            <crt:when test="${sessionScope.user == null}">
                <form action="register" method="POST">
                    <div class="col-md-8 col-md-offset-2">
                        <div class="row">
                            <p class="col-md-3">Pseudo</p>
                            <input class="col-md-6 col-md-offset-2" type="text" name="reg_login_user" required> 
                        </div>
                        <div class="row">
                            <p class="col-md-3">Email</p>
                            <input class="col-md-6 col-md-offset-2" type="email" name="reg_email_user" required> 
                        </div>
                        <div class="row">
                            <p class="col-md-3">Mot de passe</p>
                            <input class="col-md-6 col-md-offset-2" type="password" name="reg_pwd_user" required> 
                        </div>
                    </div>
                    <div class="col-md-2">
                        <input type="submit" value="Créer un compte">
                    </div>
                </form>
            </crt:when>
            <crt:otherwise>
                <c:redirect url="/presentation.jsp"/>
            </crt:otherwise>
        </crt:choose>
    </div>
    
    <crt:choose>
        <crt:when test="${not empty erreur_pseudo}">
            <div class="row">
                <p class="erreur col-md-8 col-md-offset-2">
                    Un pseudo est obligatoire
                </p>
            </div>
        </crt:when>
    </crt:choose>
    <crt:choose>
        <crt:when test="${not empty erreur_email}">
            <div class="row">
                <p class="erreur col-md-8 col-md-offset-2">
                    Une adresse email est obligatoire
                </p>
            </div>
        </crt:when>
    </crt:choose>
    <crt:choose>
        <crt:when test="${not empty erreur_mdp}">
            <div class="row">
                <p class="erreur col-md-8 col-md-offset-2">
                    Un mot de passe est obligatoire
                </p>
            </div>
        </crt:when>
    </crt:choose>
    <crt:choose>
        <crt:when test="${not empty erreur_duplication}">
            <div class="row">
                <p class="erreur col-md-8 col-md-offset-2">
                    Un compte existe déjà à cette adresse email ou ce pseudo
                </p>
            </div>
        </crt:when>
    </crt:choose>
</div>
    
<%@include file="footer.jsp" %>