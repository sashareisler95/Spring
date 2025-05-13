package com.demo.repository;

import com.demo.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    @Query(value = "SELECT next_date FROM medication WHERE name = ?", nativeQuery = true)
    String findNextDate(String nameOfMeds);

    @Query(value = "SELECT * FROM medication WHERE name = ?", nativeQuery = true)
    Medication findMedsByName(String nameOfMeds);

    @Query(value = "SELECT last_date FROM medication WHERE name = ?", nativeQuery = true)
    String findLastDate(String nameOfMeds);

    @Query(value = "SELECT interval FROM medication WHERE name = ?", nativeQuery = true)
    Long findInterval(String nameOfMeds);

    @Query(value = "UPDATE medication SET interval = ?1 WHERE name = ?2", nativeQuery = true)
    void setInterval(Long interval, String name);

    @Query(value = "UPDATE medication SET next_date = ?2 WHERE name = ?1", nativeQuery = true)
    void setNextDate(String name, String newDate);

    @Query(value = "UPDATE medication SET last_date = ?2 WHERE name = ?1", nativeQuery = true)
    void setLastDate(String name, String newDate);


}
