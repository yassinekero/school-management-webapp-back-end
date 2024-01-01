package com.schoolmanagement.schoolmanagement.controller;

import com.schoolmanagement.schoolmanagement.model.Departement;
import com.schoolmanagement.schoolmanagement.model.Filière;
import com.schoolmanagement.schoolmanagement.model.Modules;
import com.schoolmanagement.schoolmanagement.model.Modules;
import com.schoolmanagement.schoolmanagement.repository.DepartementRepository;
import com.schoolmanagement.schoolmanagement.repository.FiliereRepository;
import com.schoolmanagement.schoolmanagement.repository.ModuleRepository;
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
@RequestMapping("modules")
@CrossOrigin
public class ModuleController {
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private FiliereRepository filiereRepository;
    @PostMapping("/create")
    public String createModule(@RequestBody Modules module, @RequestParam int filiereId) {
        try {
            Optional<Filière> optionalFiliere = filiereRepository.findById(filiereId);

            if (optionalFiliere.isPresent()) {
                Filière filiere = optionalFiliere.get();
                module.setFilière(filiere);
                moduleRepository.save(module);
                return "Module created successfully";
            } else {
                return "Error creating Module: Filière not found";
            }
        } catch (DataIntegrityViolationException e) {
            return "Error creating Module: Constraint violation occurred";
        } catch (Exception e) {
            return "Error creating Module: " + e.getMessage();
        }
    }
    @GetMapping
    public ResponseEntity<List<Modules>> getAllModules(){
        List<Modules> modtList = new ArrayList<>();
        moduleRepository.findAll().forEach(modtList::add);
        return new ResponseEntity<List<Modules>>(modtList, HttpStatus.OK);
    }
    @GetMapping("{id_module}")
    public ResponseEntity<Modules>getModuleById(@PathVariable int id_module){
        Optional<Modules> modules = moduleRepository.findById(id_module);
        if(modules.isPresent()){
            return new ResponseEntity<Modules>(modules.get(),HttpStatus.FOUND);
        }else {
            return new ResponseEntity<Modules>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id_module}")
    public String updateModule(@PathVariable int id_module, @RequestBody Modules updatedModule, @RequestParam int filiereId) {
        try {
            Optional<Modules> mod = moduleRepository.findById(id_module);
            if (mod.isPresent()) {
                Modules existingMod = mod.get();
                existingMod.setCode_module(updatedModule.getCode_module());
                existingMod.setModule_name(updatedModule.getModule_name());

                // Recherche de la filière par son ID
                Optional<Filière> newFiliere = filiereRepository.findById(filiereId);

                if (newFiliere.isPresent()) {
                    // Associer la nouvelle filière au module
                    existingMod.setFilière(newFiliere.get());

                    // Enregistrer les modifications dans le repository des modules
                    moduleRepository.save(existingMod);
                    return "Module updated successfully";
                } else {
                    return "Error updating Module: Filière not found";
                }
            }
            return "Module doesn't exist";
        } catch (DataIntegrityViolationException e) {
            return "Error updating Module: Constraint violation occurred";
        } catch (Exception e) {
            return "Error updating Module: " + e.getMessage();
        }
    }




    @DeleteMapping("delete/{id_module}")
    public String deleteModuleById(@PathVariable int id_module) {
        try {
            moduleRepository.deleteById(id_module);
            return "Module deleted successfully";
        } catch (EmptyResultDataAccessException e) {
            return "Module doesn't exist";
        } catch (Exception e) {
            return "Error deleting Module: " + e.getMessage();
        }
    }

    @DeleteMapping("deleteAllModules")
    public String deleteAllModules() {
        try {
            moduleRepository.deleteAll();
            return "All modules deleted successfully";
        } catch (Exception e) {
            return "Error deleting all modules: " + e.getMessage();
        }
}
}
