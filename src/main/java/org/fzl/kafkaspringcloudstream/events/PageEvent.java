package org.fzl.kafkaspringcloudstream.events;

import java.util.Date;

public class PageEvent {

    private String name;
    private String user;
    private Date date;
    private long duration;

    // 1. Constructeur vide (OBLIGATOIRE pour que Kafka/Jackson puisse désérialiser)
    public PageEvent() {
    }

    // 2. Constructeur avec tous les arguments (Celui que votre contrôleur utilise)
    public PageEvent(String name, String user, Date date, long duration) {
        this.name = name;
        this.user = user;
        this.date = date;
        this.duration = duration;
    }

    // 3. Les Getters et Setters (INDISPENSABLES pour la conversion JSON <-> Objet)
    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date date() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long duration() {
        return duration;
    }

    public void duration(long duration) {
        this.duration = duration;
    }
}