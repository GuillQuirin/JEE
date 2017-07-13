<%-- 
    Document   : account
    Created on : 11 juil. 2017, 14:05:02
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
            <div class="row">
                <a href="<c:url value='/infos.jsp'/>" class="col-md-offset-3 col-md-3">
                    <button>Informations personnelles</button>
                </a>
                <a href="<c:url value='/urls.jsp'/>" class="col-md-offset-1 col-md-2">
                    <button>Mes URLs</button>
                </a>
            </div>
        </div>
    </crt:otherwise>
</crt:choose>
<%@include file="footer.jsp" %>