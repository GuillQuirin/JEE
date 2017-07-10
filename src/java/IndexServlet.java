import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author guillaumequirin
 */
@WebServlet(name="IndexServlet", urlPatterns="/index")
public class IndexServlet extends HttpServlet {
    
    public static final String VUE = "index.jsp";
    public static final String CHAMP_URL  = "url";
    public static final String CHAMP_IS_PASS   = "pwd_url_cb";
    public static final String CHAMP_PASS   = "pwd";

    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";
    public static final String ATT_URL = "url";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try{
            response.sendRedirect(VUE);            
            //this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
        catch (Exception e) {
            out.println(e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Données
        String url_origin = getValeurChamp(request, CHAMP_URL);
        String is_pwd = getValeurChamp(request, CHAMP_IS_PASS);
        String pwd = getValeurChamp(request, CHAMP_PASS);

        Url url = new Url();

        //Validation des champs
        try{
            validationURL(url_origin);
        }
        catch(Exception e){
            erreurs.put(CHAMP_URL, e.getMessage());
        }
        //url.setUrl_origin(url_origin);

        resultat = (erreurs.isEmpty()) ? "Succès de l'enregistrement" : "Erreur de l'enregistrement";

        request.setAttribute(ATT_ERREURS, erreurs);
        request.setAttribute(ATT_RESULTAT, resultat);
        //request.setAttribute(ATT_URL, url);

        //Générateur d'url aléatoire
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( 5 );
        for(int i = 0; i < 5; i++ ) 
        sb.append(AB.charAt(rnd.nextInt(AB.length())));
        String url_final = (request.getParameter("url_perso").equals("")) ? sb.toString() : request.getParameter("url_perso");
        
        String strInsert = "INSERT INTO url (url_origin, pwd) VALUES ('"+url_origin+"', '"+pwd+"');";
        Bdd bdd = new Bdd();
        bdd.edit(strInsert);
        
        
        response.sendRedirect(VUE);
        //this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        // String nextJSP = "/";
        // RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        // dispatcher.forward(request,response);
    }

    public String getResultat(){
    	return resultat;
    }

    public Map<String, String> getErreurs(){
    	return erreurs;
    }
    
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        return (valeur == null || valeur.trim().length() == 0) ? null : valeur.trim();
    }
        
    private void validationURL(String url) throws Exception{
        if(url != null && url.trim().length()< 3)
            throw new Exception( "L'URL." );
    }
    
    private void validationPWD(String password) throws Exception{

    }
    
}
