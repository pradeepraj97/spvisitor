package com.employee.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the employee database table.
 *
 */
@Entity
//@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
@Table(name = "employee")
@JsonIdentityInfo(scope = Employee.class,generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name="email_id")
    private String emailId;

    private String password;

    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    //bi-directional many-to-one association to Area

    @ManyToOne
    @JoinColumn(name="area_id")
    private Area area;

    //bi-directional many-to-one association to Department

    @ManyToOne
    @JoinColumn(name="dept_id")
    private Department department;

    //bi-directional many-to-one association to Role

    @ManyToOne
    @JoinColumn(name="role_id")
    private Roles role;

    public Employee() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Roles getRole() {
        return this.role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", area=" + area +
                ", department=" + department +
                ", role=" + role +
                '}';
    }
}