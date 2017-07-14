import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(urlPatterns={"/MyUrl"})
public class MyUrlServlet extends HttpServlet {
   
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
        List<Url> listUrl = new ArrayList<Url>();
        Bdd bdd = new Bdd();
        HttpSession session = request.getSession(false);
        if(session != null){
            User user = (User) session.getAttribute("user");
            if(user != null && user.getId() != 0){
                //Requete SQL
                String query = "SELECT u.id, u.url_origin, u.url_final, u.pwd, u.captcha, u.expiration, u.date_start, u.date_end, u.maximum, u.date_crea "
                                    + "FROM user_url usrl, user usr, url u "
                                    + "WHERE usrl.id_user = usr.id "
                                        + "AND usrl.url_origin = u.url_origin "
                                        + "AND usr.id="+user.getId()+" "
                                    + "GROUP BY u.id";
                try {
                    ResultSet resultat = bdd.get(query);
                    while (resultat.next()) {
                        Url url = new Url();
                        url.hydrate(resultat);
                        Bdd bdd1 = new Bdd();
                        String query1 = "SELECT COUNT(*) as nbDisplay FROM stats WHERE id_url = "+url.getId();
                        ResultSet resultat1 = bdd1.get(query1);                        
                        url.setNbDisplay((resultat1.next() ? resultat1.getInt("nbDisplay") : null));
                        listUrl.add(url);
                    }
                    request.setAttribute("listUrl",listUrl);

                    this.getServletContext().getRequestDispatcher( "/urls.jsp" ).forward( request, response );
                    return;
                } 
                catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else
                response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        else
            response.sendRedirect(request.getContextPath() + "/index.jsp");
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
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }


}
