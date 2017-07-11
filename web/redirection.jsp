<%-- 
    Document   : redirection
    Created on : 11 juil. 2017, 22:10:33
    Author     : guillaumequirin
--%>

<%@include file="head.jsp" %>
<c:set var="acces" value="1"></c:set>

<crt:choose>
    <crt:when test="${not empty maximum}">
        <div id="" class="row">
            <p class="resultUrl col-md-8 col-md-offset-2">
                LIMITE D'ACCES ATTEINTE 
                <c:set var="acces" value="0"></c:set>
            </p>
        </div>
    </crt:when>
</crt:choose>
    
<crt:choose>
    <crt:when test="${acces == 1}">
        <div class="container">
            <form action="redirect" method="POST">
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
                                    MOT DE PASSE : 
                                </p>
                                <p>
                                    <input type="password" name="pwd">
                                </p>
                            </div>
                        </div>
                    </crt:when>
                </crt:choose>
                <input type="submit">
            </form>
        </div>
    </crt:when>
</crt:choose>
        
<%@include file="footer.jsp" %>