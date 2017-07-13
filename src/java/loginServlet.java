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
@WebServlet(name="loginServlet", urlPatterns= {"/login", "/logout"})
public class loginServlet extends HttpServlet {
    
    public static final String CHAMP_PSEUDO  = "auth_login_user";
    public static final String CHAMP_PASS   = "auth_pwd_user";

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
        /* LOGIN */
        User user = new User();
            user.setPseudo(request.getParameter(CHAMP_PSEUDO));
            user.setPwd(request.getParameter(CHAMP_PASS));
        
        if(user.getPseudo() != null && user.getPwd() != null){
            Bdd bdd = new Bdd();
            //Requete SQL
            String query = "SELECT * FROM user WHERE pseudo = '"+user.getPseudo()+"' AND pwd = '"+user.getPwd()+"'";
            try {
                ResultSet resultat = bdd.get(query);
                while (resultat.next()) {
                    //Initialisation Session
                    user.hydrate(resultat);
                    
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                }
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        //Redirection Index
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        /* LOGOUT */
        HttpSession session = request.getSession(false);
        if(session != null)
            session.invalidate();
        
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }   
}