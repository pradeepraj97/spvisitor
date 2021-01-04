package com.employee.Controller;

import com.employee.Exception.AreaNotFoundException;
import com.employee.Model.Area;
import com.employee.Model.Department;
import com.employee.Service.AreaService;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @GetMapping("/status/{areaStatus}")
    public int getByAreaStatus( @PathVariable String areaStatus) throws AreaNotFoundException {
        Area area = areaService.getByAreaStatus(areaStatus);
        if (area.equals(null)) {
            throw new AreaNotFoundException("Area Not Found");
        } else {
            int areaId = area.getAreaId();
            return areaId;
        }
    }

    @PostMapping("/add")
    public void addArea(@RequestBody Area area){
        areaService.addArea(area);
    }

    @GetMapping("/all")
    public String allArea(){
        JSONArray jsonArray = new JSONArray();
        try {
            for(Area area : areaService.allArea()){

                JSONObject data = new JSONObject();
                data.put("areaId",area.getAreaId());
                data.put("areaStatus",area.getAreaStatus());
                jsonArray.put(data);
            }

        }catch (Exception e ) {
        }


        return jsonArray.toString();
    }

    @PutMapping("/update/{areaId}")
    public void updateArea(@PathVariable int areaId, @RequestBody Area area) throws AreaNotFoundException {
         areaService.updateArea(areaId,area);
    }

    @DeleteMapping("/delete/{areaId}")
    public void deleteArea(@PathVariable int areaId) throws AreaNotFoundException {
        areaService.deleteArea(areaId);
    }

    @GetMapping("/id/{areaId}")
    public String getByAreaId(@PathVariable int areaId) throws AreaNotFoundException {
        Area area = areaService.getByAreaId(areaId);
        if (area.equals(null)) {
            throw new AreaNotFoundException("Area Not Found");
        } else {
            String areaStatus = area.getAreaStatus();
            return areaStatus;
        }
    }
}
