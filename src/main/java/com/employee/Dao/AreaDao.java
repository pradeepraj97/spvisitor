package com.employee.Dao;

import com.employee.Model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AreaDao extends JpaRepository<Area,Integer> {
    public Area getByAreaStatus(String areaStatus);

   public Area getByAreaId(int areaId);

    @Transactional
    @Modifying
    @Query("UPDATE Area a set a.areaStatus = :areaStatus where a.areaId= :areaId")
   public  void saveByAreaStatus(String areaStatus, int areaId);
}
