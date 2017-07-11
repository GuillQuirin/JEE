
import java.security.SecureRandom;

/**
 *
 * @author guillaumequirin
 */
public class Url {
    
    private String url_origin;
    private String url_final;
    private String captcha;
    private String pwd;
    private String timestamp_expiration;
    private String nb_max_views;
    private String date_start;
    private String date_end;
    private String date_crea;
    private Integer maximum;
    private String expiration;

    public Url() {
        this.setUrl_final();
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
        this.url_origin = (url_origin != null) ? url_origin.trim() : "";
        System.out.println("URL_ORIGIN : "+this.url_origin);
    }

    public void setCaptcha() {
        this.captcha = "12345";
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
     * @param pwd
     */
    public void setDate_start( String pwd ) {
        this.date_start = (pwd != null) ? pwd : "";
        System.out.println("URL_DATE_START : "+this.date_start);
    }
    
    /**
     *
     * @param pwd
     */
    public void setDate_end( String pwd ) {
        this.date_end = (pwd != null) ? pwd : "";
        System.out.println("URL_DATE_END : "+this.date_end);
    }

    public void setMaximum( Integer nb ) {
        this.maximum = (nb != null) ? nb : 0;
        System.out.println("URL_MAXIMUM : "+this.maximum);
    }
 
    public void setExpiration( String nb ) {
        this.expiration = (nb != null) ? nb : "";
        System.out.println("URL_DATE_MAX : "+this.expiration);
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
        this.url_final = "'"+sb.toString()+"'";
    }
}
