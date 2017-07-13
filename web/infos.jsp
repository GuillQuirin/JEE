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
            <div id="infosUser" class="row">
                <form action="editUser" method="POST">
                    <div class="col-md-6 col-md-offset-3">
                        <label>
                            <span>Pseudo : </span>
                            <input type="text" name="pseudo" value="<crt:out value="${sessionScope.user.getPseudo()}"/>">
                        </label>
                    </div>
                    <div class="col-md-6 col-md-offset-3">
                        <label>
                            <span>Email : </span>
                            <input type="email" name="email" value="<crt:out value="${sessionScope.user.getEmail()}"/>">
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

<%@include file="footer.jsp" %>