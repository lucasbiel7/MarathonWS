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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author OCTI01
 */
@Entity
@Table(name = "charity")
@NamedQueries({
    @NamedQuery(name = "Charity.findAll", query = "SELECT c FROM Charity c")})
public class Charity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CharityId")
    private Integer charityId;
    @Basic(optional = false)
    @Column(name = "CharityName")
    private String charityName;
    @Column(name = "CharityDescription")
    private String charityDescription;
    @Column(name = "CharityLogo")
    private String charityLogo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "charityId")
    private List<Registration> registrationList;

    public Charity() {
    }

    public Charity(Integer charityId) {
        this.charityId = charityId;
    }

    public Charity(Integer charityId, String charityName) {
        this.charityId = charityId;
        this.charityName = charityName;
    }

    public Integer getCharityId() {
        return charityId;
    }

    public void setCharityId(Integer charityId) {
        this.charityId = charityId;
    }

    public String getCharityName() {
        return charityName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    public String getCharityDescription() {
        return charityDescription;
    }

    public void setCharityDescription(String charityDescription) {
        this.charityDescription = charityDescription;
    }

    public String getCharityLogo() {
        return charityLogo;
    }

    public void setCharityLogo(String charityLogo) {
        this.charityLogo = charityLogo;
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registrationList) {
        this.registrationList = registrationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (charityId != null ? charityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Charity)) {
            return false;
        }
        Charity other = (Charity) object;
        if ((this.charityId == null && other.charityId != null) || (this.charityId != null && !this.charityId.equals(other.charityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCharityName();
    }

}
