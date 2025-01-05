package com.cmed.cmed.service;

import com.cmed.cmed.model.Prescription;
import com.cmed.cmed.repository.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionServiceImplement implements PrescriptionService{

    @Autowired
    private  PrescriptionRepo prescriptionRepo;



    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepo.findAll();
    }

    @Override
    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepo.findById(id);
    }




    @Override
    public List<Prescription> getPrescriptionsByDateRange(LocalDate startDate, LocalDate endDate) {
        Sort sortByDate = Sort.by(Sort.Direction.DESC, "prescriptionDate");
        return prescriptionRepo.findByPrescriptionDateBetween(startDate, endDate, sortByDate);
    }



    @Override
    public Prescription createPrescription(Prescription prescription) {

        return prescriptionRepo.save(prescription);
    }

    @Override
    public Prescription updatePrescription(Long id, Prescription prescription) {
        Optional<Prescription> prescriptionOptional= prescriptionRepo.findById(id);
        if (prescriptionOptional.isPresent()) {
            Prescription existingPrescription = prescriptionOptional.get();
            existingPrescription.setPatientName(prescription.getPatientName());
            existingPrescription.setDiagnosis(prescription.getDiagnosis());
            existingPrescription.setNextVisitDate(prescription.getNextVisitDate());
            existingPrescription.setMedicines(prescription.getMedicines());
            return prescriptionRepo.save(existingPrescription);
        }
        else {
            throw new RuntimeException("Prescription not found with id: " + id);
        }

    }

    @Override
    public void deletePrescription(Long id) {
        if (prescriptionRepo.existsById(id)) {
            prescriptionRepo.deleteById(id);
        } else {
            throw new RuntimeException("Prescription not found with id: " + id);
        }
    }


}
