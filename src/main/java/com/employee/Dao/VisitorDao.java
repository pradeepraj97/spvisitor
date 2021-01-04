package com.employee.Dao;

import com.employee.Model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorDao extends JpaRepository<Visitor,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Visitor v set v.outTime = :outTime where v.visitorId= :visitorId")
   public void updateOutTime(@Param("visitorId") int visitorId, @Param("outTime") Time outTime);

   public List<Visitor> getByEmployeeName(String employeeName);

    @Transactional
    @Modifying
    @Query("UPDATE Visitor v set v.status = :status where v.visitorId= :visitorId")
   public void approveVisitor(@Param("visitorId") int visitorId, @Param("status") String status);

    @Transactional
    @Modifying
    @Query("SELECT v from Visitor v where v.date >= :from and v.date <= :to")
   public List<Visitor> getData(LocalDate from, LocalDate to);

    @Transactional
    @Modifying
    @Query("SELECT v from Visitor v where v.date >= :from and v.date <= :to and v.department= :dept")
    public List<Visitor> getDataByDept(LocalDate from, LocalDate to, String dept);

    @Transactional
    @Modifying
    @Query("SELECT v from Visitor v where v.date >= :from and v.date <= :to and v.area= :area")
    List<Visitor> getDataByArea(LocalDate from, LocalDate to, String area);
}
