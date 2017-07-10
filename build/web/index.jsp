<%-- 
    Document   : index
    Created on : 10 juil. 2017, 13:10:16
    Author     : guillaumequirin
--%>

<%@include file="head.jsp" %>
<div class="container">
    <crt:choose>
        <crt:when test="${envoi}">

            <c:remove var="envoi"/>

            <div id="result">
                <a href="">URL</a>
                <p>Statistiques : Ajouter un point d'exclamation '!' ou '~s' à la fin de l'URL - <a href="">12kz0~s</a></p>
            </div>

        </crt:when>
    </crt:choose>
    
    <div id="createURL" class="row">
        <form action="index" method="POST">
            <div class="paramsURL col-md-8 col-md-offset-2">
                <div class="row">
                    <p class="col-md-2">URL à raccourcir</p>
                    <input class="col-md-6 col-md-offset-1" type="text" name="url" value="<crt:out value="${param.url}"/>"> 
                    <span class="erreur">${erreurs['url']}</span>
                    <input class="col-md-2 col-md-offset-1" type="submit" value="Raccourcir">
                </div>
                <div class="row">
                    <input class="col-md-1" type="checkbox" name="pwd_url" id="pwd_url_cb">
                    <label class="col-md-4" for="pwd_url_cb">
                        Sécurisée avec mot de passe
                    </label>
                    <input type="password" class="hidden col-md-5" name="pwd" placeholder="Votre mot de passe">
                </div>
            </div>
            <!-- <div class="paramsURL">
                <p>Options avancées</p>
                <div id="advanced">
                    <crt:choose>
                        <crt:when test="${sessionScope.sessionUtilisateur != null}">
                            <p>URL perso : <input type="text" name="url_perso"></p>
                            <p>Expiration : <input type="number" name="expiration"></p>
                            <p>
                                Utilisations :   
                                <select name="nb_max_views">
                                    <option value="1">1</option>
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                    <option value="100">100</option>
                                </select>    
                            </p>
                        </crt:when>
                        <crt:otherwise>
                            <p>Créez un compte pour voir nos autres options possibles.</p>
                        </crt:otherwise>
                    </crt:choose>
                </div>
            </div> -->
        </form>
    </div>

    <p>
        <% 
        /*String attribut = (String) request.getAttribute("test");
        out.println( attribut );

        String parametre = request.getParameter( "auteur" );
        out.println( parametre );*/
        %>
    </p>
    <p>
        Récupération du bean :
        <%  
        /*Coyote notreBean = (Coyote) request.getAttribute("coyote");
        out.println( notreBean.getPrenom() );
        out.println( notreBean.getNom() );
        */ %>
    </p>
    <p>Session : <%= session.getAttribute("sessionUtilisateur") %></p>
    <p>Lien: <%= request.getAttribute("lien") %></p>
</div>
        
<%@include file="footer.jsp" %>