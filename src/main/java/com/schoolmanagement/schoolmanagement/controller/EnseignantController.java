package com.schoolmanagement.schoolmanagement.controller;

import com.schoolmanagement.schoolmanagement.model.ElementModule;
import com.schoolmanagement.schoolmanagement.model.Enseignant;
import com.schoolmanagement.schoolmanagement.model.Modules;
import com.schoolmanagement.schoolmanagement.repository.ElementModuleRepository;
import com.schoolmanagement.schoolmanagement.repository.EnseignantRepository;
import com.schoolmanagement.schoolmanagement.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("enseignants")
@CrossOrigin
public class EnseignantController {
    @Autowired
    private ElementModuleRepository elementModuleRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;

    @PostMapping("/create")
    public String createEnseignant(@RequestBody Enseignant enseignant) {

        enseignantRepository.save(enseignant);
        return "Enseignant created successfully";

    }

    @GetMapping
    public ResponseEntity<List<Enseignant>> getAllEnseignants() {
        List<Enseignant> EnstList = new ArrayList<>();
        enseignantRepository.findAll().forEach(EnstList::add);
        return new ResponseEntity<List<Enseignant>>(EnstList, HttpStatus.OK);
    }

    @GetMapping("{id_ens}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable int id_ens) {
        Optional<Enseignant> enseignant = enseignantRepository.findById(id_ens);
        if (enseignant.isPresent()) {
            return new ResponseEntity<Enseignant>(enseignant.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<Enseignant>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id_ens}")
    public String updateEnseignant(@PathVariable int id_ens, @RequestBody Enseignant updatedEnseignant) {
        try {
            Optional<Enseignant> ens = enseignantRepository.findById(id_ens);
            if (ens.isPresent()) {
                Enseignant existingEns = ens.get();
                existingEns.setNomEnseignant(updatedEnseignant.getNomEnseignant());
                existingEns.setPrenomEnseignant(updatedEnseignant.getPrenomEnseignant());

                enseignantRepository.save(existingEns);
                return "Enseignant updated successfully";
            }
            return "Enseignant doesn't exist";
        } catch (DataIntegrityViolationException e) {
            return "Error updating Enseignant: Constraint violation occurred";
        } catch (Exception e) {
            return "Error updating enseignant: " + e.getMessage();
        }
    }


    @DeleteMapping("delete/{id_ens}")
    public String deleteEnsById(@PathVariable int id_ens) {
        try {
            enseignantRepository.deleteById(id_ens);
            return "Enseignant deleted successfully";
        } catch (EmptyResultDataAccessException e) {
            return "Enseignant doesn't exist";
        } catch (Exception e) {
            return "Error deleting Enseignant: " + e.getMessage();
        }
    }

    @DeleteMapping("deleteAllEnseignantModules")
    public String deleteAllEns() {
        try {
            enseignantRepository.deleteAll();
            return "All Enseignants deleted successfully";
        } catch (Exception e) {
            return "Error deleting all enseignants: " + e.getMessage();
        }
    }

}
