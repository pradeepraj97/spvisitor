package com.employee.Service;

import com.employee.Exception.DepartmentNotFoundException;
import com.employee.Dao.DepartmentDao;
import com.employee.Model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentDao deptDao;
    public Department getByDeptName(String deptName) throws DepartmentNotFoundException {
       Department dept = deptDao.findByDeptName(deptName);
       if(dept.equals(null)){
           throw new DepartmentNotFoundException("Department Not Found");
       }else{
           return dept;
       }
    }

    public void addDept(Department dept) {
        deptDao.save(dept);
    }

    public List<Department> allDepartment() {
        return deptDao.findAll();
    }

    public void updateDepartment(int deptId, Department dept) throws DepartmentNotFoundException {
        Optional<Department> updateDept = this.deptDao.findById(deptId);
        if (updateDept.isPresent()){
            String deptName = dept.getDeptName();
             deptDao.saveByRoleName(deptName,deptId);
        }else{
            throw new DepartmentNotFoundException("Department Not Found");
        }
    }

    public void deleteDept(int deptId) throws DepartmentNotFoundException {
        Optional<Department> updateDept = this.deptDao.findById(deptId);
        if (updateDept.isPresent()){
             deptDao.deleteById(deptId);
        }else{
            throw new DepartmentNotFoundException("Department Not Found");
        }
    }

    public Department getByDeptId(int deptId) {
        return deptDao.getByDeptId(deptId);
    }
}
