package com.cmed.cmed.repository;

import com.cmed.cmed.model.Prescription;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {


    List<Prescription> findByPrescriptionDateBetween(LocalDate startDate, LocalDate endDate, Sort sortByDate);
}
