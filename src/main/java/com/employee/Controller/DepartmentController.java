package com.employee.Controller;

import com.employee.Exception.AreaNotFoundException;
import com.employee.Exception.DepartmentNotFoundException;
import com.employee.Model.Area;
import com.employee.Model.Department;
import com.employee.Model.Employee;
import com.employee.Service.DepartmentService;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService deptService;

    @GetMapping("/name/{deptName}")
    public int getByDeptName( @PathVariable String deptName) throws DepartmentNotFoundException {
        Department dept = deptService.getByDeptName(deptName);
        if (dept.equals(null)) {
            throw new DepartmentNotFoundException("Department Not Found");
        } else {
            int deptId = dept.getDeptId();
            return deptId;
        }
    }

    @PostMapping("/add")
    public void addDept(@RequestBody Department dept){
        deptService.addDept(dept);
    }

    @GetMapping("/all")
    public String allDepartment(){
        JSONArray jsonArray = new JSONArray();
        try {
            for(Department dept : deptService.allDepartment()){

                JSONObject data = new JSONObject();
                data.put("deptId",dept.getDeptId());
                data.put("deptName",dept.getDeptName());
                jsonArray.put(data);
            }

        }catch (Exception e ) {
        }


        return jsonArray.toString();
    }

    @PutMapping("/update/{deptId}")
    public void updateDepartment(@PathVariable int deptId, @RequestBody Department dept) throws DepartmentNotFoundException {
         deptService.updateDepartment(deptId,dept);
    }

    @DeleteMapping("/delete/{deptId}")
    public void deleteDept(@PathVariable int deptId) throws DepartmentNotFoundException {
        deptService.deleteDept(deptId);
    }
    @GetMapping("/id/{deptId}")
    public String getByDeptId(@PathVariable int deptId) throws DepartmentNotFoundException{
        Department dept = deptService.getByDeptId(deptId);
        if (dept.equals(null)) {
            throw new DepartmentNotFoundException("Department Not Found");
        } else {
            String deptName = dept.getDeptName();
            return deptName;
        }
    }
}
