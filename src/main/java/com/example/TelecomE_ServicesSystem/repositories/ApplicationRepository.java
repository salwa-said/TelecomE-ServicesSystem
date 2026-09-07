package com.example.TelecomE_ServicesSystem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.TelecomE_ServicesSystem.entites.*;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("SELECT x FROM Application x WHERE x.isActive=true")
    List<Application> getAllApplication();

    @Query("SELECT x FROM Application x WHERE x.isActive=true AND x.id=:id")
    Application getById(@Param("id") Long id);

    @Query("SELECT a FROM Application a WHERE a.isActive=true AND LOWER(a.status)=LOWER(:status)")
    List<Application> findByStatus(@Param("status") String status);

    @Query("SELECT a FROM Application a WHERE a.isActive=true AND a.citizen.id=:citizenId ORDER BY a.applicationDate DESC")
    List<Application> findByCitizen(@Param("citizenId") Long citizenId);

}
