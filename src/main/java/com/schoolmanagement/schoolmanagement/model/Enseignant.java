package com.schoolmanagement.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Element;
import java.util.HashSet;
import java.util.Set;

@Table(name = "Enseignant", schema = "school_management")
@Entity
@Getter
@Setter
public class Enseignant {
    @ManyToOne
    @JoinColumn(name = "element_module_id ",referencedColumnName = "id_element_module")
    private ElementModule elementModule;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_enseignant;
    private String nomEnseignant;

    private String prenomEnseignant;

    @ManyToMany(mappedBy = "enseignants")
    private Set<ElementModule> elements = new HashSet<>();




}
