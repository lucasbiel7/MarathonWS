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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author OCTI01
 */
@Entity
@Table(name = "checkpoint")
@NamedQueries({
    @NamedQuery(name = "Checkpoint.findAll", query = "SELECT c FROM Checkpoint c")})
public class Checkpoint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "checkpoint")
    private String checkpoint;
    @Column(name = "Landmark")
    private String landmark;
    @Basic(optional = false)
    @Column(name = "drink")
    private boolean drink;
    @Basic(optional = false)
    @Column(name = "energybar")
    private boolean energybar;
    @Basic(optional = false)
    @Column(name = "toilet")
    private boolean toilet;
    @Basic(optional = false)
    @Column(name = "information")
    private boolean information;
    @Basic(optional = false)
    @Column(name = "medical")
    private boolean medical;

    public Checkpoint() {
    }

    public Checkpoint(Integer id) {
        this.id = id;
    }

    public Checkpoint(Integer id, boolean drink, boolean energybar, boolean toilet, boolean information, boolean medical) {
        this.id = id;
        this.drink = drink;
        this.energybar = energybar;
        this.toilet = toilet;
        this.information = information;
        this.medical = medical;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(String checkpoint) {
        this.checkpoint = checkpoint;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public boolean getDrink() {
        return drink;
    }

    public void setDrink(boolean drink) {
        this.drink = drink;
    }

    public boolean getEnergybar() {
        return energybar;
    }

    public void setEnergybar(boolean energybar) {
        this.energybar = energybar;
    }

    public boolean getToilet() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }

    public boolean getInformation() {
        return information;
    }

    public void setInformation(boolean information) {
        this.information = information;
    }

    public boolean getMedical() {
        return medical;
    }

    public void setMedical(boolean medical) {
        this.medical = medical;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Checkpoint)) {
            return false;
        }
        Checkpoint other = (Checkpoint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.MarathonWS.model.entity.Checkpoint[ id=" + id + " ]";
    }
    
}
