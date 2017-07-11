<%-- 
    Document   : raccourcir
    Created on : 10 juil. 2017, 15:13:00
    Author     : guillaumequirin
--%>

<%@include file="head.jsp" %>

<div class="container">
    <div id="createURL" class="row">
        <form action="${pageContext.request.contextPath}/Create123" method="POST">
            <div class="col-md-6 col-md-offset-2">
                <div class="row">
                    <p class="col-md-3">URL à raccourcir</p>
                    <input class="col-md-6 col-md-offset-2" type="text" name="reg_login_user"> 
                </div>
                <div class="row">
                    <input class="col-md-1" id="captcha_cb" type="checkbox" name="reg_pwd_user"> 
                    <label for="captcha_cb">
                        <p class="col-md-3">Captcha</p>
                    </label>
                </div>
                <div class="row">
                    <input class="col-md-1" type="checkbox" name="pwd_url" id="pwd_url_cb">
                    <label class="col-md-4" for="pwd_url_cb">
                        Sécurisée avec mot de passe
                    </label>
                    <input type="password" class="hidden col-md-5" name="pwd" placeholder="Votre mot de passe">
                </div>
                <div class="row">
                    <label>
                        <input type="radio">Valable du <input type="text" name=""> au <input type="text" name="">
                    </label>
                </div>
                <div class="row">
                    <label>
                        <input type="radio">Maximum <input type="number" name=""> clics
                    </label>
                </div>
                <div class="row">
                    <label>
                        <input type="radio">Valable jusqu'au <input type="text" name="">
                    </label>
                </div>
            </div>
            <div class="col-md-2">
                <input type="submit" value="Raccourcir">
            </div>
        </form>
    </div>
</div>

<%@include file="footer.jsp" %>
