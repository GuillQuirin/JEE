import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author guillaumequirin
 */
@WebServlet(name="registerServlet", urlPatterns="/register")
public class registerServlet extends HttpServlet {
    
    public static final String CHAMP_PSEUDO  = "reg_login_user";
    public static final String CHAMP_EMAIL  = "reg_email_user";
    public static final String CHAMP_PASS   = "reg_pwd_user";
   
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
 
        //Données
        User user = new User();
            user.setPseudo(request.getParameter(CHAMP_PSEUDO));    
            user.setEmail(request.getParameter(CHAMP_EMAIL));
            user.setPwd(request.getParameter(CHAMP_PASS));
        
        Integer erreur = 0;
        
        if(request.getParameter(CHAMP_PSEUDO) != null && request.getParameter(CHAMP_PSEUDO).trim().equals("")){
            request.setAttribute("erreur_pseudo",1);
            erreur = 1;
        }
        
        if(request.getParameter(CHAMP_EMAIL) != null && request.getParameter(CHAMP_EMAIL).trim().equals("")){
            request.setAttribute("erreur_email",1);
            erreur = 1;
        }
        
        if(request.getParameter(CHAMP_PASS) != null && request.getParameter(CHAMP_PASS).trim().equals("")){
            request.setAttribute("erreur_mdp",1);
            erreur = 1;
        }
        
        if(erreur == 0){
            Bdd bdd = new Bdd();
            //Requete SQL
            String query = "SELECT * FROM user WHERE email = '"+user.getEmail()+"' OR pseudo = '"+user.getPseudo()+"'";
            try {
                ResultSet resultat = bdd.get(query);
                if(!resultat.isBeforeFirst()){
                    query = "INSERT INTO user (email, pwd, pseudo) "
                                    + "VALUES ('"+user.getEmail()+"','"+user.getPwd()+"','"+user.getPseudo()+"')";
                    bdd.edit(query);
                    
                    query = "SELECT * FROM user WHERE email = '"+user.getEmail()+"' OR pseudo = '"+user.getPseudo()+"'";
                    try{
                        resultat = bdd.get(query);
                        while(resultat.next()) {
                            //Initialisation Session
                            user.hydrate(resultat);

                            //Initialisation Session
                            HttpSession session = request.getSession(true);
                            session.setAttribute("user", user);
                            response.sendRedirect(request.getContextPath() + "/presentation.jsp");
                            return;
                        }
                    }
                    catch(IOException | SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
                else{
                    request.setAttribute("erreur_duplication",1);
                    this.getServletContext().getRequestDispatcher( "/creation.jsp" ).forward( request, response );
                    return;
                }
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        this.getServletContext().getRequestDispatcher( "/creation.jsp" ).forward( request, response );
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{        
        response.sendRedirect(request.getContextPath() + "/creation.jsp");
    }
}
