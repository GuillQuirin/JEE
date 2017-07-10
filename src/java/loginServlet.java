import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name="loginServlet", urlPatterns="/login")
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
 
        //Données
        String login = getValeurChamp(request, CHAMP_PSEUDO);
        String pwd = getValeurChamp(request, CHAMP_PASS);
        
        if(login != null && !login.isEmpty() && pwd != null && !pwd.isEmpty()){
            Bdd bdd = new Bdd();
            //Requete SQL
            String search = "SELECT * FROM user WHERE pseudo = '"+login+"' AND pwd = '"+pwd+"'";
            try {
                ResultSet resultat = bdd.get(search);
                System.out.println(resultat);
                while (resultat.next()) {
                    //Initialisation Session
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", resultat);
                }
            } 
            catch (SQLException ex) {
                Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Redirection Index
        response.sendRedirect(request.getContextPath() + "/index");
    }
    
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        return (valeur == null || valeur.trim().length() == 0) ? null : valeur.trim();
    }
    
}

