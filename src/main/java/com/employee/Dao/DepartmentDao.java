package com.employee.Dao;

import com.employee.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DepartmentDao extends JpaRepository<Department,Integer> {
  public Department findByDeptName(String deptName);

  public Department getByDeptId(int deptId);

  @Transactional
  @Modifying
  @Query("UPDATE Department  d set d.deptName = :deptName where d.deptId = :deptId")
   public void saveByRoleName(String deptName, int deptId);
}
