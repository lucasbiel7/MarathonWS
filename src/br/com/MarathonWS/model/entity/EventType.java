/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author OCTI01
 */
@Entity
@Table(name = "eventtype")
@NamedQueries({
    @NamedQuery(name = "EventType.findAll", query = "SELECT e FROM EventType e")})
public class EventType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EventTypeId")
    private String eventTypeId;
    @Basic(optional = false)
    @Column(name = "EventTypeName")
    private String eventTypeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventTypeId")
    private List<Event> eventList;

    public EventType() {
    }

    public EventType(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public EventType(String eventTypeId, String eventTypeName) {
        this.eventTypeId = eventTypeId;
        this.eventTypeName = eventTypeName;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventTypeId != null ? eventTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventType)) {
            return false;
        }
        EventType other = (EventType) object;
        if ((this.eventTypeId == null && other.eventTypeId != null) || (this.eventTypeId != null && !this.eventTypeId.equals(other.eventTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.MarathonWS.model.entity.EventType[ eventTypeId=" + eventTypeId + " ]";
    }
    
}
