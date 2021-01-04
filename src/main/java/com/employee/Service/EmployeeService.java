package com.employee.Service;

import com.employee.Exception.UserNotFoundException;
import com.employee.Dao.EmployeeDao;
import com.employee.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public void addUser(Employee employee) {
          employeeDao.save(employee);
    }

    public Employee getByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException {
//        Optional<Employee> pass = this.employeeDao.findByPassword(password);
//        if (pass.isPresent()) {
//            Employee newpass = pass.get();
//            long id = newpass.getId();
//            System.out.println(id);
            return  employeeDao.getByEmailId(emailId);
//        } else {
//            throw new UserNotFoundException("User Not Found");
//        }

    }


    public void updatePassword(Employee employee) throws UserNotFoundException {
        Optional<Employee> update = this.employeeDao.findByEmailId(employee.getEmailId());
        if (update.isPresent()) {
            Employee newpass = update.get();
            newpass.setPassword(employee.getPassword());
            employeeDao.save(newpass);
        } else {
            throw new UserNotFoundException("User Not Found");
        }

    }

    public List<Employee> getEmployee() {
        return employeeDao.findAll();
    }

    public void deleteById(long id) throws UserNotFoundException {
        Optional<Employee> delete = this.employeeDao.findById(id);
        if (delete.isPresent()) {
            employeeDao.deleteById(id);
        } else {
            throw new UserNotFoundException("User Not Found");
        }
    }

    public Employee getById(long id) throws UserNotFoundException {
        Optional<Employee> get = this.employeeDao.findById(id);
        if (get.isPresent()) {
           return employeeDao.getById(id);
        } else {
            throw new UserNotFoundException("User Not Found");
        }
    }

    public void updateById(Employee employee, long id) throws UserNotFoundException {
        Optional<Employee> updateUser = this.employeeDao.findById(id);
        if(updateUser.isPresent()){
            String username = employee.getUsername();
            String emailId = employee.getEmailId();
            String phoneNumber = employee.getPhoneNumber();
            int area_id = employee.getArea().getAreaId();
            int role_id = employee.getRole().getRoleId();
            int dept_id = employee.getDepartment().getDeptId();
            employeeDao.saveById(username,id,emailId,phoneNumber,area_id,role_id,dept_id);
        }
        else {
            throw new UserNotFoundException("User Not Found");
        }
    }
}
