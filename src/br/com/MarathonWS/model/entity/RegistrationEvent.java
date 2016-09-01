/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author OCTI01
 */
@Entity
@Table(name = "registrationevent")
@NamedQueries({
    @NamedQuery(name = "RegistrationEvent.findAll", query = "SELECT r FROM RegistrationEvent r")})
public class RegistrationEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RegistrationEventId")
    private Integer registrationEventId;
    @Column(name = "BibNumber")
    private Short bibNumber;
    @Column(name = "RaceTime")
    private Integer raceTime;
    @JoinColumn(name = "RegistrationId", referencedColumnName = "RegistrationId")
    @ManyToOne(optional = false)
    private Registration registrationId;
    @JoinColumn(name = "EventId", referencedColumnName = "EventId")
    @ManyToOne(optional = false)
    private Event eventId;

    public RegistrationEvent() {
    }

    public RegistrationEvent(Integer registrationEventId) {
        this.registrationEventId = registrationEventId;
    }

    public Integer getRegistrationEventId() {
        return registrationEventId;
    }

    public void setRegistrationEventId(Integer registrationEventId) {
        this.registrationEventId = registrationEventId;
    }

    public Short getBibNumber() {
        return bibNumber;
    }

    public void setBibNumber(Short bibNumber) {
        this.bibNumber = bibNumber;
    }

    public Integer getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(Integer raceTime) {
        this.raceTime = raceTime;
    }

    public Registration getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Registration registrationId) {
        this.registrationId = registrationId;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registrationEventId != null ? registrationEventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrationEvent)) {
            return false;
        }
        RegistrationEvent other = (RegistrationEvent) object;
        if ((this.registrationEventId == null && other.registrationEventId != null) || (this.registrationEventId != null && !this.registrationEventId.equals(other.registrationEventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getRegistrationId().getRunnerId().getEmail().getLastName() + ", " + getRegistrationId().getRunnerId().getEmail().getFirstName() + " - " + getBibNumber() + "(" + getRegistrationId().getRunnerId().getCountryCode().getCountryCode() + ")";
    }

}
