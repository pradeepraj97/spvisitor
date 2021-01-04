package com.employee.Service;

import com.employee.Exception.RoleNotFoundException;
import com.employee.Dao.RoleDao;
import com.employee.Model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public Roles getByRoleName(String roleName) {
        return roleDao.getByRoleName(roleName);
    }

    public void addRole(Roles role) {
        roleDao.save(role);
    }

    public List<Roles> allRoles() {
        return roleDao.findAll();
    }

    public void updateRole(int roleId, Roles role) throws RoleNotFoundException {
        Optional<Roles > updateRole = this.roleDao.findById(roleId);
        if (updateRole.isPresent()){
            String roleName = role.getRoleName();
             roleDao.saveByRoleName(roleName,roleId);
        }else{
            throw new RoleNotFoundException("Role Not Found");
        }
    }

    public void deleteRole(int roleId) throws RoleNotFoundException {
        Optional<Roles > updateRole = this.roleDao.findById(roleId);
        if (updateRole.isPresent()){
             roleDao.deleteById(roleId);
        }else{
            throw new RoleNotFoundException("Role Not Found");
        }
    }

    public Roles getByRoleId(int roleId) {
        return roleDao.getByRoleId(roleId);
    }
}
