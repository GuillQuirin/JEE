<%-- 
    Document   : urls
    Created on : 11 juil. 2017, 15:06:17
    Author     : guillaumequirin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>

<div class="container">
    <div id="createURL" class="row">
        <crt:choose>
            <crt:when test="${sessionScope.user == null}">
                <form action="register" method="POST">
                    <div class="col-md-6 col-md-offset-2">
                        <div class="row">
                            <p class="col-md-3">Pseudo</p>
                            <input class="col-md-6 col-md-offset-2" type="text" name="reg_login_user"> 
                        </div>
                        <div class="row">
                            <p class="col-md-3">Email</p>
                            <input class="col-md-6 col-md-offset-2" type="text" name="reg_email_user"> 
                        </div>
                        <div class="row">
                            <p class="col-md-3">Mot de passe</p>
                            <input class="col-md-6 col-md-offset-2" type="password" name="reg_pwd_user"> 
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
</div>