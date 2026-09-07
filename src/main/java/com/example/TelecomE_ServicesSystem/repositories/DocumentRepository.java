package com.example.TelecomE_ServicesSystem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.TelecomE_ServicesSystem.entites.*;

@Repository
public interface DocumentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT x FROM Document x WHERE x.isActive=true")
    List<Document> getAllDocument();

    @Query("SELECT x FROM Document x WHERE x.isActive=true AND x.id=:id")
    Document getById(@Param("id") Long id);

}