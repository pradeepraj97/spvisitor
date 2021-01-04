package com.employee.Dao;

import com.employee.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RoleDao extends JpaRepository<Roles,Integer> {
   public Roles getByRoleName(String roleName);

   public Roles getByRoleId(int roleId);

    @Transactional
    @Modifying
    @Query("UPDATE Roles r set r.roleName = :roleName where r.roleId= :roleId")
    public void saveByRoleName(String roleName, int roleId);
}
