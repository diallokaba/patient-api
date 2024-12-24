package com.fhunhospital.patient_management.controllers;

import com.fhunhospital.patient_management.entites.Contact;
import com.fhunhospital.patient_management.entites.Patient;
import com.fhunhospital.patient_management.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin("*")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> allPatients() {
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Patient> save(@RequestBody Patient patient){
        return new ResponseEntity<>(patientService.save(patient), HttpStatus.CREATED) ;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> update(@RequestBody Patient patient, @PathVariable String id){
        return new ResponseEntity<>(patientService.update(id, patient), HttpStatus.CREATED) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable String id){
        return new ResponseEntity<>(patientService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idPatient}/contact")
    public ResponseEntity<Patient> addContact(@PathVariable String idPatient, @RequestBody Contact contact) {
        return new ResponseEntity<>(patientService.addContact(idPatient, contact), HttpStatus.CREATED);
    }

    @PutMapping("/{idPatient}/contact/{index}")
    public ResponseEntity<Patient> updateContact(
            @PathVariable String idPatient,
            @PathVariable int index,
            @RequestBody Contact contact
    ) {
        return new ResponseEntity<>(patientService.updateContact(idPatient, index, contact), HttpStatus.OK);
    }

    @DeleteMapping("/{idPatient}/contact/{index}")
    public ResponseEntity<Patient> deleteContact(@PathVariable String idPatient, @PathVariable int index) {
        return new ResponseEntity<>(patientService.deleteContact(idPatient, index), HttpStatus.OK);
    }
}
