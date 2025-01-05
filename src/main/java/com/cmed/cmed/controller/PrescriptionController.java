package com.cmed.cmed.controller;

import com.cmed.cmed.model.Prescription;
import com.cmed.cmed.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;


    // Get all the prescription at once
    @GetMapping("/getallp")
    public ResponseEntity<List<Prescription>> getAllPrescriptions(){
       List<Prescription> prescriptions=prescriptionService.getAllPrescriptions();
       return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }
    // Get prescription by using Date Range
    @GetMapping("/getpbd")
    public ResponseEntity<List<Prescription>> getPrescriptionsByDateRange(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                          @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        if (endDate == null) {
            endDate = startDate;
        }
        List<Prescription> prescriptions=prescriptionService.getPrescriptionsByDateRange(startDate, endDate);
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }
    // Get prescription by using ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPrescriptionById(@PathVariable Long id){
        Optional<Prescription> prescriptions=prescriptionService.getPrescriptionById(id);
        if(prescriptions.isPresent()) {
            return new ResponseEntity<>(prescriptions.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("{message: \"tutorial not found\"}", HttpStatus.NOT_FOUND);
        }
    }
    // Create prescription
    @PostMapping("/createPres")
    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
        Prescription createdPrescription = prescriptionService.createPrescription(prescription);
        return new ResponseEntity<>(createdPrescription, HttpStatus.OK);
    }
    // Update prescription by using ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePrescription(@PathVariable Long id, @RequestBody Prescription prescription) {

        Prescription updatedPrescription = prescriptionService.updatePrescription(id, prescription);
        return new ResponseEntity<>(updatedPrescription, HttpStatus.OK);

    }


    // Delete prescription by using ID
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);

        return ResponseEntity.ok("deleted");
    }


}
