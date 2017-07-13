<%-- 
    Document   : footer
    Created on : 10 juil. 2017, 13:18:19
    Author     : guillaumequirin
--%>

        <footer>
            <crt:choose>
                <crt:when test="${sessionScope.user == null}">
                    <div>
                        <p>Créez un compte pour voir nos autres options possibles</p>
                    </div>
                </crt:when>
            </crt:choose>
            <div>
                <a href="">Devenir partenaires</a>
                <a href="">CGV</a>
                <a href="">Mentionslégales</a>
            </div>
        </footer>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.15/af-2.2.0/b-1.3.1/b-colvis-1.3.1/b-print-1.3.1/cr-1.3.3/r-2.1.1/rr-1.2.0/datatables.min.js"></script>
        <script src="js/script.js"></script>
</body>
</html>