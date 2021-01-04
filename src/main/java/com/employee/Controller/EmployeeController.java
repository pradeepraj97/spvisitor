package com.employee.Controller;

import com.employee.Exception.DepartmentNotFoundException;
import com.employee.Exception.UserNotFoundException;
import com.employee.Model.Area;
import com.employee.Model.Department;
import com.employee.Model.Roles;
import com.employee.Model.Employee;
import com.employee.Service.AreaService;
import com.employee.Service.DepartmentService;
import com.employee.Service.EmployeeService;

import com.employee.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;
    @Autowired
    private DepartmentService deptService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AreaService areaService;

    @PostMapping(value = "/newuser")
    public void addUser(@RequestBody Employee employee) throws DepartmentNotFoundException {
      String dept = employee.getDepartment().getDeptName();
      String role = employee.getRole().getRoleName();
      String area = employee.getArea().getAreaStatus();
      Department i = deptService.getByDeptName(dept);
      Roles r = roleService.getByRoleName(role);
        Area a = areaService.getByAreaStatus(area);
      int d = i.getDeptId();
     System.out.println(d);
     employee.setDepartment(i);
     employee.setRole(r);
     employee.setArea(a);
        service.addUser(employee);
    }

    @PostMapping(value = "/login")
    public String getByEmailIdAndPassword(@RequestBody Employee employee) throws UserNotFoundException, JSONException {
        String emailId = employee.getEmailId();
        String password = employee.getPassword();
        JSONArray jsonArray = new JSONArray();
        Employee login= service.getByEmailIdAndPassword(emailId,password);
        JSONObject log = new JSONObject();
        log.put("id",login.getId());
        log.put("emailId",login.getEmailId());
        log.put("username",login.getUsername());
        log.put("deptName",login.getDepartment().getDeptName());
        log.put("roleName",login.getRole().getRoleName());
        log.put("phoneNumber",login.getPhoneNumber());
        log.put("areaStatus",login.getArea().getAreaStatus());
        jsonArray.put(log);
        if(login.equals(null)) {
            throw new UserNotFoundException("User Not Found");
        }else{
            return jsonArray.toString();
        }
    }
//    @PostMapping(value = "/login")
//    public String getByEmailIdAndPassword(@RequestBody Employee employee) throws UserNotFoundException {
//        String emailId = employee.getEmailId();
//        System.out.println(emailId);
//        String password = employee.getPassword();
//        Employee login= service.getByEmailIdAndPassword(emailId,password);
//
//        if(login.equals(null)) {
//            throw new UserNotFoundException("User Not Found");
//        }else{
//            System.out.println(login);
//           return login.toString();
//        }
//    }

    @PutMapping("/updatepassword/")
    public void updatePassword(@RequestBody Employee employee) throws UserNotFoundException {

        service.updatePassword(employee);

    }

    @GetMapping("/all")
    public String getEmployee() throws JSONException{


        JSONArray jsonArray = new JSONArray();
        try {
            for(Employee emp: service.getEmployee()){
                JSONObject data = new JSONObject();
                data.put("id",emp.getId());
                data.put("emailId",emp.getEmailId());
                data.put("username",emp.getUsername());
                data.put("deptName",emp.getDepartment().getDeptName());
                data.put("roleName",emp.getRole().getRoleName());
                data.put("phoneNumber",emp.getPhoneNumber());
                data.put("areaStatus",emp.getArea().getAreaStatus());
                jsonArray.put(data);
            }

        }catch (Exception e ) {
        }
        return jsonArray.toString();

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) throws UserNotFoundException {
        service.deleteById(id);
    }

    @GetMapping("all/{id}")
    public String getById(@PathVariable Long id) throws UserNotFoundException {

        JSONArray jsonArray = new JSONArray();
        try {
            Employee employee = service.getById(id);
                JSONObject obj = new JSONObject();
            obj.put("emailId",employee.getEmailId());
            obj.put("username",employee.getUsername());
            obj.put("deptName",employee.getDepartment().getDeptName());
            obj.put("roleName",employee.getRole().getRoleName());
            obj.put("phoneNumber",employee.getPhoneNumber());
            obj.put("areaStatus",employee.getArea().getAreaStatus());
                jsonArray.put(obj);
        }catch (Exception e ) {
        }
         return jsonArray.toString();

    }

    @PostMapping("/updateuser/{id}")
    public void updateById(@PathVariable Long id , @RequestBody Employee employee) throws DepartmentNotFoundException, UserNotFoundException {
        String dept = employee.getDepartment().getDeptName();
        String role = employee.getRole().getRoleName();
        String area = employee.getArea().getAreaStatus();
        Department i = deptService.getByDeptName(dept);
        Roles r = roleService.getByRoleName(role);
        Area a = areaService.getByAreaStatus(area);
        int d = i.getDeptId();
        System.out.println(d);
        employee.setDepartment(i);
        employee.setRole(r);
        employee.setArea(a);
        service.updateById(employee,id);
    }
}
