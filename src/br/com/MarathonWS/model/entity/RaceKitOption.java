/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "racekitoption")
@NamedQueries({
    @NamedQuery(name = "RaceKitOption.findAll", query = "SELECT r FROM RaceKitOption r")})
public class RaceKitOption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RaceKitOptionId")
    private String raceKitOptionId;
    @Basic(optional = false)
    @Column(name = "RaceKitOption")
    private String raceKitOption;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Cost")
    private BigDecimal cost;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceKitOptionId")
    private List<Registration> registrationList;

    public RaceKitOption() {
    }

    public RaceKitOption(String raceKitOptionId) {
        this.raceKitOptionId = raceKitOptionId;
    }

    public RaceKitOption(String raceKitOptionId, String raceKitOption, BigDecimal cost) {
        this.raceKitOptionId = raceKitOptionId;
        this.raceKitOption = raceKitOption;
        this.cost = cost;
    }

    public String getRaceKitOptionId() {
        return raceKitOptionId;
    }

    public void setRaceKitOptionId(String raceKitOptionId) {
        this.raceKitOptionId = raceKitOptionId;
    }

    public String getRaceKitOption() {
        return raceKitOption;
    }

    public void setRaceKitOption(String raceKitOption) {
        this.raceKitOption = raceKitOption;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
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
        hash += (raceKitOptionId != null ? raceKitOptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RaceKitOption)) {
            return false;
        }
        RaceKitOption other = (RaceKitOption) object;
        if ((this.raceKitOptionId == null && other.raceKitOptionId != null) || (this.raceKitOptionId != null && !this.raceKitOptionId.equals(other.raceKitOptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.MarathonWS.model.entity.RaceKitOption[ raceKitOptionId=" + raceKitOptionId + " ]";
    }
    
}
