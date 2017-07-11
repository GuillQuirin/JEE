import java.io.IOException;
import java.io.PrintWriter;
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
    
    public static final String VUE = "/index.jsp";
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
        String pwd = getValeurChamp(request, CHAMP_PASS);

        Url url = new Url();
            url.setUrl_origin(url_origin);
            url.setPwd(pwd);
        String url_final = url.getUrl_final();
        
        if(url.getUrl_origin() != null){
            String strInsert = "INSERT INTO url (url_origin, pwd, url_final) "
                                    + "VALUES ("+url.getUrl_origin()+","+url.getPwd()+","+url.getUrl_final()+");";
            Bdd bdd = new Bdd();
            bdd.edit(strInsert);
            request.setAttribute("link",url);
        }
        else{
             System.out.println("/!\\ URL_ORIGIN vide");
        }
        
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
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
}
