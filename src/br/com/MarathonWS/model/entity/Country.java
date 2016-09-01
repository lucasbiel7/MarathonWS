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
@Table(name = "country")
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CountryCode")
    private String countryCode;
    @Basic(optional = false)
    @Column(name = "CountryName")
    private String countryName;
    @Basic(optional = false)
    @Column(name = "CountryFlag")
    private String countryFlag;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryCode")
    private List<Marathon> marathonList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryCode")
    private List<Volunteer> volunteerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryCode")
    private List<Runner> runnerList;

    public Country() {
    }

    public Country(String countryCode) {
        this.countryCode = countryCode;
    }

    public Country(String countryCode, String countryName, String countryFlag) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.countryFlag = countryFlag;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public List<Marathon> getMarathonList() {
        return marathonList;
    }

    public void setMarathonList(List<Marathon> marathonList) {
        this.marathonList = marathonList;
    }

    public List<Volunteer> getVolunteerList() {
        return volunteerList;
    }

    public void setVolunteerList(List<Volunteer> volunteerList) {
        this.volunteerList = volunteerList;
    }

    public List<Runner> getRunnerList() {
        return runnerList;
    }

    public void setRunnerList(List<Runner> runnerList) {
        this.runnerList = runnerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryCode != null ? countryCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.countryCode == null && other.countryCode != null) || (this.countryCode != null && !this.countryCode.equals(other.countryCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCountryName();
    }

}
