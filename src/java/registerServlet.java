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

/**
 *
 * @author guillaumequirin
 */
@WebServlet(name="registerServlet", urlPatterns="/register")
public class registerServlet extends HttpServlet {
    
//  public static final String VUE = "index.jsp";
    public static final String CHAMP_PSEUDO  = "url";
    public static final String CHAMP_PASS   = "pwd";

    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";
    public static final String ATT_URL = "url";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
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
                while (resultat.next()) {
                    //Initialisation Session
//                    request.setAttribute(ATT_ERREURS, erreurs);
//                    request.setAttribute(ATT_RESULTAT, resultat);
                    /*int i = rs.getInt("a");
                    String s = rs.getString("b");
                    float f = rs.getFloat("c");
                    System.out.println("ROW = " + i + " " + s + " " + f);*/
                }
                //Redirection Index
            } 
            catch (SQLException ex) {
                Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
    }
    
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        return (valeur == null || valeur.trim().length() == 0) ? null : valeur.trim();
    }
}
