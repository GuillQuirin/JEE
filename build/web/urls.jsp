<%-- 
    Document   : urls
    Created on : 11 juil. 2017, 15:06:17
    Author     : guillaumequirin
--%>

<%@include file="head.jsp" %>

<div class="container">
    <div id="createURL" class="row paramsURL">
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
                        <th>Captcha</th>
                        <th>Mot de passe</th>
                        <th>Date d'expiration</th>
                        <th>Période d'expiration</th>
                        <th>Nombre de redirection</th>
                    </thead>
                    <tbody>
                        <crt:forEach items = "${listUrl}" var = "link">
                            <tr>
                                <td><a href="<crt:out value = "${link.getUrl_origin()}"/>"><crt:out value = "${link.getUrl_origin()}"/></a></td>
                                <td><a href="<crt:out value = "${link.getUrl_final()}"/>"><crt:out value = "${link.getUrl_final()}"/></a></td>
                                <td><fmt:formatDate type = "date" dateStyle = "short" value = "${link.getDate_crea()}" /></td>
                                <td>
                                    <crt:choose>
                                        <crt:when test="${empty link.getCaptcha()}">
                                            Non
                                        </crt:when>
                                        <crt:otherwise>
                                            Oui
                                        </crt:otherwise>
                                    </crt:choose>
                                </td>
                                <td>
                                    <crt:choose>
                                        <crt:when test="${empty link.getPwd()}">
                                            Non
                                        </crt:when>
                                        <crt:otherwise>
                                            Oui
                                        </crt:otherwise>
                                    </crt:choose>
                                </td>
                                <td>
                                    <crt:choose>
                                        <crt:when test="${empty link.getExpiration()}">
                                            Non
                                        </crt:when>
                                        <crt:otherwise>
                                            Oui
                                        </crt:otherwise>
                                    </crt:choose>
                                </td>
                                <td>
                                    <crt:choose>
                                        <crt:when test="${empty link.getStart()}">
                                            x
                                        </crt:when>
                                        <crt:otherwise>
                                            <fmt:formatDate type = "date" dateStyle = "short" value = "${link.getStart()}" />
                                        </crt:otherwise>
                                    </crt:choose>
                                    -
                                    <crt:choose>
                                        <crt:when test="${empty link.getEnd()}">
                                            x
                                        </crt:when>
                                        <crt:otherwise>
                                            <fmt:formatDate type = "date" dateStyle = "short" value = "${link.getEnd()}" />
                                        </crt:otherwise>
                                    </crt:choose>
                                </td>
                                <td>
                                    <crt:out value = "${link.getNbDisplay()}"/> 
                                    / 
                                    <crt:choose>
                                        <crt:when test="${empty link.getMaximum()}">
                                            x
                                        </crt:when>
                                        <crt:otherwise>
                                            <crt:out value = "${link.getMaximum()}"/>
                                        </crt:otherwise>
                                    </crt:choose>
                                </td>
                            </tr>
                        </crt:forEach>
                    </tbody>
                </table>
            </crt:otherwise>
        </crt:choose>
    </div>
</div>

<%@include file="footer.jsp" %>