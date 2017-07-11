
import java.security.SecureRandom;

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
    private String expiration="";

    /**
     *
     */
    public Url() {
    }  
    
    public String getUrl_origin(){return this.url_origin;}    
    public String getUrl_final(){return this.url_final;}    
    public String getCaptcha(){return this.captcha;}
    public String getPwd(){return this.pwd;}
    public String getStart(){return this.date_start;}
    public String getEnd(){return this.date_end;}
    public Integer getMaximum(){return this.maximum;}
    public String getExpiration(){return this.expiration;}

    /**
     *
     * @param url_origin
     */
    public void setUrl_origin( String url_origin ) {
        if(url_origin != null)
            this.url_origin = url_origin.trim();
        System.out.println("URL_ORIGIN : "+this.url_origin);
    }

    /**
     *
     */
    public void setCaptcha() {
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
     * @param nb
     */
    public void setMaximum( Integer nb ) {
        if(nb != null)
            this.maximum = nb;
        System.out.println("URL_MAXIMUM : "+this.maximum);
    }
 
    /**
     *
     * @param date
     */
    public void setExpiration( String date ) {
        if(date != null)
            this.expiration = date;
        System.out.println("URL_DATE_EXPIRATION : "+this.expiration);
    }

    /**
     *
     */
    public void setUrl_final() {
        //Générateur d'url aléatoire
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( 5 );
        for(int i = 0; i < 5; i++ ) 
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        this.url_final = "http://localhost:8080/ProjetJee/redirect?url="+sb.toString();
    }
}
