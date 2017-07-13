<%-- 
    Document   : urls
    Created on : 11 juil. 2017, 15:06:17
    Author     : guillaumequirin
--%>

<%@include file="head.jsp" %>

<div class="container">
    <div id="createURL" class="row">
        <crt:choose>
            <crt:when test="${sessionScope.user == null}">
                <c:redirect url="/creation.jsp"/>
            </crt:when>
            <crt:otherwise>
                <crt:choose>
                    <crt:when test="${sessionScope.user == null}">
                        <div>Vous n'avez aucune URL enregistrée.</div>
                    </crt:when>
                    <crt:otherwise>
                        <table id="listUrl">
                            <thead>
                                Nom
                            </thead>
                            <tbody>
                                <c:forTokens items = "Zara,nuha,roshy" delims = "," var = "name">
                                    <tr>
                                        <td><crt:out value = "${name}"/></td>
                                    </tr>
                                </c:forTokens>
                            </tbody>
                        </table>
                    </crt:otherwise>
                </crt:choose>
            </crt:otherwise>
        </crt:choose>
    </div>
</div>

<%@include file="footer.jsp" %>