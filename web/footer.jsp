<%-- 
    Document   : footer
    Created on : 10 juil. 2017, 13:18:19
    Author     : guillaumequirin
--%>

        <footer>
            <crt:choose>
                <crt:when test="${sessionScope.user == null}">
                    <div>
                        <p>Cr�ez un compte pour voir nos autres options possibles</p>
                    </div>
                </crt:when>
            </crt:choose>
            <div>
                <a href="">Devenir partenaires</a>
                <a href="">CGV</a>
                <a href="">Mentionsl�gales</a>
            </div>
        </footer>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="js/script.js"></script>
</body>
</html>