package com.example.TelecomE_ServicesSystem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;
import com.example.TelecomE_ServicesSystem.entites.*;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    @Query("SELECT x FROM Complaint x WHERE x.isActive=true")
    List<Complaint> getAllComplaint();

    @Query("SELECT x FROM Complaint x WHERE x.isActive=true AND x.id=:id")
    Complaint getById(@Param("id") Long id);

    @Query("SELECT c FROM Complaint c WHERE c.isActive=true AND LOWER(c.status)=LOWER('open')")
    List<Complaint> findOpenComplaints();

    @Query("SELECT c FROM Complaint c WHERE c.isActive=true AND c.operator.id=:operatorId AND LOWER(c.status)=LOWER('open')")
    List<Complaint> findOpenByOperator(@Param("operatorId") Long operatorId);

}
