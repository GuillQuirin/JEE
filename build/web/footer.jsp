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
            <div class="row">
                <a href="" class="col-md-4">Devenir partenaires</a>
                <a href="" class="col-md-4">CGV</a>
                <a href="" class="col-md-4">Mentions l�gales</a>
            </div>
        </footer>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.15/af-2.2.0/b-1.3.1/b-colvis-1.3.1/b-print-1.3.1/cr-1.3.3/r-2.1.1/rr-1.2.0/datatables.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/clipboard.js/1.4.0/clipboard.min.js"></script>
        <script src="js/script.js"></script>
</body>
</html>