package com.cmed.cmed.service;

import com.cmed.cmed.model.Prescription;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrescriptionService {

    List<Prescription> getAllPrescriptions();

    Optional<Prescription> getPrescriptionById(Long id);

    // List<DayWiseCount> getPrescriptionCountByDay();

   List<Prescription> getPrescriptionsByDateRange(LocalDate startDate, LocalDate endDate);

    Prescription createPrescription(Prescription prescription);

    Prescription updatePrescription(Long id, Prescription prescription);

    void deletePrescription(Long id);

}
