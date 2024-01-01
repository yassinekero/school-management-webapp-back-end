package com.schoolmanagement.schoolmanagement.controller;

import com.schoolmanagement.schoolmanagement.model.Departement;
import com.schoolmanagement.schoolmanagement.model.Filière;
import com.schoolmanagement.schoolmanagement.repository.DepartementRepository;
import com.schoolmanagement.schoolmanagement.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("filieres")
@CrossOrigin
public class FiliereController {
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired DepartementRepository departementRepository;
    @PostMapping("/create")
    public ResponseEntity<String> createFiliere(@RequestBody Filière filiere, @RequestParam int deptId) {
        try {
            Optional<Departement> optionalDepartement = departementRepository.findById(deptId);

            if (optionalDepartement.isPresent()) {
                Departement departement = optionalDepartement.get();
                filiere.setDepartement(departement);
                filiereRepository.save(filiere);
                return ResponseEntity.ok("Filière created successfully");
            } else {
                return ResponseEntity.badRequest().body("Error creating Filière: Department not found");
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error creating Filière: Constraint violation occurred");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Filière: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Filière>> getAllFilieres(){
        List<Filière> filiereList = new ArrayList<>();
        filiereRepository.findAll().forEach(filiereList::add);
        return new ResponseEntity<List<Filière>>(filiereList, HttpStatus.OK);

    }

    @GetMapping("{id_filiere}")
    public ResponseEntity<Filière>getDeptById(@PathVariable int id_filiere){
        Optional<Filière> filiere = filiereRepository.findById(id_filiere);
        if(filiere.isPresent()){
            return new ResponseEntity<Filière>(filiere.get(),HttpStatus.FOUND);
        }else {
            return new ResponseEntity<Filière>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id_filiere}")
    public String updateFiliere(@PathVariable int id_filiere, @RequestBody Filière updatedFiliere,@RequestParam int deptId) {
        try {
            Optional<Filière> filiere = filiereRepository.findById(id_filiere);
            if (filiere.isPresent()) {
                Filière existingFil = filiere.get();
                existingFil.setFiliere_name(updatedFiliere.getFiliere_name());
                existingFil.setChef_filière(updatedFiliere.getChef_filière());

                Optional<Departement> newDepartment = departementRepository.findById(deptId);

                if (newDepartment.isPresent()) {
                    existingFil.setDepartement(newDepartment.get());
                    filiereRepository.save(existingFil);
                    return "Filière updated successfully";
                } else {
                    return "Error updating Filière: Department not found";
                }
            }
            return "Filière doesn't exist";
        } catch (DataIntegrityViolationException e) {
            return "Error updating Filière: Constraint violation occurred";
        } catch (Exception e) {
            return "Error updating Filière: " + e.getMessage();
        }
    }


    @DeleteMapping("delete/{id_filiere}")
    public String deleteFiliereById(@PathVariable int id_filiere) {
        try {
            filiereRepository.deleteById(id_filiere);
            return "Filière deleted successfully";
        } catch (EmptyResultDataAccessException e) {
            return "Filière doesn't exist";
        } catch (Exception e) {
            return "Error deleting Filière: " + e.getMessage();
        }
    }

    @DeleteMapping("deleteAllFiliere")
    public String deleteAllFilere() {
        try {
            filiereRepository.deleteAll();
            return "All filières deleted successfully";
        } catch (Exception e) {
            return "Error deleting all filières: " + e.getMessage();
        }
    }

}
