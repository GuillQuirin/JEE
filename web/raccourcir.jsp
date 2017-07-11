<%-- 
    Document   : raccourcir
    Created on : 10 juil. 2017, 15:13:00
    Author     : guillaumequirin
--%>

<%@include file="head.jsp" %>


<div class="container">
    <div id="raccourcirURL" class="row">
        <crt:choose>
            <crt:when test="${sessionScope.user == null}">
                <c:redirect url="/index.jsp"/>
            </crt:when>
            <crt:otherwise>
                <form action="raccourcir" method="POST">
                    <div class="col-md-6 col-md-offset-2">
                        <div class="row">
                            <p class="col-md-3">URL à raccourcir</p>
                            <input class="col-md-6 col-md-offset-2" type="text" name="url" required value="<crt:out value="${param.url}"/>"> 
                        </div>
                        <div class="row">
                            <input class="col-md-1" id="captcha_cb" type="checkbox" name="is_captcha"> 
                            <label for="captcha_cb">
                                <p class="col-md-3">Captcha</p>
                            </label>
                        </div>
                        <div class="row">
                            <input class="col-md-1" type="checkbox" name="is_pwd" id="pwd_url">
                            <label class="col-md-4" for="pwd_url">
                                Sécurisée avec mot de passe
                            </label>
                            <input type="password" class="hidden col-md-6" name="pwd" placeholder="Votre mot de passe">
                        </div>
                        <div class="row">
                            <label>
                                <input type="radio" name="is_limit" value="fork">Valable du 
                                <input type="text" name="date_start" class="" value="<crt:out value="${param.date_start}"/>"> au 
                                <input type="text" name="date_end" class="" value="<crt:out value="${param.date_end}"/>">
                            </label>
                        </div>
                        <div class="row">
                            <label>
                                <input type="radio" name="is_limit" value="max">Maximum 
                                <input type="number" name="maximum" class="" value="<crt:out value="${param.maximum}"/>"> clics
                            </label>
                        </div>
                        <div class="row">
                            <label>
                                <input type="radio" name="is_limit" value="expiration">Valable jusqu'au 
                                <input type="text" name="date_exp" class="" value="<crt:out value="${param.date_exp}"/>">
                            </label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <input type="submit" value="Raccourcir">
                    </div>
                </form>
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
            </crt:otherwise>
        </crt:choose>
    </div>
</div>

<%@include file="footer.jsp" %>
