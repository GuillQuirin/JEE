
/**
 *
 * @author guillaumequirin
 */
public class User {
    private String civilite;
    private String nom;
    private String prenom;
    private String email;
    private Integer status;
    private String pseudo;
    private String pwd;
    
    public User(){
        
    }
    
    public String getCivilite(){return this.civilite;}
    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
    public String getEmail(){return this.email;}
    public Integer getStatus(){return this.status;}
    public String getPseudo(){return this.pseudo;}
    public String getPwd(){return this.pwd;}
    
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
}
