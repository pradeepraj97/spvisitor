package com.employee.Controller;

import com.employee.Exception.UserNotFoundException;
import com.employee.Model.Visitor;
import com.employee.Service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/visitor")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @PostMapping("/newvisitor")
    public void addVisitor(@RequestBody Visitor visitor){
        visitorService.addVisitor(visitor);
    }

    @GetMapping("/all")
    public List<Visitor> allVisitor(){
        return visitorService.allVisitor();
    }

    @PutMapping("/update/{visitorId}")
    public void updateVisitor(@PathVariable int visitorId,@RequestBody Visitor visitor) throws UserNotFoundException {
        visitorService.updateVisitor(visitorId,visitor);
    }

    @DeleteMapping("/delete/{visitorId}")
    public void deleteVisitor(@PathVariable int visitorId) throws UserNotFoundException{
        visitorService.deleteVisitor(visitorId);
    }

    @PutMapping("/outTime/{visitorId}/{outTime}")
    public void updateOutTime(@PathVariable int visitorId, @PathVariable Time outTime) throws UserNotFoundException {
        visitorService.updateOutTime(visitorId,outTime);
    }

    @GetMapping("/all/{employeeName}")
    public List<Visitor> getByEmployeeName(@PathVariable String employeeName) throws UserNotFoundException {
        List<Visitor> visit = visitorService.getByEmployeeName(employeeName);
        return visit;
    }

    @PutMapping("/status/{visitorId}/{status}")
    public void approveVisitor(@PathVariable int visitorId, @PathVariable String status){
            visitorService.approveVisitor(visitorId,status);
        }

    @GetMapping("/data/{fromDate}/{toDate}")
    public List<Visitor> getData(@PathVariable String fromDate, @PathVariable String toDate){
        return visitorService.getData(fromDate,toDate);
    }

    @GetMapping("/data/department/{fromDate}/{toDate}/{dept}")
    public List<Visitor> getDataByDept(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String dept){
        return visitorService.getDataByDept(fromDate,toDate,dept);
    }

    @GetMapping("/data/area/{fromDate}/{toDate}/{area}")
    public List<Visitor> getDataByArea(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String area){
        return visitorService.getDataByArea(fromDate,toDate,area);
    }

}
