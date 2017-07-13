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
                    <p class="col-md-3">URL à raccourcir : </p>
                    <input class="col-md-6" type="text" name="url" value="<crt:out value="${param.url}"/>" required> 
                    <input class="col-md-2 col-md-offset-1" type="submit" value="Raccourcir">
                </div>
                <div>
                    <label class="col-md-4">
                        <input type="checkbox" name="is_pwd">
                        Sécurisée avec mot de passe
                    </label>
                    <input type="password" class="hidden col-md-5" name="pwd" placeholder="Votre mot de passe">
                </div>
            </div>
        </form>
    </div>
    <crt:choose>
        <crt:when test="${not empty erreur_url_origin}">
            <div class="row">
                <p class="erreur col-md-8 col-md-offset-2">
                    Une URL est obligatoire
                </p>
            </div>
        </crt:when>
    </crt:choose>
    <crt:choose>
        <crt:when test="${not empty link}">
            <div id="result" class="row">
                <p class="resultUrl col-md-8 col-md-offset-2">
                    URL finale : 
                    <a id="to-copy" target="_blank" href="<crt:out value="${link.getUrl_final()}"/>">
                        <crt:out value="${link.getUrl_final()}"/>
                    </a>
                    <button id="copy" class="btn btn-success" data-clipboard-target="#to-copy">Copier le lien</button>
                </p>
            </div>
        </crt:when>
    </crt:choose>
</div>
        
<%@include file="footer.jsp" %>