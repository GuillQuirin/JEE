
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author guillaumequirin
 */
public class User {
    private Integer id;
    private String civilite;
    private String nom;
    private String prenom;
    private String email;
    private Integer status;
    private String pseudo;
    private String pwd;
    
    public User(){
        
    }
    
    public Integer getId(){return this.id;}
    public String getCivilite(){return this.civilite;}
    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
    public String getEmail(){return this.email;}
    public Integer getStatus(){return this.status;}
    public String getPseudo(){return this.pseudo;}
    public String getPwd(){return this.pwd;}
    
    public void setId( Integer id ){
        this.id = id;
    }
    
    public void setCivilite( String civilite ){
        this.civilite = civilite;
    }
    
    public void setNom( String nom ){
        this.nom = nom;
    }
    
    public void setPrenom( String prenom ){
        this.prenom = prenom;
    }
    
    public void setEmail( String email ){
        this.email = (email.isEmpty()) ? null : email;
    }
    
    public void setStatus( Integer status ){
        this.status = status;
    }
    
    public void setPseudo( String pseudo ){
        this.pseudo = (pseudo.isEmpty()) ? null : pseudo;
    }
    
    public void setPwd( String pwd ){
        this.pwd = (pwd.isEmpty()) ? null : pwd;
    }
    
    public void initPwd( String pwd ){
        this.pwd = pwd;
    }
    
    public void hydrate(ResultSet resultat) throws SQLException {
        try{
            if(resultat.getInt("id") != 0)
                this.setId(resultat.getInt("id"));

            if(resultat.getString("civilite") != null)
                this.setCivilite(resultat.getString("civilite"));

            if(resultat.getString("nom") != null)
                this.setNom(resultat.getString("nom"));
            
            if(resultat.getString("prenom") != null)
                this.setPrenom(resultat.getString("prenom"));

            if(resultat.getString("email") != null)
                this.setEmail(resultat.getString("email"));
            
            /*if(resultat.getInt("status") != 0l)
                this.setPwd(resultat.getInt("status"));
            */
            
            if(resultat.getString("pseudo") != null)
                this.setPseudo(resultat.getString("pseudo"));
            
            if(resultat.getString("pwd") != null)
                this.setPwd(resultat.getString("pwd"));
        }
        catch(SQLException e){
            System.out.println("ERREUR HYDRATATION");
            System.out.println(e.getMessage());
        }
    }
}
