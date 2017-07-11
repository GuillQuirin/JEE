import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guillaumequirin
 */
@WebServlet(urlPatterns={"/raccourcir"})
public class raccourcirServlet extends HttpServlet {
    
    public static final String VUE = "/raccourcir.jsp";
    public static final String CHAMP_URL  = "url";
    public static final String CHAMP_CAPTCHA   = "is_captcha";
    public static final String CHAMP_IS_PASS   = "is_pwd";
    public static final String CHAMP_PWD   = "pwd";
    public static final String CHAMP_LIMIT   = "is_limit";
    public static final String CHAMP_LIM_DD   = "date_start";
    public static final String CHAMP_LIM_DF   = "date_end";
    public static final String CHAMP_LIM_MAX   = "maximum";
    public static final String CHAMP_LIM_EXP   = "date_exp";
    
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
        response.sendRedirect(request.getContextPath() + "/raccourcir.jsp");
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
        Url url = new Url();
            url.setUrl_origin(request.getParameter(CHAMP_URL));
            
            if(request.getParameter(CHAMP_CAPTCHA) != null)
                url.setCaptcha();
            
            if(request.getParameter(CHAMP_IS_PASS) != null)
                url.setPwd(CHAMP_PWD);
            
            if(request.getParameter(CHAMP_LIMIT) != null){
                if(request.getParameter(CHAMP_LIMIT).equals("fork")){
                    url.setDate_start(request.getParameter(CHAMP_LIM_DD));
                    url.setDate_end(request.getParameter(CHAMP_LIM_DF));
                }
                else if(request.getParameter(CHAMP_LIMIT).equals("max")){
                    url.setMaximum(Integer.parseInt(request.getParameter(CHAMP_LIM_MAX)));
                }
                else if(request.getParameter(CHAMP_LIMIT).equals("expiration"))
                    url.setExpiration(request.getParameter(CHAMP_LIM_EXP));
            }
    }
}
