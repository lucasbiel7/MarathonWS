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
@Table(name = "volunteer")
@NamedQueries({
    @NamedQuery(name = "Volunteer.findAll", query = "SELECT v FROM Volunteer v")})
public class Volunteer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VolunteerId")
    private Integer volunteerId;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @JoinColumn(name = "CountryCode", referencedColumnName = "CountryCode")
    @ManyToOne(optional = false)
    private Country countryCode;
    @JoinColumn(name = "Gender", referencedColumnName = "Gender")
    @ManyToOne(optional = false)
    private Gender gender;

    public Volunteer() {
    }

    public Volunteer(Integer volunteerId) {
        this.volunteerId = volunteerId;
    }

    public Integer getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Integer volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Country getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Country countryCode) {
        this.countryCode = countryCode;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (volunteerId != null ? volunteerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Volunteer)) {
            return false;
        }
        Volunteer other = (Volunteer) object;
        if ((this.volunteerId == null && other.volunteerId != null) || (this.volunteerId != null && !this.volunteerId.equals(other.volunteerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.MarathonWS.model.entity.Volunteer[ volunteerId=" + volunteerId + " ]";
    }
    
}
