import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guillaumequirin
 */
@WebServlet(urlPatterns={"/redirect"})
public class redirectServlet extends HttpServlet {
   
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String url = request.getParameter("url");
        if(url != null){
            Bdd bdd = new Bdd();
            //Requete SQL
            String query = "SELECT * FROM url WHERE url_final = '"+url+"'";
            try {
                ResultSet resultat = bdd.get(query);
                Integer redirection = 1;
                if (resultat.isBeforeFirst()){
                    while (resultat.next()) {

                        /*if(resultat.getString("captcha") != null){
                            request.setAttribute("captcha",1);
                            redirection=0;
                        }*/ 
                        
                        //Mot de passe
                        if(resultat.getString("pwd") != null){
                            request.setAttribute("pwd",1);
                            redirection=0;
                        } 
                        
                        //Date limite
                        if(resultat.getDate("expiration") != null){
                            Date date = new Date();
                            java.sql.Date now = new java.sql.Date(date.getTime());
 
                            if(now.before(resultat.getDate("expiration")))
                                redirection = 1;
                            else{
                                request.setAttribute("erreur_expiration",1);
                                redirection=0;
                            }
                        }
                        
                        //Période limite
                        if(resultat.getDate("date_start") != null && resultat.getDate("date_end") != null){
                            Date date = new Date();
                            java.sql.Date now = new java.sql.Date(date.getTime());
 
                            if(resultat.getDate("date_start").before(now) && now.before(resultat.getDate("date_end")))
                                redirection = 1;
                            else{
                                request.setAttribute("erreur_periode",1);
                                redirection=0;
                            }
                        }
                        
                        //Limite d'affichage
                        if(resultat.getString("maximum") != null && resultat.getInt("maximum") <= 0){
                            request.setAttribute("erreur_maximum",1);
                            redirection=0;
                        }
                        else if(resultat.getString("maximum") != null && resultat.getInt("maximum") > 0){
                            if(redirection == 1){
                                Integer maximum = resultat.getInt("maximum")-1;
                                query = "UPDATE url SET maximum = "+maximum+" WHERE url_final = '"+url+"'";
                                bdd.edit(query);
                            }
                        }
                        
                        //Redirection vers le site-cible
                        if(redirection==1)
                            response.sendRedirect(resultat.getString("url_origin"));
                        else
                            this.getServletContext().getRequestDispatcher( "/redirection.jsp" ).forward( request, response );

                        return;
                    }
                }
            } 
            catch (SQLException ex) {
                
            }
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String link = request.getParameter("url");

        if(link != null){
            Integer redirection = 1;
            Bdd bdd = new Bdd();
            //Requete SQL
            String query = "SELECT * FROM url WHERE url_final = '"+link+"'";
            try {
                Url url = new Url();
                ResultSet resultat = bdd.get(query);
                if (resultat.isBeforeFirst()){
                    while (resultat.next()) {
                        
                        url.hydrate(resultat);

                        /*if(resultat.getString("captcha") != null && request.getAttribute("captcha") != null){
                            if()
                            request.setAttribute("captcha",1);
                            redirection=0;
                            //redirection = () ? 0 : 1;
                        }*/
                        
                        if(url.getPwd() != null && request.getParameter("pwd") !=null ){
                            if(!url.getPwd().equals(request.getParameter("pwd"))){
                               request.setAttribute("pwd",1);
                                request.setAttribute("erreur_pwd",1);
                                redirection = 0;
                            }
                            else
                                redirection = 1;
                        }

                        /*if(resultat.getString("expiration") != null){
                            request.setAttribute("expiration",resultat.getString("expiration"));
                        
                        if(resultat.getString("date_start") != null){
                            request.setAttribute("expiration",resultat.getString("expiration"));
                        
                        if(resultat.getString("date_end") != null){
                            request.setAttribute("expiration",resultat.getString("expiration"));
                        }*/

                        if(url.getMaximum() <= 0){
                            request.setAttribute("erreur_maximum",1);
                            redirection=0;
                        }
                        else if(url.getMaximum() > 0){
                            if(redirection == 1){
                                Integer maximum = url.getMaximum()-1;
                                //2eme objet BDD car le premier est en cours d'utilisation dans while()
                                Bdd bdd_update = new Bdd(); 
                                query = "UPDATE url SET maximum = "+maximum+" WHERE url_final = '"+link+"'";
                                bdd_update.edit(query);
                            }
                        }  
                    }
                }
                System.out.println(redirection);
                System.out.println(url.getUrl_origin());               
                if(redirection == 1)
                    response.sendRedirect(url.getUrl_origin());
                else
                    this.getServletContext().getRequestDispatcher( "/redirection.jsp" ).forward( request, response );
                return; //Obligatoire pour ne plus lire la suite du code
            } 
            catch (SQLException e) {
                System.out.println("ERREUR RECUPERATION");
                System.out.println(e.getMessage());
            }
        }
        this.getServletContext().getRequestDispatcher( "/redirection.jsp" ).forward( request, response );
    }


}
