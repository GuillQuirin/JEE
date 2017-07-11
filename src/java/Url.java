
import java.security.SecureRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author guillaumequirin
 */
public class Url {
    
    private String url_origin;
    private String url_final;
    private String pwd;
    private String timestamp_expiration;
    private String nb_max_views;
    private String date_crea;

    public Url() {
        this.setUrl_final();
    }  
    
    public String getUrl_origin(){
        return this.url_origin;
    }
    
    public String getUrl_final(){
        return this.url_final;
    }
    
    public String getPwd(){
        return this.pwd;
    }
    
    public void setUrl_origin( String url_origin ) {
        this.url_origin = (url_origin != null) ? "'"+url_origin.trim()+"'" : null;
        System.out.println("URL_ORIGIN : "+this.url_origin);
    }

    public void setPwd( String pwd ) {
        this.pwd = (pwd != null) ? "'"+pwd+"'" : null;
        System.out.println("URL_PWD : "+this.pwd);
    }

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
