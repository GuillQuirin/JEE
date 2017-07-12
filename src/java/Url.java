
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author guillaumequirin
 */
public class Url {
    
    private String url_origin="";
    private String url_final="";
    private String captcha="";
    private String pwd="";
    private String date_start="";
    private String date_end="";
    private Integer maximum=0;
    private Date expiration;

    /**
     *
     */
    public Url() {
    }  
    
    public String getUrl_origin(){return this.url_origin;}    
    public String getUrl_final(){return "http://localhost:8080/ProjetJee/redirect?url="+this.url_final;}    
    public String getCaptcha(){return this.captcha;}
    public String getPwd(){return this.pwd;}
    public String getStart(){return this.date_start;}
    public String getEnd(){return this.date_end;}
    public Date getExpiration(){return this.expiration;}
    public Integer getMaximum(){return this.maximum;}

    /**
     *
     * @param url_origin
     */
    public void setUrl_origin( String url_origin ) {
        
        if(url_origin != null)
            this.url_origin = url_origin.trim();
        
        if(this.url_origin.lastIndexOf("http://") == -1)
            this.url_origin = "http://"+this.url_origin;
        
        System.out.println("URL_ORIGIN : "+this.url_origin);
    }

    /**
     *
     */
    public void setUrl_final(String url) {
        this.url_final = url;
    }
    
    /**
     *
     */
    public void setCaptcha(String captcha) {
        this.captcha = "12345";
        System.out.println("URL_CAPTCHA : "+this.pwd);
    }
    
    /**
     *
     * @param pwd
     */
    public void setPwd( String pwd ) {
        this.pwd = (pwd != null) ? pwd : "";
        System.out.println("URL_PWD : "+this.pwd);
    }
    
    /**
     *
     * @param date
     */
    public void setDate_start( String date ) {
        if(date != null)
            this.date_start = date;
        System.out.println("URL_DATE_START : "+this.date_start);
    }
    
    /**
     *
     * @param date
     */
    public void setDate_end( String date ) {
        if(date != null)
            this.date_end = date;
        System.out.println("URL_DATE_END : "+this.date_end);
    }

    /**
     *
     * @param date
     */
    public void setExpiration( Date date ) {
        this.expiration = date;
        System.out.println("URL_DATE_EXPIRATION : "+this.expiration);
    }
    
    /**
     *
     * @param nb
     */
    public void setMaximum( Integer nb ) {
        this.maximum = (nb != null) ? nb : 0;
        System.out.println("URL_MAXIMUM : "+this.maximum);
    }
    
    /**
     *
     */
    public void init_captcha(){
        //Générateur d'url aléatoire
        /*String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( 5 );
        for(int i = 0; i < 5; i++ ) 
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        this.url_final = sb.toString();*/
    }
    
    /**
     *
     */
    public String init_url(){
        //Générateur d'url aléatoire
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( 5 );
        for(int i = 0; i < 5; i++ ) 
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        this.url_final = sb.toString();
        return this.url_final;
    }

    public void hydrate(ResultSet resultat) throws SQLException {
        try{
            if(resultat.getString("url_origin") != null)
                this.setUrl_origin(resultat.getString("url_origin"));

            if(resultat.getString("url_final") != null)
                this.setUrl_final(resultat.getString("url_final"));

            if(resultat.getString("pwd") != null)
                this.setPwd(resultat.getString("pwd"));

            /*if(resultat.getString("date_start") != null)
                this.setDate_start(resultat.getString("date_start"));

            if(resultat.getString("date_end") != null)
                this.setDate_end(resultat.getString("date_end"));
            
            if(resultat.getString("expiration") != null)
                this.setExpiration(resultat.getString("expiration"));
            */
            if(resultat.getString("maximum") != null)
                this.setMaximum(resultat.getInt("maximum"));
        }
        catch(SQLException e){
            System.out.println("ERREUR HYDRATATION");
            System.out.println(e.getMessage());
        }
    }
}
