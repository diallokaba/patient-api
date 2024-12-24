package com.fhunhospital.patient_management.services;

import com.fhunhospital.patient_management.entites.Contact;
import com.fhunhospital.patient_management.entites.Patient;
import com.fhunhospital.patient_management.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService{

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient update(String id, Patient updatedPatient) {
        Optional<Patient> existingPatientOptional = patientRepository.findById(id);
        if (existingPatientOptional.isEmpty()) {
            throw new RuntimeException("Patient with ID " + id + " not found");
        }

        Patient existingPatient = existingPatientOptional.get();

        if (updatedPatient.getNom() != null && !updatedPatient.getNom().isBlank()) {
            existingPatient.setNom(updatedPatient.getNom());
        }
        if (updatedPatient.getPrenom() != null && !updatedPatient.getPrenom().isBlank()) {
            existingPatient.setPrenom(updatedPatient.getPrenom());
        }
        if (updatedPatient.getDateNaissance() != null) {
            existingPatient.setDateNaissance(updatedPatient.getDateNaissance());
        }
        if (updatedPatient.getSexe() != null) {
            existingPatient.setSexe(updatedPatient.getSexe());
        }
        if(updatedPatient.getTaille() != null && updatedPatient.getTaille() > 0){
            existingPatient.setTaille(updatedPatient.getTaille());
        }
        if(updatedPatient.getPoids() != null && updatedPatient.getPoids() > 0){
            existingPatient.setTaille(updatedPatient.getTaille());
        }
        if (updatedPatient.getContacts() != null && !updatedPatient.getContacts().isEmpty()) {
            existingPatient.setContacts(updatedPatient.getContacts());
        }

        return patientRepository.save(existingPatient);
    }

    public Patient findById(String id) {
        return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("No patient found with id: " + id));
    }

    public void delete(String id){
        patientRepository.deleteById(id);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient addContact(String id, Contact contact) {
        Patient patient = findById(id);
        patient.getContacts().add(contact);
        return patientRepository.save(patient);
    }

    public Patient updateContact(String id, int index, Contact updatedContact) {
        Patient patient = findById(id);
        List<Contact> contacts = patient.getContacts();

        if (index < 0 || index >= contacts.size()) {
            throw new RuntimeException("Invalid contact index: " + index);
        }

        contacts.set(index, updatedContact);
        patient.setContacts(contacts);
        return patientRepository.save(patient);
    }

    // Supprimer un contact spécifique d'un patient
    public Patient deleteContact(String id, int index) {
        Patient patient = findById(id);
        List<Contact> contacts = patient.getContacts();

        if (index < 0 || index >= contacts.size()) {
            throw new RuntimeException("Invalid contact index: " + index);
        }

        contacts.remove(index);
        patient.setContacts(contacts);

        return patientRepository.save(patient);
    }
}
