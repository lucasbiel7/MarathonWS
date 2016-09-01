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
@Table(name = "registration")
@NamedQueries({
    @NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r")})
public class Registration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RegistrationId")
    private Integer registrationId;
    @Basic(optional = false)
    @Column(name = "RegistrationDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDateTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Cost")
    private BigDecimal cost;
    @Basic(optional = false)
    @Column(name = "SponsorshipTarget")
    private BigDecimal sponsorshipTarget;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registrationId")
    private List<RegistrationEvent> registrationEventList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registrationId")
    private List<Sponsorship> sponsorshipList;
    @JoinColumn(name = "RunnerId", referencedColumnName = "RunnerId")
    @ManyToOne(optional = false)
    private Runner runnerId;
    @JoinColumn(name = "RaceKitOptionId", referencedColumnName = "RaceKitOptionId")
    @ManyToOne(optional = false)
    private RaceKitOption raceKitOptionId;
    @JoinColumn(name = "RegistrationStatusId", referencedColumnName = "RegistrationStatusId")
    @ManyToOne(optional = false)
    private RegistrationStatus registrationStatusId;
    @JoinColumn(name = "CharityId", referencedColumnName = "CharityId")
    @ManyToOne(optional = false)
    private Charity charityId;

    public Registration() {
    }

    public Registration(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Registration(Integer registrationId, Date registrationDateTime, BigDecimal cost, BigDecimal sponsorshipTarget) {
        this.registrationId = registrationId;
        this.registrationDateTime = registrationDateTime;
        this.cost = cost;
        this.sponsorshipTarget = sponsorshipTarget;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Date getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(Date registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getSponsorshipTarget() {
        return sponsorshipTarget;
    }

    public void setSponsorshipTarget(BigDecimal sponsorshipTarget) {
        this.sponsorshipTarget = sponsorshipTarget;
    }

    public List<RegistrationEvent> getRegistrationEventList() {
        return registrationEventList;
    }

    public void setRegistrationEventList(List<RegistrationEvent> registrationEventList) {
        this.registrationEventList = registrationEventList;
    }

    public List<Sponsorship> getSponsorshipList() {
        return sponsorshipList;
    }

    public void setSponsorshipList(List<Sponsorship> sponsorshipList) {
        this.sponsorshipList = sponsorshipList;
    }

    public Runner getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(Runner runnerId) {
        this.runnerId = runnerId;
    }

    public RaceKitOption getRaceKitOptionId() {
        return raceKitOptionId;
    }

    public void setRaceKitOptionId(RaceKitOption raceKitOptionId) {
        this.raceKitOptionId = raceKitOptionId;
    }

    public RegistrationStatus getRegistrationStatusId() {
        return registrationStatusId;
    }

    public void setRegistrationStatusId(RegistrationStatus registrationStatusId) {
        this.registrationStatusId = registrationStatusId;
    }

    public Charity getCharityId() {
        return charityId;
    }

    public void setCharityId(Charity charityId) {
        this.charityId = charityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registrationId != null ? registrationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registration)) {
            return false;
        }
        Registration other = (Registration) object;
        if ((this.registrationId == null && other.registrationId != null) || (this.registrationId != null && !this.registrationId.equals(other.registrationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.MarathonWS.model.entity.Registration[ registrationId=" + registrationId + " ]";
    }
    
}
