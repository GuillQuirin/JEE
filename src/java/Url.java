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

    // public Url() {
    // 	this.url_origin = "Pas de nom";
    // 	this.url_final = "Pas de mot de passe";
    // }

    public String getUrl_origin(){
            return this.url_origin;
    }

    public String getPwd(){
            return this.pwd;
    }

    /*public String getUrl_final(){
            return this.url_final;
    }*/

    public void setUrl_origin( String url_origin ) {
            this.url_origin = url_origin;
    }

    public void setPwd( String pwd ) {
            this.pwd = pwd;
    }

    /*public void setUrl_final( String url_final ) {
            this.url_final = url_final;
    }*/
}
