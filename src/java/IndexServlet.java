import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
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
    
    public static final String CHAMP_URL  = "url";
    public static final String CHAMP_IS_PASS   = "pwd_url_cb";
    public static final String CHAMP_PASS   = "pwd";


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
        response.sendRedirect(request.getContextPath() + "/index.jsp");
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
         Url url = new Url();
            url.setUrl_origin(request.getParameter(CHAMP_URL));
            String code = url.init_url();
            url.setPwd(request.getParameter(CHAMP_PASS));
        
        if(url.getUrl_origin() != null){
            Bdd bdd = new Bdd();
            String strInsert = "INSERT INTO url (url_origin) VALUES ('"+url.getUrl_origin()+"');";
            bdd.edit(strInsert);
            
            String update = "UPDATE url SET url_final = '"+code+"'";
                if(url.getPwd() != null)
                    update+=" ,pwd='"+url.getPwd()+"'";
            update+=" WHERE url_origin='"+url.getUrl_origin()+"'";
            bdd.edit(update);
            
            request.setAttribute("link",url);
        }
        else{
            request.setAttribute("erreur_url_origin",1);
        }
               
        this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
    } 
}