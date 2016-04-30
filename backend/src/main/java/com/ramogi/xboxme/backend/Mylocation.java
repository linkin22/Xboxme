package com.ramogi.xboxme.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;

/**
 * Created by ramogiochola on 4/28/16.
 */
@Entity
public class Mylocation {


    @Id
    private String email;
    private double latx;
    private double longy;
    private String gamertag;
    private String displayname;
    private String url;
    private Date updatetime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatx() {
        return latx;
    }

    public void setLatx(double latx) {
        this.latx = latx;
    }

    public double getLongy() {
        return longy;
    }

    public void setLongy(double longy) {
        this.longy = longy;
    }

    public String getGamertag() {
        return gamertag;
    }

    public void setGamertag(String gamertag) {
        this.gamertag = gamertag;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
