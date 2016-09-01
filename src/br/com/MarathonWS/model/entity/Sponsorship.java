/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "sponsorship")
@NamedQueries({
    @NamedQuery(name = "Sponsorship.findAll", query = "SELECT s FROM Sponsorship s")})
public class Sponsorship implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SponsorshipId")
    private Integer sponsorshipId;
    @Basic(optional = false)
    @Column(name = "SponsorName")
    private String sponsorName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Amount")
    private BigDecimal amount;
    @JoinColumn(name = "RegistrationId", referencedColumnName = "RegistrationId")
    @ManyToOne(optional = false)
    private Registration registrationId;

    public Sponsorship() {
    }

    public Sponsorship(Integer sponsorshipId) {
        this.sponsorshipId = sponsorshipId;
    }

    public Sponsorship(Integer sponsorshipId, String sponsorName, BigDecimal amount) {
        this.sponsorshipId = sponsorshipId;
        this.sponsorName = sponsorName;
        this.amount = amount;
    }

    public Integer getSponsorshipId() {
        return sponsorshipId;
    }

    public void setSponsorshipId(Integer sponsorshipId) {
        this.sponsorshipId = sponsorshipId;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Registration getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Registration registrationId) {
        this.registrationId = registrationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sponsorshipId != null ? sponsorshipId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sponsorship)) {
            return false;
        }
        Sponsorship other = (Sponsorship) object;
        if ((this.sponsorshipId == null && other.sponsorshipId != null) || (this.sponsorshipId != null && !this.sponsorshipId.equals(other.sponsorshipId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.MarathonWS.model.entity.Sponsorship[ sponsorshipId=" + sponsorshipId + " ]";
    }
    
}
