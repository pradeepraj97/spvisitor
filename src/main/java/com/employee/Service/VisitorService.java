package com.employee.Service;

import com.employee.Exception.UserNotFoundException;
import com.employee.Dao.VisitorDao;
import com.employee.Model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorService {
    
    @Autowired
    private VisitorDao visitorDao;

    public void addVisitor(Visitor visitor) {
        visitorDao.save(visitor);
    }

    public List<Visitor> allVisitor() {
        return visitorDao.findAll();
    }

    public void updateVisitor(int visitorId, Visitor visitor) throws UserNotFoundException {
        Optional<Visitor> updateVisitor = this.visitorDao.findById(visitorId);
        if(updateVisitor.isPresent()){
            visitorDao.save(visitor);
        }else{
            throw new UserNotFoundException("Visitor Not Found");
        }
    }

    public void deleteVisitor(int visitorId) throws UserNotFoundException {
        Optional<Visitor> delete = this.visitorDao.findById(visitorId);
        if(delete.isPresent()){
            visitorDao.deleteById(visitorId);
        }else{
            throw new UserNotFoundException("Visitor Not Found");
        }
    }

    public void updateOutTime(int visitorId, Time outTime) throws UserNotFoundException {
        Optional<Visitor> updateTime = this.visitorDao.findById(visitorId);
        if(updateTime.isPresent()){
            visitorDao.updateOutTime(visitorId,outTime);
        }else{
            throw new UserNotFoundException("Visitor Not Found");
        }
    }

    public List<Visitor> getByEmployeeName(String employeeName) throws UserNotFoundException {
        List<Visitor> get = this.visitorDao.getByEmployeeName(employeeName);
        if(!(get.equals(null))){
           return get;
       }else{
            throw new UserNotFoundException("Visitor Not Found");
        }
    }

    public void approveVisitor(int visitorId, String status) {
         visitorDao.approveVisitor(visitorId,status);
    }

    public List<Visitor> getData(String fromDate, String toDate) {
        Visitor v = new Visitor();
         v.setDate(fromDate);
        LocalDate from = v.getDate();
         v.setDate(toDate);
         LocalDate to = v.getDate();
         System.out.println(from+" "+to);
        return  visitorDao.getData(from,to);
    }

    public List<Visitor> getDataByDept(String fromDate, String toDate,String dept) {
        Visitor visit = new Visitor();
        visit.setDate(fromDate);
        LocalDate from = visit.getDate();
        visit.setDate(toDate);
        LocalDate to = visit.getDate();
        System.out.println(from+" "+to);
        System.out.println(dept);
        return  visitorDao.getDataByDept(from,to,dept);
    }

    public List<Visitor> getDataByArea(String fromDate, String toDate, String area) {
        Visitor visitor = new Visitor();
        visitor.setDate(fromDate);
        LocalDate from = visitor.getDate();
        visitor.setDate(toDate);
        LocalDate to = visitor.getDate();
        System.out.println(from+" "+to);
        return  visitorDao.getDataByArea(from,to,area);
    }
}
