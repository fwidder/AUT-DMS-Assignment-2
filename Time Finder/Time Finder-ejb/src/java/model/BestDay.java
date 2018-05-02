/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Florian Widder
 */
public class BestDay {

    private Event event;
    private Date date;

    public Event getEvent() {
        return event;
    }

    public BestDay(Event event, Date date) {
        this.event = event;
        this.date = date;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
