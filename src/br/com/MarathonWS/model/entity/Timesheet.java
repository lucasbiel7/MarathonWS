/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.model.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author OCTI01
 */
@Entity
@Table(name = "timesheet")
@NamedQueries({
    @NamedQuery(name = "Timesheet.findAll", query = "SELECT t FROM Timesheet t")})
public class Timesheet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TimesheetId")
    private Integer timesheetId;
    @Column(name = "StartDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Column(name = "EndDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PayAmount")
    private Double payAmount;
    @JoinColumn(name = "StaffId", referencedColumnName = "StaffId")
    @ManyToOne(optional = false)
    private Staff staffId;

    public Timesheet() {
    }

    public Timesheet(Integer timesheetId) {
        this.timesheetId = timesheetId;
    }

    public Integer getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(Integer timesheetId) {
        this.timesheetId = timesheetId;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public Staff getStaffId() {
        return staffId;
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timesheetId != null ? timesheetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timesheet)) {
            return false;
        }
        Timesheet other = (Timesheet) object;
        if ((this.timesheetId == null && other.timesheetId != null) || (this.timesheetId != null && !this.timesheetId.equals(other.timesheetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.MarathonWS.model.entity.Timesheet[ timesheetId=" + timesheetId + " ]";
    }
    
}
