import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns={"/infos", "/editUser"})
public class infosServlet extends HttpServlet {
   
    public static final String VUE = "/infos.jsp";
    
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
        response.sendRedirect(request.getContextPath() + VUE);
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
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        if(user != null){
            Integer modifs = 1;
            
            if(request.getParameter("pseudo") == null || request.getParameter("pseudo").trim().equals("")){
                request.setAttribute("erreur_pseudo",1);
                modifs = 0;
            }
            else
                user.setPseudo(request.getParameter("pseudo"));
            
            if(request.getParameter("email") == null || request.getParameter("email").trim().equals("")){
                request.setAttribute("erreur_email",1);
                modifs = 0;
            }
            else
                user.setEmail(request.getParameter("email"));
            
            if(!request.getParameter("pwd").equals("") && !request.getParameter("confirm_pwd").equals("")){
                if(!request.getParameter("pwd").equals(request.getParameter("confirm_pwd"))){
                    request.setAttribute("erreur_pwd",1);
                    modifs = 0;
                }
                else
                    user.setPwd(request.getParameter("pwd"));
            }
  
            try{
                if(modifs == 1){
                    Bdd bdd = new Bdd();
                    String query = "UPDATE user SET pseudo='"+user.getPseudo()+"', "
                            +                       "email='"+user.getEmail()+"', "
                            +                       "pwd='"+user.getPwd()+"' "
                            +                   "WHERE id="+user.getId();
                    bdd.edit(query);
                    request.setAttribute("modifications",1);
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            this.getServletContext().getRequestDispatcher( "/infos.jsp" ).forward( request, response );
        }
        else{
            response.sendRedirect(request.getContextPath() + "/index.jsp"); 
        }
    }
}
