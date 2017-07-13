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
                <table id="listUrl">
                    <thead>
                        <th>URL originale</th>
                        <th>URL générée</th>
                        <th>Date de création</th>
                        <th>Statistiques</th>
                    </thead>
                    <tbody>
                        <crt:forEach items = "${listUrl}" var = "link">
                            <tr>
                                <td><a href="<crt:out value = "${link.getUrl_origin()}"/>"><crt:out value = "${link.getUrl_origin()}"/></a></td>
                                <td><a href="<crt:out value = "${link.getUrl_final()}"/>"><crt:out value = "${link.getUrl_final()}"/></a></td>
                                <td><fmt:formatDate type = "both" dateStyle = "short" value = "${link.getDate_crea()}" /></td>
                                <td>Stats</td>
                            </tr>
                        </crt:forEach>
                    </tbody>
                </table>
            </crt:otherwise>
        </crt:choose>
    </div>
</div>

<%@include file="footer.jsp" %>