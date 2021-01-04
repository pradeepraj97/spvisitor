package com.employee.Controller;

import com.employee.Exception.AreaNotFoundException;
import com.employee.Exception.RoleNotFoundException;
import com.employee.Model.Area;
import com.employee.Model.Department;
import com.employee.Model.Roles;
import com.employee.Service.RoleService;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/name/{roleName}")
    public int getByRoleName( @PathVariable String roleName) throws RoleNotFoundException {
        Roles role=roleService.getByRoleName(roleName);
        if (role.equals(null)) {
            throw new RoleNotFoundException("Role Not Found");
        } else {
            int roleId = role.getRoleId();
            return roleId;
        }
    }

    @PostMapping("/add")
    public void addRole(@RequestBody Roles role){
        roleService.addRole(role);
    }

    @GetMapping("/all")
    public String allRoles(){
        JSONArray jsonArray = new JSONArray();
        try {
            for(Roles role : roleService.allRoles()){

                JSONObject data = new JSONObject();
                data.put("roleId",role.getRoleId());
                data.put("roleName",role.getRoleName());
                jsonArray.put(data);
            }

        }catch (Exception e ) {
        }


        return jsonArray.toString();
    }

    @PutMapping("/update/{roleId}")
    public void updateRole(@PathVariable int roleId, @RequestBody Roles role) throws RoleNotFoundException {
        roleService.updateRole(roleId,role);
    }

    @DeleteMapping("/delete/{roleId}")
    public void deleteRole(@PathVariable int roleId) throws RoleNotFoundException {
        roleService.deleteRole(roleId);
    }
    @GetMapping("/id/{roleId}")
    public String getByRoleId(@PathVariable int roleId) throws RoleNotFoundException {
        Roles role = roleService.getByRoleId(roleId);
        if (role.equals(null)) {
            throw new RoleNotFoundException("Role Not Found");
        } else {
            String roleName = role.getRoleName();
            return roleName;
        }
    }
}
