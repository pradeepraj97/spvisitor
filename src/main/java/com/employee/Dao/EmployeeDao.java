package com.employee.Dao;

import com.employee.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Long> {


//    @Transactional
//    @Query(value = "SELECT employee.id, employee.email_id, area.area_status, department.dept_name,employee.phone_Number,roles.role_name,employee.username FROM employee LEFT JOIN area ON area.area_id = employee.area_id LEFT JOIN department ON department.dept_id = employee.dept_id LEFT JOIN roles ON roles.role_id = employee.role_id WHERE  employee.email_id = ?1 ",nativeQuery = true)
   public Employee getByEmailId( String emailId);

   public Optional<Employee> findByEmailId(String emailId);

   public Employee getById(long id);



    @Transactional
    @Modifying
    @Query("UPDATE Employee e set e.username = :username, e.emailId = :emailId , e.area.areaId= :area_id , e.department.deptId = :dept_id , e.role.roleId = :role_id , e.phoneNumber = :phoneNumber where e.id = :id")
    public void saveById(String username, long id, String emailId, String phoneNumber, int area_id, int role_id, int dept_id);

   public  Optional<Employee> findByPassword(String password);
}
