<%-- 
    Document   : redirection
    Created on : 11 juil. 2017, 22:10:33
    Author     : guillaumequirin
--%>

<%@include file="head.jsp" %>
<c:set var="acces" value="1"></c:set>

<crt:choose>
    <crt:when test="${not empty erreur_maximum}">
        <div class="container">
            <div id="" class="row">
                <p class="resultUrl col-md-8 col-md-offset-2">
                    LIMITE D'ACCES ATTEINTE 
                    <c:set var="acces" value="0"></c:set>
                </p>
            </div>
        </div>
    </crt:when>
</crt:choose>
    
<crt:choose>
    <crt:when test="${acces == 1}">
        <div class="container">
            <form action="redirect?url=<crt:out value="${param.url}"/>" method="POST">
                <input type="hidden" name="url" value="<crt:out value="${param.url}"/>">
                <crt:choose>
                    <crt:when test="${not empty captcha}">
                        <div id="" class="row">
                            <div class="resultUrl col-md-8 col-md-offset-2">
                                <p>
                                    CAPTCHA :
                                </p>
                                <p>
                                    <input type="text" name="captcha">
                                </p>
                            </div>
                        </div>
                    </crt:when>
                </crt:choose>
                <crt:choose>
                    <crt:when test="${not empty pwd}">
                        <div id="" class="row">
                            <div class="resultUrl col-md-8 col-md-offset-2">
                                <p>
                                    MOT DE PASSE REQUIS : 
                                </p>
                                <p>
                                    <input type="password" name="pwd">
                                </p>
                                <crt:choose>
                                    <crt:when test="${not empty erreur_pwd}">
                                        <p>Le mot de passe est erroné</p>
                                    </crt:when>
                                </crt:choose>
                            </div>
                        </div>
                    </crt:when>
                </crt:choose>
                <div id="" class="row">
                    <div class="resultUrl col-md-8 col-md-offset-2">
                        <input type="submit">
                    </div>
                </div>
            </form>
        </div>
    </crt:when>
</crt:choose>
        
<%@include file="footer.jsp" %>