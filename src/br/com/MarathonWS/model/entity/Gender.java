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
@Table(name = "gender")
@NamedQueries({
    @NamedQuery(name = "Gender.findAll", query = "SELECT g FROM Gender g")})
public class Gender implements Serializable {

    @OneToMany(mappedBy = "gender")
    private List<Staff> staffList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Gender")
    private String gender;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    private List<Volunteer> volunteerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    private List<Runner> runnerList;

    public Gender() {
    }

    public Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        hash += (gender != null ? gender.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gender)) {
            return false;
        }
        Gender other = (Gender) object;
        if ((this.gender == null && other.gender != null) || (this.gender != null && !this.gender.equals(other.gender))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getGender();
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

}
