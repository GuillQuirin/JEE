<%-- 
    Document   : index
    Created on : 10 juil. 2017, 13:10:16
    Author     : guillaumequirin
--%>

<%@include file="head.jsp" %>
<div class="container">
    <div id="createURL" class="row">
        <form action="index" method="POST">
            <div class="paramsURL col-md-8 col-md-offset-2">
                <div class="row">
                    <p class="col-md-2">URL à raccourcir</p>
                    <input class="col-md-6 col-md-offset-1" type="text" name="url" value="<crt:out value="${param.url}"/>" required> 
                    <input class="col-md-2 col-md-offset-1" type="submit" value="Raccourcir">
                </div>
                <crt:choose>
                    <crt:when test="${not empty erreur_url_origin}">
                        <div class="row">
                            <p class="col-md-8 col-md-offset-2">
                                Une URL est obligatoire
                            </p>
                        </div>
                    </crt:when>
                </crt:choose>
                <div class="row">
                    <input class="col-md-1" type="checkbox" name="is_pwd" id="pwd_url_cb">
                    <label class="col-md-4" for="pwd_url_cb">
                        Sécurisée avec mot de passe
                    </label>
                    <input type="password" class="hidden col-md-5" name="pwd" placeholder="Votre mot de passe">
                </div>
            </div>
        </form>
    </div>
    <crt:choose>
        <crt:when test="${not empty link}">
            <div id="result" class="row">
                <p class="resultUrl col-md-8 col-md-offset-2">
                    URL finale : 
                    <a href="<crt:out value="${link.getUrl_final()}"/>">
                        <crt:out value="${link.getUrl_final()}"/>
                    </a>
                </p>
            </div>
        </crt:when>
    </crt:choose>
</div>
        
<%@include file="footer.jsp" %>