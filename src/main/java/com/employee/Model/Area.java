package com.employee.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the area database table.
 *
 */
@Entity
@NamedQuery(name="Area.findAll", query="SELECT a FROM Area a")
@JsonIdentityInfo(scope = Area.class,generator = ObjectIdGenerators.PropertyGenerator.class,property = "areaId")
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="area_id")
    private int areaId;

    @Column(name="area_status")
    private String areaStatus;

    //bi-directional many-to-one association to Employee

    @OneToMany(mappedBy="area")
    private List<Employee> employees;

    public Area() {
    }

    public int getAreaId() {
        return this.areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaStatus() {
        return this.areaStatus;
    }

    public void setAreaStatus(String areaStatus) {
        this.areaStatus = areaStatus;
    }


    public List<Employee> getEmployees() {
        return this.employees;
    }


    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee addEmployee(Employee employee) {
        getEmployees().add(employee);
        employee.setArea(this);

        return employee;
    }

    public Employee removeEmployee(Employee employee) {
        getEmployees().remove(employee);
        employee.setArea(null);

        return employee;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                ", areaStatus='" + areaStatus + '\'' +
                ", employees=" + employees +
                '}';
    }
}