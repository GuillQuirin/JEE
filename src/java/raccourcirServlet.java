import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            String code = url.init_url();
            
        if(url.getUrl_origin() != null){

            Bdd bdd = new Bdd();
            String strInsert = "INSERT INTO url (url_origin) VALUES ('"+url.getUrl_origin()+"');";
            bdd.edit(strInsert);

            String update = "UPDATE url SET url_final = '"+code+"'";

            //Captcha
            if(request.getParameter(CHAMP_CAPTCHA) != null){
                url.setCaptcha("1");
                if(request.getParameter(CHAMP_CAPTCHA) != null)
                    update+=" ,captcha="+url.getCaptcha();
            }

            //Mot de passe
            if(request.getParameter(CHAMP_IS_PASS) != null){
                url.setPwd(request.getParameter(CHAMP_PWD));
                if(!url.getPwd().isEmpty())
                    update+=" ,pwd='"+url.getPwd()+"'";
            }

            //Limitations 
            if(request.getParameter(CHAMP_LIMIT) != null){
                if(request.getParameter(CHAMP_LIMIT).equals("fork")){
                    try {
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter(CHAMP_LIM_DD));
                        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter(CHAMP_LIM_DF));

                        if(date1.before(date2)){
                            System.out.println("OK");
                            java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
                            java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
                        
                            url.setDate_start(sqlDate1);
                            url.setDate_end(sqlDate2);
                            if(url.getStart() != null && url.getEnd() != null)
                                update+=" ,date_start='"+url.getStart()+"', date_end='"+url.getEnd()+"'";   
                        }
                        else{
                            //Dates incorrects
                            System.out.println("/ ! \\ DATE 1 APRES DATE 2");
                        }
                    } catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                else if(request.getParameter(CHAMP_LIMIT).equals("max")){
                    url.setMaximum(Integer.parseInt(request.getParameter(CHAMP_LIM_MAX)));
                    if(url.getMaximum() != 0)
                        update+=" ,maximum="+url.getMaximum();
                }
                else if(request.getParameter(CHAMP_LIMIT).equals("expiration")){
                    try {
                        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter(CHAMP_LIM_EXP));
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        url.setExpiration(sqlDate);
                        if(url.getExpiration() != null)
                            update+=" ,expiration='"+url.getExpiration()+"'";
                    } 
                    catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }

            update+=" WHERE url_origin='"+url.getUrl_origin()+"'";
            bdd.edit(update);
            
            
            //Liaison de l'URL à l'utilisateur
            HttpSession session = request.getSession(false);
            if(session != null){
                User user = (User) session.getAttribute("user");
                if(user != null && user.getId() != 0){
                    strInsert = "INSERT INTO user_url (id_user, url_origin) VALUES ("+user.getId()+",'"+url.getUrl_origin()+"');";
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
