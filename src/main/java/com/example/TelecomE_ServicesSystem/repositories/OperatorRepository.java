package com.example.TelecomE_ServicesSystem.repositories;
import com.example.TelecomE_ServicesSystem.entites.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;


@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
    @Query("SELECT x FROM Operator x WHERE x.isActive=true")
    List<Operator> getAllOperator();

    @Query("SELECT x FROM Operator x WHERE x.isActive=true AND x.id=:id")
    Operator getById(@Param("id") Long id);

}