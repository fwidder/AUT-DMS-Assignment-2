/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Florian Widder
 */
public class Event {

    public Event() {
    }

    public Event(int eventID, String name, String description, Date start, Date end, Date best, User creator) {
        this.eventID = eventID;
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
        this.best = best;
        this.creator = creator;
    }

    private int eventID;
    private String name;
    private String description;
    private Date start;
    private Date end;
    private Date best;
    private User creator;

    public int getEventID() {
        return eventID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Date getBest() {
        return best;
    }

    public User getCreator() {
        return creator;
    }

    @Override
    public String toString() {
        return "Event{" + "eventID=" + eventID + ", name=" + name + ", description=" + description + ", start=" + start + ", end=" + end + ", best=" + best + ", creator=" + creator + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.eventID;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + Objects.hashCode(this.start);
        hash = 41 * hash + Objects.hashCode(this.end);
        hash = 41 * hash + Objects.hashCode(this.best);
        hash = 41 * hash + Objects.hashCode(this.creator);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (this.eventID != other.eventID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        if (!Objects.equals(this.best, other.best)) {
            return false;
        }
        if (!Objects.equals(this.creator, other.creator)) {
            return false;
        }
        return true;
    }
}
