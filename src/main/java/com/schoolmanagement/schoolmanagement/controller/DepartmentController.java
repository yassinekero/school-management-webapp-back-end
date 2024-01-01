package com.schoolmanagement.schoolmanagement.controller;

import com.schoolmanagement.schoolmanagement.model.Departement;
import com.schoolmanagement.schoolmanagement.model.Enseignant;
import com.schoolmanagement.schoolmanagement.model.Modules;
import com.schoolmanagement.schoolmanagement.repository.DepartementRepository;
import com.schoolmanagement.schoolmanagement.repository.EnseignantRepository;
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
@RequestMapping("departments")
@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @PostMapping("/create")
    public String createDepartement(@RequestBody Departement departement, @RequestParam int eid) {
        try {
            Optional<Enseignant> optionalEnseignant = enseignantRepository.findById(eid);
            if ( optionalEnseignant.isPresent()) {
                Enseignant enseignant = optionalEnseignant.get();
                departement.setEnseignant(enseignant);
                departementRepository.save(departement);
                return "Department created successfully";
            } else {
                return "Error creating Department";
            }
        } catch (DataIntegrityViolationException e) {
            return "Error creating Element de Module: Constraint violation occurred";
        } catch (Exception e) {
            return "Error creating Element de Module: " + e.getMessage();
        }
    }
    @GetMapping
    public ResponseEntity<List<Departement>> getAllDepartments(){
        List<Departement> deptList = new ArrayList<>();
        departementRepository.findAll().forEach(deptList::add);
        return new ResponseEntity<List<Departement>>(deptList, HttpStatus.OK);

    }

    @GetMapping("{id_dept}")
    public ResponseEntity<Departement>getDeptById(@PathVariable int id_dept){
        Optional<Departement> dept = departementRepository.findById(id_dept);
        if(dept.isPresent()){
            return new ResponseEntity<Departement>(dept.get(),HttpStatus.FOUND);
        }else {
            return new ResponseEntity<Departement>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id_dept}")
    public ResponseEntity<String> updateDept(@PathVariable int id_dept, @RequestBody Departement departement, @RequestParam int eid) {
        try {
            Optional<Departement> dept = departementRepository.findById(id_dept);
            Optional<Enseignant> optionalEnseignant = enseignantRepository.findById(eid);
            if (dept.isPresent() && optionalEnseignant.isPresent())
            {
                Departement existingDept = dept.get();
                Enseignant enseignant = optionalEnseignant.get();
                existingDept.setDept_name(departement.getDept_name());
                existingDept.setEnseignant(enseignant);
                departementRepository.save(existingDept);
                return ResponseEntity.ok("Department updated successfully");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department doesn't exist");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error updating department: Constraint violation occurred");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating department: " + e.getMessage());
        }
    }

    @DeleteMapping("delete/{id_dept}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable int id_dept) {
        try {
            departementRepository.deleteById(id_dept);
            return ResponseEntity.ok("Department deleted successfully");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department doesn't exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting department: " + e.getMessage());
        }
    }

    @DeleteMapping("deleteAllDepartments")
    public ResponseEntity<String> deleteAllDepartments() {
        try {
            departementRepository.deleteAll();
            return ResponseEntity.ok("All departments deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting all departments: " + e.getMessage());
        }
    }


}
