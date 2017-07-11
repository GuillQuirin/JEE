import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                while (resultat.next()) {
                    
                    if(resultat.getString("captcha") != null){
                        request.setAttribute("captcha",1);
                        redirection=0;
                    } 
                    
                    if(resultat.getString("pwd") != null){
                        request.setAttribute("pwd",1);
                        redirection=0;
                    } 
                    
                    /*if(resultat.getString("expiration") != null){
                        request.setAttribute("expiration",resultat.getString("expiration"));
                    }*/
                    
                    if(resultat.getString("maximum") != null && resultat.getInt("maximum") <= 0){
                        request.setAttribute("maximum",resultat.getString("maximum"));
                        redirection=0;
                    }
                    else if(resultat.getString("maximum") != null && resultat.getInt("maximum") > 0){
                        if(redirection == 1){
                            //SQL à faire passer en dernier pour s'assurer de la validité de la redirection
                    
                        }
                    }
                    
                    if(redirection==1)
                        response.sendRedirect(resultat.getString("url_origin"));
                    else
                        this.getServletContext().getRequestDispatcher( "/redirection.jsp" ).forward( request, response );
                    
                    return;
                }
            } 
            catch (SQLException ex) {
                
            }
        }
        
        //this.getServletContext().getRequestDispatcher( "/redirection.jsp" ).forward( request, response );
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
        String url = request.getParameter("url");
        if(url != null){
            Bdd bdd = new Bdd();
            //Requete SQL
            String query = "SELECT * FROM url WHERE url_final = '"+url+"'";
            try {
                ResultSet resultat = bdd.get(query);
                Integer redirection = 1;
                while (resultat.next()) {
                    
                    if(resultat.getString("captcha") != null && request.getAttribute("captcha") != null)
                        //redirection = () ? 0 : 1;
                    
                    if(resultat.getString("pwd") != null && request.getAttribute("pwd") !=null )
                        //redirection = () ? 0 : 1;
                    
                    /*if(resultat.getString("expiration") != null){
                        request.setAttribute("expiration",resultat.getString("expiration"));
                    }*/
                    
                    if(resultat.getString("maximum") != null && resultat.getInt("maximum") <= 0){
                        request.setAttribute("maximum",resultat.getString("maximum"));
                        redirection=0;
                    }
                    else if(resultat.getString("maximum") != null && resultat.getInt("maximum") > 0){
                        if(redirection == 1){
                            //SQL
                        }
                    }
                    
                    if(redirection == 1)
                        response.sendRedirect(resultat.getString("url_origin"));
                    else
                        this.getServletContext().getRequestDispatcher( "/redirection.jsp" ).forward( request, response );
                    
                    return;
                }
            } 
            catch (SQLException ex) {
                
            }
        }
    }


}
