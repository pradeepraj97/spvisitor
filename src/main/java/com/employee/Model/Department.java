package com.employee.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the department database table.
 *
 */
@Entity
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
@JsonIdentityInfo(scope = Department.class,generator = ObjectIdGenerators.PropertyGenerator.class,property = "deptId")
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dept_id")
    private int deptId;

    @Column(name="dept_name")
    private String deptName;

    //bi-directional many-to-one association to Employee

    @OneToMany(mappedBy="department")
    private List<Employee> employees;

    public Department() {
    }

    public int getDeptId() {
        return this.deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public List<Employee> getEmployees() {
        return this.employees;
    }


    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee addEmployee(Employee employee) {
        getEmployees().add(employee);
        employee.setDepartment(this);

        return employee;
    }

    public Employee removeEmployee(Employee employee) {
        getEmployees().remove(employee);
        employee.setDepartment(null);

        return employee;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", employees=" + employees +
                '}';
    }
}