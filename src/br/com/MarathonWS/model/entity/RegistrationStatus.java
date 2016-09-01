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
@Table(name = "registrationstatus")
@NamedQueries({
    @NamedQuery(name = "RegistrationStatus.findAll", query = "SELECT r FROM RegistrationStatus r")})
public class RegistrationStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RegistrationStatusId")
    private Short registrationStatusId;
    @Basic(optional = false)
    @Column(name = "RegistrationStatus")
    private String registrationStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registrationStatusId")
    private List<Registration> registrationList;

    public RegistrationStatus() {
    }

    public RegistrationStatus(Short registrationStatusId) {
        this.registrationStatusId = registrationStatusId;
    }

    public RegistrationStatus(Short registrationStatusId, String registrationStatus) {
        this.registrationStatusId = registrationStatusId;
        this.registrationStatus = registrationStatus;
    }

    public Short getRegistrationStatusId() {
        return registrationStatusId;
    }

    public void setRegistrationStatusId(Short registrationStatusId) {
        this.registrationStatusId = registrationStatusId;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
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
        hash += (registrationStatusId != null ? registrationStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrationStatus)) {
            return false;
        }
        RegistrationStatus other = (RegistrationStatus) object;
        if ((this.registrationStatusId == null && other.registrationStatusId != null) || (this.registrationStatusId != null && !this.registrationStatusId.equals(other.registrationStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getRegistrationStatus();
    }

}
