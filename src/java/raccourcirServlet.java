import java.io.IOException;
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
            url.init_url();
            
        if(url.getUrl_origin() != null){

            Bdd bdd = new Bdd();
            String strInsert = "INSERT INTO url (url_origin) VALUES ('"+url.getUrl_origin()+"');";
            bdd.edit(strInsert);

            String update = "UPDATE url SET url_final = '"+url.getUrl_final()+"'";

            //Captcha
            if(request.getParameter(CHAMP_CAPTCHA) != null){
                url.setCaptcha("123");
                if(!url.getCaptcha().isEmpty())
                    update+=" ,captcha='"+url.getCaptcha()+"'";
            }

            //Mot de passe
            if(request.getParameter(CHAMP_IS_PASS) != null){
                url.setPwd(request.getParameter(CHAMP_PWD));
                if(!url.getPwd().isEmpty())
                    update+=" ,pwd='"+url.getPwd()+"'";
            }

            //Dates fourchette
            if(request.getParameter(CHAMP_LIMIT) != null){
                if(request.getParameter(CHAMP_LIMIT).equals("fork")){
                    url.setDate_start(request.getParameter(CHAMP_LIM_DD));
                    url.setDate_end(request.getParameter(CHAMP_LIM_DF));
                    if(!url.getStart().isEmpty() && !url.getEnd().isEmpty())
                        update+=" ,date_start='"+url.getStart()+"', date_end='"+url.getEnd()+"'";
                }
                else if(request.getParameter(CHAMP_LIMIT).equals("max")){
                    url.setMaximum(Integer.parseInt(request.getParameter(CHAMP_LIM_MAX)));
                    if(url.getMaximum() != 0)
                        update+=" ,maximum="+url.getMaximum();
                }
                else if(request.getParameter(CHAMP_LIMIT).equals("expiration")){
                    url.setExpiration(request.getParameter(CHAMP_LIM_EXP));
                    if(!url.getExpiration().isEmpty())
                        update+=" ,expiration='"+url.getExpiration()+"'";
                }
            }

            update+=" WHERE url_origin='"+url.getUrl_origin()+"'";
            bdd.edit(update);
            
            
            //Liaison de l'URL à l'utilisateur
            HttpSession session = request.getSession(false);
            if(session != null){
                User user = (User) session.getAttribute("user");
                if(user != null && user.getEmail() != null){
                    strInsert = "INSERT INTO user_url (email_user, url_origin) VALUES ('"+user.getEmail()+"','"+url.getUrl_origin()+"');";
                    System.out.println(strInsert);
                    bdd.edit(strInsert);
                }
                else{
                    System.out.println("PAS D'INSERTION");
                }
            }
        }
 
        request.setAttribute("link",url);
        this.getServletContext().getRequestDispatcher( "/raccourcir.jsp" ).forward( request, response );
    }
}
