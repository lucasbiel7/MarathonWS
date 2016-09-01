/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.model.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author OCTI01
 */
@Entity
@Table(name = "runner")
@NamedQueries({
    @NamedQuery(name = "Runner.findAll", query = "SELECT r FROM Runner r")})
public class Runner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RunnerId")
    private Integer runnerId;
    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "runnerId")
    private List<Registration> registrationList;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private User email;
    @JoinColumn(name = "Gender", referencedColumnName = "Gender")
    @ManyToOne(optional = false)
    private Gender gender;
    @JoinColumn(name = "CountryCode", referencedColumnName = "CountryCode")
    @ManyToOne(optional = false)
    private Country countryCode;

    public Runner() {
    }

    public Runner(Integer runnerId) {
        this.runnerId = runnerId;
    }

    public Integer getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(Integer runnerId) {
        this.runnerId = runnerId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registrationList) {
        this.registrationList = registrationList;
    }

    public User getEmail() {
        return email;
    }

    public void setEmail(User email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Country getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Country countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (runnerId != null ? runnerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Runner)) {
            return false;
        }
        Runner other = (Runner) object;
        if ((this.runnerId == null && other.runnerId != null) || (this.runnerId != null && !this.runnerId.equals(other.runnerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getEmail().getFirstName() + " " + getEmail().getLastName();
    }

}
