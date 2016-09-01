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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author OCTI01
 */
@Entity
@Table(name = "marathon")
@NamedQueries({
    @NamedQuery(name = "Marathon.findAll", query = "SELECT m FROM Marathon m")})
public class Marathon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MarathonId")
    private Short marathonId;
    @Basic(optional = false)
    @Column(name = "MarathonName")
    private String marathonName;
    @Column(name = "CityName")
    private String cityName;
    @Column(name = "YearHeld")
    private Short yearHeld;
    @JoinColumn(name = "CountryCode", referencedColumnName = "CountryCode")
    @ManyToOne(optional = false)
    private Country countryCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "marathonId")
    private List<Event> eventList;

    public Marathon() {
    }

    public Marathon(Short marathonId) {
        this.marathonId = marathonId;
    }

    public Marathon(Short marathonId, String marathonName) {
        this.marathonId = marathonId;
        this.marathonName = marathonName;
    }

    public Short getMarathonId() {
        return marathonId;
    }

    public void setMarathonId(Short marathonId) {
        this.marathonId = marathonId;
    }

    public String getMarathonName() {
        return marathonName;
    }

    public void setMarathonName(String marathonName) {
        this.marathonName = marathonName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Short getYearHeld() {
        return yearHeld;
    }

    public void setYearHeld(Short yearHeld) {
        this.yearHeld = yearHeld;
    }

    public Country getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Country countryCode) {
        this.countryCode = countryCode;
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
        hash += (marathonId != null ? marathonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marathon)) {
            return false;
        }
        Marathon other = (Marathon) object;
        if ((this.marathonId == null && other.marathonId != null) || (this.marathonId != null && !this.marathonId.equals(other.marathonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getMarathonName();
    }

}
