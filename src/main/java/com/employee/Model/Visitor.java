package com.employee.Model;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



/**
 * The persistent class for the visitor database table.
 *
 */
@Entity
@NamedQuery(name="Visitor.findAll", query="SELECT v FROM Visitor v")
@Table(name = "visitor")
public class Visitor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="visitor_id")
    private int visitorId;

    @Column(name="authorized_by")
    private String authorizedBy;

    @JsonFormat(pattern="YYYY-MM-dd")
    private LocalDate date;

    private String department;

    @Column(name="employee_name")
    private String employeeName;

    @Column(name="in_time")
    @JsonFormat(pattern="HH:mm:ss",timezone = "Asia/Kolkatta")
    private Time inTime;

    @Column(name="out_time")
    @JsonFormat(pattern="HH:mm:ss",timezone = "Asia/Kolkatta")
    private Time outTime;

    private String purpose;

    @Column(name="visitor_name")
    private String visitorName;

    @Column(name="Status")
    private String status;

    @Column(name="area")
    private String area;

    public Visitor() {
    }

    public int getVisitorId() {
        return this.visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public String getAuthorizedBy() {
        return this.authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate newDate = LocalDate.parse(date,formatter);
        this.date = newDate;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Time getInTime() {
        return this.inTime;
    }

    public void setInTime(Time inTime) {
        this.inTime = inTime;
    }

    public Time getOutTime() {
        return this.outTime;
    }

    public void setOutTime(Time outTime) {
        this.outTime = outTime;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getVisitorName() {
        return this.visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}