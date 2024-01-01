package com.schoolmanagement.schoolmanagement.controller;

import com.schoolmanagement.schoolmanagement.model.ElementModule;
import com.schoolmanagement.schoolmanagement.model.Enseignant;
import com.schoolmanagement.schoolmanagement.model.Filière;
import com.schoolmanagement.schoolmanagement.model.Modules;
import com.schoolmanagement.schoolmanagement.repository.ElementModuleRepository;
import com.schoolmanagement.schoolmanagement.repository.EnseignantRepository;
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
@RequestMapping("elementmodule")
@CrossOrigin
public class ElementModuleController {
    @Autowired
    private ElementModuleRepository elementModuleRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @PostMapping("/create")
    public String createElementModule(@RequestBody ElementModule elementModule, @RequestParam int mid, @RequestParam int eid) {
        try {
            Optional<Modules> optionalModule = moduleRepository.findById(mid);
            Optional<Enseignant> optionalEnseignant = enseignantRepository.findById(eid);
            if (optionalModule.isPresent() && optionalEnseignant.isPresent()) {
                Modules module = optionalModule.get();
                Enseignant enseignant = optionalEnseignant.get();
                elementModule.setModules(module);
                elementModule.setEnseignant(enseignant);
                elementModuleRepository.save(elementModule);
                return "Element de module created successfully";
            } else {
                return "Error creating Element de Module";
            }
        } catch (DataIntegrityViolationException e) {
            return "Error creating Element de Module: Constraint violation occurred";
        } catch (Exception e) {
            return "Error creating Element de Module: " + e.getMessage();
        }
    }
    @GetMapping
    public ResponseEntity<List<ElementModule>> getAllElementModules(){
        List<ElementModule> ElemModtList = new ArrayList<>();
        elementModuleRepository.findAll().forEach(ElemModtList::add);
        return new ResponseEntity<List<ElementModule>>(ElemModtList, HttpStatus.OK);
    }
    @GetMapping("{id_emodule}")
    public ResponseEntity<ElementModule>getElementModuleById(@PathVariable int id_emodule){
        Optional<ElementModule> emodules = elementModuleRepository.findById(id_emodule);
        if(emodules.isPresent()){
            return new ResponseEntity<ElementModule>(emodules.get(),HttpStatus.FOUND);
        }else {
            return new ResponseEntity<ElementModule>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id_emodule}")
    public String updateElementModule(@PathVariable int id_emodule, @RequestBody ElementModule updatedElementModule, @RequestParam int mid, @RequestParam int eid) {
        try {
            Optional<ElementModule> emod = elementModuleRepository.findById(id_emodule);
            if (emod.isPresent()) {
                ElementModule existingEmod = emod.get();
                existingEmod.setCode_element_module(updatedElementModule.getCode_element_module());
                existingEmod.setElement_module_name(updatedElementModule.getElement_module_name());

                Optional<Modules> newModule = moduleRepository.findById(mid);
                Optional<Enseignant> newEnseignant = enseignantRepository.findById(eid);
                if (newModule.isPresent() && newEnseignant.isPresent()) {

                    existingEmod.setModules(newModule.get());
                    existingEmod.setEnseignant(newEnseignant.get());

                    elementModuleRepository.save(existingEmod);
                    return "Element de Module updated successfully";
                } else {
                    return "Error updating Element de Module";
                }
            }
            return "Element de Module doesn't exist";
        } catch (DataIntegrityViolationException e) {
            return "Error updating Element de Module: Constraint violation occurred";
        } catch (Exception e) {
            return "Error updating elemen de Module: " + e.getMessage();
        }
    }




    @DeleteMapping("delete/{id_emodule}")
    public String deleteModuleById(@PathVariable int id_emodule) {
        try {
            elementModuleRepository.deleteById(id_emodule);
            return "Element de Module deleted successfully";
        } catch (EmptyResultDataAccessException e) {
            return "Element de Module doesn't exist";
        } catch (Exception e) {
            return "Error deleting Element de Module: " + e.getMessage();
        }
    }

    @DeleteMapping("deleteAllElementModules")
    public String deleteAllElementModules() {
        try {
            elementModuleRepository.deleteAll();
            return "All Element de modules deleted successfully";
        } catch (Exception e) {
            return "Error deleting all element de modules: " + e.getMessage();
        }
    }
}
