/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author OCTI01
 */
@Entity
@Table(name = "event")
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e")})
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EventId")
    private String eventId;
    @Basic(optional = false)
    @Column(name = "EventName")
    private String eventName;
    @Column(name = "StartDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Cost")
    private BigDecimal cost;
    @Column(name = "MaxParticipants")
    private Short maxParticipants;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
    private List<RegistrationEvent> registrationEventList;
    @JoinColumn(name = "EventTypeId", referencedColumnName = "EventTypeId")
    @ManyToOne(optional = false)
    private EventType eventTypeId;
    @JoinColumn(name = "MarathonId", referencedColumnName = "MarathonId")
    @ManyToOne(optional = false)
    private Marathon marathonId;

    public Event() {
    }

    public Event(String eventId) {
        this.eventId = eventId;
    }

    public Event(String eventId, String eventName) {
        this.eventId = eventId;
        this.eventName = eventName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Short getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Short maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public List<RegistrationEvent> getRegistrationEventList() {
        return registrationEventList;
    }

    public void setRegistrationEventList(List<RegistrationEvent> registrationEventList) {
        this.registrationEventList = registrationEventList;
    }

    public EventType getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(EventType eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public Marathon getMarathonId() {
        return marathonId;
    }

    public void setMarathonId(Marathon marathonId) {
        this.marathonId = marathonId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getEventName();
    }
    
}
