package com.employee.Service;

import com.employee.Exception.AreaNotFoundException;
import com.employee.Dao.AreaDao;
import com.employee.Model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    @Autowired
    private AreaDao areaDao;

    public Area getByAreaStatus(String areaStatus) {
        return areaDao.getByAreaStatus(areaStatus);
    }

    public void addArea(Area area) {
        areaDao.save(area);
    }

    public List<Area> allArea() {
        return areaDao.findAll();
    }

    public void updateArea(int areaId, Area area) throws AreaNotFoundException {
        Optional<Area> updateArea = this.areaDao.findById(areaId);
        if (updateArea.isPresent()){
            String areaStatus = area.getAreaStatus();
             areaDao.saveByAreaStatus(areaStatus,areaId);
        }else{
            throw new AreaNotFoundException("Area Not Found");
        }
    }

    public void deleteArea(int areaId) throws AreaNotFoundException {
        Optional<Area> updateArea = this.areaDao.findById(areaId);
        if (updateArea.isPresent()){
            areaDao.deleteById(areaId);
        }else{
            throw new AreaNotFoundException("Area Not Found");
        }
    }

    public Area getByAreaId(int areaId) {
        return  areaDao.getByAreaId(areaId);

    }
}
