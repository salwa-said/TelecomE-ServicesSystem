package com.example.TelecomE_ServicesSystem.repositories;

import com.example.TelecomE_ServicesSystem.entites.DomainRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;
@Repository
public interface DomainRegistrationRepository extends JpaRepository<DomainRegistration, Long> {
    @Query("SELECT x FROM DomainRegistration x WHERE x.isActive=true")
    List<DomainRegistration> getAllDomainRegistration();

    @Query("SELECT x FROM DomainRegistration x WHERE x.isActive=true AND x.id=:id")
    DomainRegistration getById(@Param("id") Long id);

    @Query("SELECT d FROM DomainRegistration d WHERE d.isActive=true AND LOWER(d.domainName)=LOWER(:domainName)")
    DomainRegistration findActiveByDomainName(@Param("domainName") String domainName);

}
